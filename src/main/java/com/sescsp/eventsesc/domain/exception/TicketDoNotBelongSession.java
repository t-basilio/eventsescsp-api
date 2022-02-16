package com.sescsp.eventsesc.domain.exception;

public class TicketDoNotBelongSession extends BusinessException{

    private static final long serialVersionUID = 1L;

    public TicketDoNotBelongSession(String message) {
        super(message);
    }

    public TicketDoNotBelongSession(Long ticketId, Long sessionId){
        this(String.format("O ingresso com código %d não pertence a sessão com código %d", ticketId, sessionId));
    }
}
