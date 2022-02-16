package com.sescsp.eventsesc.domain.exception;

public class CategoryNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public CategoryNotFoundException(String message) {
        super(message);
    }

    public CategoryNotFoundException(Long categoryId){
        this(String.format("Não existe um cadastro de categoria com código %d", categoryId));
    }
}
