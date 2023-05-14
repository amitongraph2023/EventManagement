package com.eventManagement.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventManagement.model.Event;
import com.eventManagement.model.EventType;

public interface EventRepository extends JpaRepository<Event, Integer>{

	List<Event> findEventByTitle(String eventTitle);
	
	List<Event> findEventByCategory(String eventCategory);
	
	List<Event> findEventByType(EventType eventType);
	
	List<Event> findByStartDateBetween(LocalDate startDate, LocalDate endDate);
}
