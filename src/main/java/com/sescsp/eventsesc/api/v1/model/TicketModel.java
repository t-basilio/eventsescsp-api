package com.sescsp.eventsesc.api.v1.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@ApiModel("Ingresso")
@Setter
@Getter
public class TicketModel {

    @ApiModelProperty(value = "ID de um ingresso", example = "1", position = 1)
    private Long id;
    @ApiModelProperty(value = "Categoria de um ingresso", example = "Esporte", position = 2)
    private String nomeCategoria;

    @ApiModelProperty(value = "Valor de um ingresso", example = "12", position = 3)
    private BigDecimal valor;
}
