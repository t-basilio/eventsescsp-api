package com.sescsp.eventsesc.api.v1.controller;

import com.sescsp.eventsesc.api.v1.assembler.TicketModelAssembler;
import com.sescsp.eventsesc.api.v1.assembler.TicketModelDisassembler;
import com.sescsp.eventsesc.api.v1.model.TicketModel;
import com.sescsp.eventsesc.api.v1.model.input.TicketInput;
import com.sescsp.eventsesc.domain.exception.TicketDoNotBelongSession;
import com.sescsp.eventsesc.domain.model.Event;
import com.sescsp.eventsesc.domain.model.Session;
import com.sescsp.eventsesc.domain.model.Ticket;
import com.sescsp.eventsesc.domain.repository.TicketRepository;
import com.sescsp.eventsesc.domain.service.SessionService;
import com.sescsp.eventsesc.domain.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/events/{eventId}/sessions/{sessionId}/tickets", produces = MediaType.APPLICATION_JSON_VALUE)
public class SessionTicketController {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketModelAssembler ticketModelAssembler;

    @Autowired
    private TicketModelDisassembler ticketModelDisassembler;

    @GetMapping
    public List<TicketModel> list(@PathVariable Long eventId, @PathVariable Long sessionId) {
        Session session = sessionService.findSessionByEventOrFail(eventId, sessionId);
        List<Ticket> tickets = ticketRepository.findAllBySession(session);

        return ticketModelAssembler.toCollectionModel(tickets);
    }

    @GetMapping("/{ticketId}")
    public TicketModel search (@PathVariable Long eventId, @PathVariable Long sessionId, @PathVariable Long ticketId) {
        Session session = sessionService.findSessionByEventOrFail(eventId, sessionId);
        Ticket ticket = ticketService.searchOrFail(ticketId);

        if (!ticket.getSession().equals(session)){
            throw new TicketDoNotBelongSession(ticketId, sessionId);
        }
        return ticketModelAssembler.toModel(ticket);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TicketModel add (@PathVariable Long eventId, @PathVariable Long sessionId,
                       @RequestBody @Valid TicketInput ticketInput) {
        Ticket ticket = ticketModelDisassembler.toDomainObject(ticketInput);
        Session session = sessionService.findSessionByEventOrFail(eventId, sessionId);
        ticketService.addTicket(session, ticket);
        return ticketModelAssembler.toModel(ticketService.save(ticket));
    }


}
