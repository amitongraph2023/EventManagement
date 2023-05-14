package com.eventManagement.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventManagement.model.Event;
import com.eventManagement.model.EventType;
import com.eventManagement.repository.EventRepository;

@Service
public class EventServiceImpl implements EventService{
	
	private Logger log = LoggerFactory.getLogger(EventServiceImpl.class.getName());
	
	public static String uploadImageDir = System.getProperty("user.dir")
			+ "/src/main/resources/static/img";;
			
	@Autowired
	EventRepository eventRepository;
	
	@Override
	public void createEvent(Event event) {
		
		try {
			eventRepository.save(event);
			
		} catch(Exception e) {
			log.error("Error occurred while saving event");
		}
	}

	@Override
	public void updateEvent(int id, Event updatedEvent) throws Exception {
		Event event = eventRepository.findById(id).get();
		if (event == null) {
			throw new Exception("Event not found");
		}
		
		event = new Event(updatedEvent.getEventTitle(), updatedEvent.getStartDate(), updatedEvent.getEndDate(),
				updatedEvent.getStartTime(), updatedEvent.getEndTime(), updatedEvent.getEventCategory(), 
				updatedEvent.getEventType(), updatedEvent.getUserType(), updatedEvent.getLocation(), updatedEvent.getAddress(),
				updatedEvent.getLink(), updatedEvent.getEventDetails(), updatedEvent.getImageName());
		
		try {
			eventRepository.save(event);
			
		} catch(Exception e) {
			log.error("Error occurred while saving event");
		}
		
	}

	@Override
	public List<Event> getAllEvent() {
		List<Event> eventList = new ArrayList<>();		
		try {
			eventList = eventRepository.findAll();
			
		} catch(Exception e) {
			log.error("Error occurred while saving event");
		}
		return eventList;
		
	}

	@Override
	public List<Event> searchEvent(String title) {
		List<Event> eventList = new ArrayList<>();
		try {
			eventList = eventRepository.findEventByTitle(title);
			
		} catch (Exception e) {
			log.error("Error occurred while fetching event");
		}
		
		return eventList;
	}

	@Override
	public List<Event> getEventByCategory(String category) {
		List<Event> eventList = new ArrayList<>();		
		try {
			eventList = eventRepository.findEventByCategory(category);
			
		} catch(Exception e) {
			log.error("Error occurred while finding event by category");
		}
		return eventList;
	}

	@Override
	public List<Event> getEventByType(EventType eventType) {
		List<Event> eventList = new ArrayList<>();		
		try {
			eventList = eventRepository.findEventByType(eventType);
			
		} catch(Exception e) {
			log.error("Error occurred while finding event by type");
		}
		return eventList;
	}

	@Override
	public List<Event> getEventsByDateRange(LocalDate startDate, LocalDate endDate) {
		List<Event> eventList = new ArrayList<>();	
		try {
			eventRepository.findByStartDateBetween(startDate, endDate);
			
		} catch(Exception e) {
			log.error("Error occurred while finding event by date");
		}
		return eventList;
				
	}

}
