package com.sescsp.eventsesc.api.v1.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EventInput {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    @NotNull
    private Boolean ativo;

    @NotBlank
    private String local;
}
