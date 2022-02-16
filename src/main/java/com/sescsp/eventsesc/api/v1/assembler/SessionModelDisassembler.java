package com.sescsp.eventsesc.api.v1.assembler;

import com.sescsp.eventsesc.api.v1.model.input.SessionInput;
import com.sescsp.eventsesc.domain.model.Session;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionModelDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Session toDomainObject(SessionInput sessionInput) {
        return modelMapper.map(sessionInput, Session.class);
    }

    public void copyToDomainObject(SessionInput sessionInput, Session session){
        modelMapper.map(sessionInput, session);
    }
}
