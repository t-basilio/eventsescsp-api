package com.sescsp.eventsesc.api.v1.controller;

import com.sescsp.eventsesc.api.v1.assembler.EventModelAssembler;
import com.sescsp.eventsesc.api.v1.assembler.EventModelDisassembler;
import com.sescsp.eventsesc.api.v1.model.EventModel;
import com.sescsp.eventsesc.api.v1.model.input.EventInput;
import com.sescsp.eventsesc.api.v1.openapi.controller.EventControllerOpenApi;
import com.sescsp.eventsesc.domain.model.Event;
import com.sescsp.eventsesc.domain.repository.EventRepository;
import com.sescsp.eventsesc.domain.service.EventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/events", produces = MediaType.APPLICATION_JSON_VALUE)
public class EventController implements EventControllerOpenApi {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventModelAssembler eventModelAssembler;

    @Autowired
    private EventModelDisassembler eventModelDisasembler;

    @Override
    @GetMapping
    public List<EventModel> list(@RequestParam(required = false, defaultValue = "false") Boolean getInactive) {

        if (getInactive){
            return eventModelAssembler.toCollectionModel(eventRepository.findAll());
        } else{
            return eventModelAssembler.toCollectionModel(eventRepository.findAllActiveEventWithTickets());
        }
    }

    @Override
    @GetMapping("/{eventId}")
    public EventModel search(@PathVariable Long eventId) {
        return eventModelAssembler.toModel(eventService.searchOrFail(eventId));
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public EventModel add ( @RequestBody @Valid EventInput eventInput) {
        Event event = eventModelDisasembler.toDomainObject(eventInput);
        event = eventService.save(event);
        return eventModelAssembler.toModel(event);
    }

    @Override
    @PutMapping("/{eventId}")
    public EventModel update (@PathVariable Long eventId,
                                  @RequestBody @Valid EventInput eventInput) {
        Event currentEvent = eventService.searchOrFail(eventId);

        eventModelDisasembler.copyToDomainObject(eventInput, currentEvent);
        currentEvent = eventService.save(currentEvent);
        return eventModelAssembler.toModel(currentEvent);
    }

}
