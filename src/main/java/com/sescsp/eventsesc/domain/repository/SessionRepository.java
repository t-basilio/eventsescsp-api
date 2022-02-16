package com.sescsp.eventsesc.domain.repository;

import com.sescsp.eventsesc.domain.model.Event;
import com.sescsp.eventsesc.domain.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    List<Session> findAllByEvent(Event event);

    @Query("FROM Session s WHERE s.active = true AND s.totalTickets > 0 AND s.event = :event")
    List<Session> findAllActiveSessionWithTickets(Event event);

    @Query("FROM Session s WHERE s.event = :event AND s = :session")
    Session findSessionByEvent(Event event, Session session);
}
