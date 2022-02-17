package com.sescsp.eventsesc.api.v1.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@ApiModel("Sessão")
@Setter
@Getter
public class SessionModel {

    @ApiModelProperty(value = "ID de uma sessão", example = "1", position = 1)
    private Long id;

    @ApiModelProperty(value = "Data e hora de uma sessão", example = "2022-03-21T11:30:00Z", position = 2)
    private OffsetDateTime dataHora;
}
