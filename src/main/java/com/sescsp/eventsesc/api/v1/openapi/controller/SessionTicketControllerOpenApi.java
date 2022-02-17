package com.sescsp.eventsesc.api.v1.openapi.controller;

import com.sescsp.eventsesc.api.v1.exceptionhandler.Problem;
import com.sescsp.eventsesc.api.v1.model.TicketModel;
import com.sescsp.eventsesc.api.v1.model.input.TicketInput;
import io.swagger.annotations.*;

import java.util.List;

@Api(tags = "Ingressos")
public interface SessionTicketControllerOpenApi {

    @ApiOperation("Lista os ingressos de uma sessão")
    List<TicketModel> list(@ApiParam(value = "ID de um evento", example = "1") Long eventId,
                           @ApiParam(value = "ID de uma sessão", example = "1")Long sessionId);

    @ApiOperation("Busca o ingresso de uma sessão pelo ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Parâmetro inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Recurso não encontrado", response = Problem.class)
    })
    TicketModel search ( @ApiParam(value = "ID de um evento", example = "1")Long eventId,
                         @ApiParam(value = "ID de uma sessão", example = "1")Long sessionId,
                         @ApiParam(value = "ID de um ingresso", example = "1")Long ticketId);

    @ApiOperation("Cadastra um ingresso em uma sessão")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Parâmetro inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Recurso não encontrado", response = Problem.class)
    })
    TicketModel add ( @ApiParam(value = "ID de um evento", example = "1")Long eventId,
                      @ApiParam(value = "ID de uma sessão", example = "1")Long sessionId,
                      @ApiParam("Representação de um ingresso")TicketInput ticketInput);
}
