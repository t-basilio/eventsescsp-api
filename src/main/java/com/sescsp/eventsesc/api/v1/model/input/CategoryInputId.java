package com.sescsp.eventsesc.api.v1.model.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
@Setter
public class CategoryInputId {

    @ApiModelProperty(value = "ID de uma categoria", example = "1", required = true, position = 1)
    @NotNull
    private Long id;
}
