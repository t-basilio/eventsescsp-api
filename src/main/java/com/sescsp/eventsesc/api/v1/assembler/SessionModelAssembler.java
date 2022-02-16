package com.sescsp.eventsesc.api.v1.assembler;

import com.sescsp.eventsesc.api.v1.model.SessionModel;
import com.sescsp.eventsesc.domain.model.Session;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SessionModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public SessionModel toModel (Session session){
        return modelMapper.map(session, SessionModel.class);
    }

    public List<SessionModel> toCollectionModel(List<Session> sessions) {
        return sessions.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

}
