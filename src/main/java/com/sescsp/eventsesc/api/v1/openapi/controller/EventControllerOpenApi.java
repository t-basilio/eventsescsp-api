package com.sescsp.eventsesc.api.v1.openapi.controller;

import com.sescsp.eventsesc.api.v1.exceptionhandler.Problem;
import com.sescsp.eventsesc.api.v1.model.EventModel;
import com.sescsp.eventsesc.api.v1.model.input.EventInput;
import io.swagger.annotations.*;

import java.util.List;

@Api(tags = "Eventos")
public interface EventControllerOpenApi {

    @ApiOperation("Lista os eventos")
    List<EventModel> list( @ApiParam("Flag para eventos inativos") Boolean getInactive);

    @ApiOperation("Busca um evento por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Parâmetro inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Recurso não encontrado", response = Problem.class)
    })
    EventModel search( @ApiParam(value = "ID de um evento", example = "1") Long eventId);

    @ApiOperation("Cadastra um evento")
    EventModel add( @ApiParam(name = "payload", value = "Representação de um evento") EventInput eventInput);

    @ApiOperation("Atualiza um evento por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Parâmetro inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Recurso não encontrado", response = Problem.class)
    })
    EventModel update( @ApiParam(value = "ID de um evento", example = "1") Long eventId,
                    @ApiParam(name = "payload", value = "Representação de um evento com novos dados") EventInput eventInput);

}
