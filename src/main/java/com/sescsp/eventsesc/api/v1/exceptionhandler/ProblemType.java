package com.sescsp.eventsesc.api.v1.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    INVALID_MESSAGE("/invalid-message","Mensagem incompreensivel"),
    RESOURCE_NOT_FOUND("/resource-not-found", "Recurso não encontrado"),
    ENTITY_IN_USE("/entity-in-use", "Entidade em uso"),
    BUSINESS_ERROR("/business-error", "Violação de regra de negócio"),
    INVALID_PARAM("/invalid-param", "Parâmetro inválido"),
    SYSTEM_ERROR("/system-error", "Erro de sistema"),
    INVALID_DATA("/invalid-data", "Dados inválidos");

    private  String title;
    private String uri;

    ProblemType(String path, String title){
        this.uri = "https://sescsp.com.br" + path;
        this.title = title;
    }
}
