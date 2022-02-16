package com.sescsp.eventsesc.api.v1.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class TicketModel {

    private Long id;
    private String nomeCategoria;
    private BigDecimal valor;
}
