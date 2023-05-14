package com.eventManagement.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.eventManagement.model.Event;
import com.eventManagement.model.EventType;

public interface EventService {

	public void createEvent(Event event);
	
	public void updateEvent(int id, Event updatedEvent) throws Exception;
	
	List<Event> getAllEvent();
	
	List<Event> getEventByCategory(String category);
	
	List<Event> getEventByType(EventType eventType);
	
	List<Event> getEventsByDateRange(LocalDate startDate, LocalDate endDate);
	
	List<Event> searchEvent(String searchText);
	
}
