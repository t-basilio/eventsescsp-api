package com.sescsp.eventsesc.domain.exception;

public class SessionNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public SessionNotFoundException(String message) {
        super(message);
    }

    public SessionNotFoundException(Long sessionId){
        this(String.format("Não existe um cadastro de sessão com código %d", sessionId));
    }
}
