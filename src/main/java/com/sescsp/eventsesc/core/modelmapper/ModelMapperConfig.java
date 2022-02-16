package com.sescsp.eventsesc.core.modelmapper;

import com.sescsp.eventsesc.api.v1.model.EventModel;
import com.sescsp.eventsesc.api.v1.model.SessionModel;
import com.sescsp.eventsesc.api.v1.model.TicketModel;
import com.sescsp.eventsesc.api.v1.model.input.EventInput;
import com.sescsp.eventsesc.api.v1.model.input.SessionInput;
import com.sescsp.eventsesc.api.v1.model.input.TicketInput;
import com.sescsp.eventsesc.domain.model.Event;
import com.sescsp.eventsesc.domain.model.Session;
import com.sescsp.eventsesc.domain.model.Ticket;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper =  new ModelMapper();

        modelMapper.createTypeMap(Event.class, EventModel.class)
                .addMapping(Event::getTitle, EventModel::setNome)
                .addMapping(Event::getDescription, EventModel::setDescricao);

        modelMapper.createTypeMap(Session.class, SessionModel.class)
                .addMapping(Session::getDateHour, SessionModel::setDataHora);

        modelMapper.createTypeMap(Ticket.class, TicketModel.class)
                .addMapping(Ticket -> Ticket.getCategory().getName(), TicketModel::setNomeCategoria)
                .addMapping(Ticket::getPrice, TicketModel::setValor);

        modelMapper.createTypeMap(EventInput.class, Event.class)
                .addMapping(EventInput::getNome,Event::setTitle)
                .addMapping(EventInput::getDescricao, Event::setDescription)
                .addMapping(EventInput::getAtivo, Event::setActive);

        modelMapper.createTypeMap(SessionInput.class, Session.class)
                .addMapping(SessionInput::getDataHora, Session::setDateHour)
                .addMapping(SessionInput::getAtivo, Session::setActive)
                .addMapping(SessionInput::getTotalIngressos, Session::setTotalTickets);

        modelMapper.createTypeMap(TicketInput.class, Ticket.class)
                .addMapping(TicketInput::getCategoria, Ticket::setCategory)
                .addMapping(TicketInput::getValor, Ticket::setPrice);

        return modelMapper;
    }
}
