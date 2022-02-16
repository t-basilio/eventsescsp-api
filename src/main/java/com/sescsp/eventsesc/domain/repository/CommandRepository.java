package com.sescsp.eventsesc.domain.repository;

import com.sescsp.eventsesc.domain.model.Command;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandRepository extends JpaRepository<Command, Long> {
}
