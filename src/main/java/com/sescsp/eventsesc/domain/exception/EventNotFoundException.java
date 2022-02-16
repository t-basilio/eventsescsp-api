package com.sescsp.eventsesc.domain.exception;

public class EventNotFoundException extends EntityNotFoundException{

    private static final long serialVersionUID = 1L;

    public EventNotFoundException(String message) {
        super(message);
    }

    public EventNotFoundException(Long eventId){
        this(String.format("Não existe um cadastro de evento com código %d", eventId));
    }
}
