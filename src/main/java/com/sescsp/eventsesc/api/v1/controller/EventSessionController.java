package com.sescsp.eventsesc.api.v1.controller;

import com.sescsp.eventsesc.api.v1.assembler.SessionModelAssembler;
import com.sescsp.eventsesc.api.v1.assembler.SessionModelDisassembler;
import com.sescsp.eventsesc.api.v1.model.SessionModel;
import com.sescsp.eventsesc.api.v1.model.input.SessionInput;
import com.sescsp.eventsesc.api.v1.openapi.controller.EventSessionControllerOpenApi;
import com.sescsp.eventsesc.domain.model.Event;
import com.sescsp.eventsesc.domain.model.Session;
import com.sescsp.eventsesc.domain.repository.SessionRepository;
import com.sescsp.eventsesc.domain.service.EventService;
import com.sescsp.eventsesc.domain.service.SessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(path = "/v1/events/{eventId}/sessions", produces = MediaType.APPLICATION_JSON_VALUE)
public class EventSessionController implements EventSessionControllerOpenApi {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private EventService eventService;

    @Autowired
    private SessionModelAssembler sessionModelAssembler;

    @Autowired
    private SessionModelDisassembler sessionModelDisassembler;


    @Override
    @GetMapping
    public List<SessionModel> list(@PathVariable Long eventId,
                                   @RequestParam (required = false, defaultValue = "false") Boolean getInactive) {
        Event event = eventService.searchOrFail(eventId);
        List<Session> allSessions = null;

        if (getInactive){
            allSessions = sessionRepository.findAllByEvent(event);
        } else{
            allSessions = sessionRepository.findAllActiveSessionWithTickets(event);
        }

        return sessionModelAssembler.toCollectionModel(allSessions);
    }

    @Override
    @GetMapping("/{sessionId}")
    public SessionModel search (@PathVariable Long eventId, @PathVariable Long sessionId) {
        Session session = sessionService.findSessionByEventOrFail(eventId, sessionId);
        return sessionModelAssembler.toModel(session);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SessionModel add (@PathVariable Long eventId, @RequestBody @Valid SessionInput sessionInput) {
        Event event = eventService.searchOrFail(eventId);
        Session session = sessionModelDisassembler.toDomainObject(sessionInput);
        session.setEvent(event);
        return sessionModelAssembler.toModel(sessionService.save(session));
    }

    @Override
    @PutMapping("/{sessionId}")
    public SessionModel update (@PathVariable Long eventId, @PathVariable Long sessionId,
                           @RequestBody @Valid SessionInput sessionInput) {

        Session currentSession = sessionService.findSessionByEventOrFail(eventId, sessionId);
        sessionModelDisassembler.copyToDomainObject(sessionInput, currentSession);

        currentSession = sessionService.save(currentSession);
        return sessionModelAssembler.toModel(currentSession);
    }

}
