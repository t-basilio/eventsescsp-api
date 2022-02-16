package com.sescsp.eventsesc.domain.service;

import com.sescsp.eventsesc.domain.exception.*;
import com.sescsp.eventsesc.domain.model.Event;
import com.sescsp.eventsesc.domain.model.Session;
import com.sescsp.eventsesc.domain.model.Ticket;
import com.sescsp.eventsesc.domain.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SessionService {

    public static final String MSG_SESSION_IN_USE =
            "Sessão de código %d não pode ser removida, pois está em uso";

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private EventService eventService;

    @Autowired
    private TicketService ticketService;

    @Transactional
    public Session save (Session session) {
        return sessionRepository.save(session);
    }

    @Transactional
    public void exclude (Long sessionId) {
        try {
            sessionRepository.deleteById(sessionId);
            sessionRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw new SessionNotFoundException(sessionId);

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format(MSG_SESSION_IN_USE, sessionId));
        }
    }

    public Session searchOrFail(Long sessionId){
        return sessionRepository.findById(sessionId)
                .orElseThrow( () -> new SessionNotFoundException(sessionId));
    }

    public Session findSessionByEventOrFail(Long eventId, Long sessionId) {
        Event event = eventService.searchOrFail(eventId);
        Session session = searchOrFail(sessionId);
        Session sessionSearched = sessionRepository.findSessionByEvent(event, session);
       if (sessionSearched == null){
           throw new SessionDoNotBelongEvent(session.getId(), event.getId());
       }
       return sessionSearched;
    }

}
