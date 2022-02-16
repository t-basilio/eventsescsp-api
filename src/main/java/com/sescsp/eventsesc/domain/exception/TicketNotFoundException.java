package com.sescsp.eventsesc.domain.exception;

public class TicketNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public TicketNotFoundException(String message) {
        super(message);
    }

    public TicketNotFoundException(Long ticketId){
        this(String.format("Não existe um cadastro de ingresso com código %d", ticketId));
    }
}
