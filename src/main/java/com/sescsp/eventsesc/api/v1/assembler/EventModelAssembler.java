package com.sescsp.eventsesc.api.v1.assembler;

import com.sescsp.eventsesc.api.v1.model.EventModel;
import com.sescsp.eventsesc.domain.model.Event;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public EventModel toModel (Event event){
         return modelMapper.map(event, EventModel.class);
    }

    public List<EventModel> toCollectionModel(List<Event> events) {
        return events.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
