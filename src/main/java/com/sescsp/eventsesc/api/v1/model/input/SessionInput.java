package com.sescsp.eventsesc.api.v1.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Getter
@Setter
public class SessionInput {

    @NotNull
    private OffsetDateTime dataHora;

    @NotNull
    private Boolean ativo;

    @NotNull
    private Integer totalIngressos;
}
