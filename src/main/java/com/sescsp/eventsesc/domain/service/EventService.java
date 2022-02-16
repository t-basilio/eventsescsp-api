package com.sescsp.eventsesc.domain.service;
import com.sescsp.eventsesc.domain.exception.EntityInUseException;
import com.sescsp.eventsesc.domain.exception.EventNotFoundException;
import com.sescsp.eventsesc.domain.model.Event;
import com.sescsp.eventsesc.domain.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {

    public static final String MSG_EVENT_IN_USE =
            "Evento de código %d não pode ser removido, pois está em uso";

    @Autowired
    private EventRepository eventRepository;

    @Transactional
    public Event save (Event event) {
        return eventRepository.save(event);
    }

    @Transactional
    public void exclude (Long eventId) {
        try {
            eventRepository.deleteById(eventId);
            eventRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw new EventNotFoundException(eventId);

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format(MSG_EVENT_IN_USE, eventId));
        }
    }

    public Event searchOrFail( Long eventId){
        return eventRepository.findById(eventId)
                .orElseThrow( () -> new EventNotFoundException(eventId));
    }

}
