package com.sescsp.eventsesc.api.v1.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Getter
@Setter
public class SessionInput {

    @ApiModelProperty(value = "Data e hora de uma sessão", example = "2022-02-16T17:23:55-03:00",
            required = true, position = 1)
    @NotNull
    private OffsetDateTime dataHora;

    @ApiModelProperty(example = "true", required = true, position = 2)
    @NotNull
    private Boolean ativo;

    @ApiModelProperty(value = "Total de ingressos de uma sessão", example = "10", required = true, position = 3)
    @NotNull
    private Integer totalIngressos;
}
