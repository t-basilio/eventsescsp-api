package com.sescsp.eventsesc.domain.model;

import com.sescsp.eventsesc.domain.enums.StatusCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Command {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id",nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "ticket_id",nullable = false)
    private Ticket ticket;

    @CreationTimestamp
    private OffsetDateTime date;

    @Enumerated(EnumType.STRING)
    private StatusCommand status = StatusCommand.CREATED;
}
