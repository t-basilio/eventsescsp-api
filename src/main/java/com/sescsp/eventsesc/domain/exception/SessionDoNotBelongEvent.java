package com.sescsp.eventsesc.domain.exception;

public class SessionDoNotBelongEvent extends BusinessException{

    private static final long serialVersionUID = 1L;

    public SessionDoNotBelongEvent(String message) {
        super(message);
    }

    public SessionDoNotBelongEvent(Long sessionId, Long eventId){
        this(String.format("A sessão com código %d não pertence ao evento com código %d", sessionId, eventId));
    }
}
