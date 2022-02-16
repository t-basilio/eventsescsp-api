package com.sescsp.eventsesc.api.v1.assembler;

import com.sescsp.eventsesc.api.v1.model.input.TicketInput;
import com.sescsp.eventsesc.domain.model.Ticket;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketModelDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Ticket toDomainObject(TicketInput ticketInput) {
        return modelMapper.map(ticketInput, Ticket.class);
    }

    public void copyToDomainObject(TicketInput ticketInput, Ticket ticket){
        modelMapper.map(ticketInput, ticket);
    }
}
