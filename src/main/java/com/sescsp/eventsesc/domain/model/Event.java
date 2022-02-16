package com.sescsp.eventsesc.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Event {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    private Boolean active = Boolean.TRUE;

    @Column(nullable = false)
    private String local;


    @JsonIgnore
    @OneToMany(mappedBy = "event")
    private List<Session> sessions = new ArrayList<>();
}
