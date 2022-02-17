package com.sescsp.eventsesc.api.v1.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("Evento")
@Setter
@Getter
public class EventModel {

    @ApiModelProperty(value = "ID de um evento", example = "1", position = 1)
    private Long id;

    @ApiModelProperty(example = "Teatro infantil", position = 2)
    private String nome;

    @ApiModelProperty(example = "Venha assistir ao espetaculo com seus filhos", position = 3)
    private String descricao;
}
