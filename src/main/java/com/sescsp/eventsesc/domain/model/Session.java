package com.sescsp.eventsesc.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Session {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dateHour;

    private Boolean active = Boolean.TRUE;

    @Column(nullable = false)
    private Integer totalTickets;

    public boolean addTicket (){
        if (totalTickets > 0){
            totalTickets--;
            return true;
        }
        return false;
    }

    public void removeTicket (){
            totalTickets++;
    }

}
