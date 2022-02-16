package com.sescsp.eventsesc.domain.enums;

import java.util.Arrays;
import java.util.List;

public enum StatusCommand {

    CREATED("Criado"),
    CONFIRMED("Confirmado", CREATED),
    CANCELED("Cancelado", CREATED);

    private String description;
    private List<StatusCommand> beforeStatus;

    StatusCommand(String description, StatusCommand... beforeStatus){
        this.description = description;
        this.beforeStatus = Arrays.asList(beforeStatus);
    }

    public String getDescription(){
        return this.description;
    }

    public boolean cannotUpdateFor(StatusCommand newStatus) {
        return !newStatus.beforeStatus.contains(this);
    }

    public boolean canUpdateFor(StatusCommand newStatus) {
        return !cannotUpdateFor(newStatus);
    }
}
