package com.sescsp.eventsesc.api.v1.model.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EventInput {

    @ApiModelProperty(example = "Show de viola", required = true, position = 1)
    @NotBlank
    private String nome;

    @ApiModelProperty(example = "Venha apreciar o melhor da viola sertaneja", required = true, position = 2)
    @NotBlank
    private String descricao;

    @ApiModelProperty(example = "true", required = true, position = 3)
    @NotNull
    private Boolean ativo;

    @ApiModelProperty(example = "Sesc Pinheiros", required = true, position = 4)
    @NotBlank
    private String local;
}
