package com.sescsp.eventsesc.api.v1.model.input;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class TicketInput {

    @NotNull
    private CategoryInputId categoria;

    @ApiModelProperty(example = "15", required = true, position = 2)
    @NotNull
    private BigDecimal valor;
}
