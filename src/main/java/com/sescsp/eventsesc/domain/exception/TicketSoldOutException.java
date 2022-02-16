package com.sescsp.eventsesc.domain.exception;

public class TicketSoldOutException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public TicketSoldOutException(String message) {
        super(message);
    }

    public TicketSoldOutException(Long sessaoId, Long eventId){
        this(String.format("Ingressos esgostados para a sessão de código %d do evento de código %d",
                sessaoId, eventId));
    }
}
