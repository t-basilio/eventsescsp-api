package com.sescsp.eventsesc.api.v1.model.input;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class TicketInput {

    @NotNull
    private CategoryInputId categoria;

    @NotNull
    private BigDecimal valor;
}
