package com.eventManagement.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eventManagement.model.Event;

public interface EventRepository extends JpaRepository<Event, Long>{

	@Query("SELECT e FROM Event e WHERE LOWER(e.eventTitle) LIKE :eventTitle OR LOWER(e.location) LIKE :eventTitle")
	List<Event> findEventByTitle(@Param("eventTitle") String eventTitle);
	
	
	
}
