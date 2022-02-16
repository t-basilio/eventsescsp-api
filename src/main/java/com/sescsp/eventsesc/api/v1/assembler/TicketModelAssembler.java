package com.sescsp.eventsesc.api.v1.assembler;

import com.sescsp.eventsesc.api.v1.model.TicketModel;
import com.sescsp.eventsesc.domain.model.Ticket;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TicketModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public TicketModel toModel (Ticket ticket){
        return modelMapper.map(ticket, TicketModel.class);
    }

    public List<TicketModel> toCollectionModel(List<Ticket> tickets) {
        return tickets.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
