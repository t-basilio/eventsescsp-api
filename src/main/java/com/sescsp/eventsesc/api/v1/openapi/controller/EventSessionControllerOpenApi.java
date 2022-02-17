package com.sescsp.eventsesc.api.v1.openapi.controller;

import com.sescsp.eventsesc.api.v1.exceptionhandler.Problem;
import com.sescsp.eventsesc.api.v1.model.SessionModel;
import com.sescsp.eventsesc.api.v1.model.input.SessionInput;
import io.swagger.annotations.*;

import java.util.List;

@Api(tags = "Sessões")
public interface EventSessionControllerOpenApi {

    @ApiOperation("Lista as sessões de um evento")
    List<SessionModel> list(@ApiParam(value = "ID de um evento", example = "1") Long eventId,
                            @ApiParam("Flag para sessões inativas")Boolean getInactive);

    @ApiOperation("Busca a sessão de um evento pelo ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Parâmetro inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Recurso não encontrado", response = Problem.class)
    })
    SessionModel search ( @ApiParam(value = "ID de um evento", example = "1")Long eventId,
                          @ApiParam(value = "ID de uma sessão", example = "1")Long sessionId);

    @ApiResponses({
            @ApiResponse(code = 400, message = "Parâmetro inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Recurso não encontrado", response = Problem.class)
    })
    @ApiOperation("Cadastra uma sessão em um evento")
    SessionModel add ( @ApiParam(value = "ID de um evento", example = "1")Long eventId,
                       @ApiParam("Representação de uma sessão") SessionInput sessionInput);

    @ApiResponses({
            @ApiResponse(code = 400, message = "Parâmetro inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Recurso não encontrado", response = Problem.class)
    })
    @ApiOperation("Atualiza uma sessão em um evento por ID")
    SessionModel update ( @ApiParam(value = "ID de um evento", example = "1")Long eventId,
                          @ApiParam(value = "ID de uma sessão", example = "1")Long sessionId,
                          @ApiParam("Representação de uma sessão com novos dados") SessionInput sessionInput);
}
