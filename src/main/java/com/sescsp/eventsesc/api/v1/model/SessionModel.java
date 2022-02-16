package com.sescsp.eventsesc.api.v1.model;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Setter
@Getter
public class SessionModel {

    private Long id;
    private OffsetDateTime dataHora;
}
