package com.sescsp.eventsesc.domain.service;

import com.sescsp.eventsesc.domain.exception.EntityInUseException;
import com.sescsp.eventsesc.domain.exception.TicketNotFoundException;
import com.sescsp.eventsesc.domain.exception.TicketSoldOutException;
import com.sescsp.eventsesc.domain.model.Category;
import com.sescsp.eventsesc.domain.model.Session;
import com.sescsp.eventsesc.domain.model.Ticket;
import com.sescsp.eventsesc.domain.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TicketService {

    public static final String MSG_TICKET_IN_USE =
            "Ingresso de código %d não pode ser removido, pois está em uso";

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private CategoryService categoryService;

    @Transactional
    public Ticket save (Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Transactional
    public void exclude (Long ticketId) {
        try {
            ticketRepository.deleteById(ticketId);
            ticketRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw new TicketNotFoundException(ticketId);

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format(MSG_TICKET_IN_USE, ticketId));
        }
    }

    public Ticket searchOrFail(Long ticketId){
        return ticketRepository.findById(ticketId)
                .orElseThrow( () -> new TicketNotFoundException(ticketId));
    }

    public void addTicket(Session session, Ticket ticket){
        Category category = categoryService.searchOrFail(ticket.getCategory().getId());
        ticket.setCategory(category);
        ticket.setSession(session);
        if (!session.addTicket()){
            throw new TicketSoldOutException(session.getId(), session.getEvent().getId());
        }
    }
}
