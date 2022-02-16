package com.sescsp.eventsesc.domain.repository;

import com.sescsp.eventsesc.domain.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT DISTINCT e FROM Event e LEFT JOIN FETCH e.sessions s WHERE e.active = true AND s.totalTickets > 0")
    List<Event> findAllActiveEventWithTickets();
}
