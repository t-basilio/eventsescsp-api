package com.sescsp.eventsesc.api.v1.assembler;

import com.sescsp.eventsesc.api.v1.model.input.EventInput;
import com.sescsp.eventsesc.domain.model.Event;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventModelDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Event toDomainObject(EventInput eventInput) {

        return modelMapper.map(eventInput, Event.class);
    }

    public void copyToDomainObject(EventInput eventInput, Event event){
        modelMapper.map(eventInput, event);
    }
}
