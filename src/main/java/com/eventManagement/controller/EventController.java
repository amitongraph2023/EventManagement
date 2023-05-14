package com.eventManagement.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eventManagement.model.Event;
import com.eventManagement.model.EventType;
import com.eventManagement.service.EventService;



@RestController
public class EventController {

	
	@Autowired
	EventService eventService;
	
	@PostMapping("/createEvent")
	public ResponseEntity<String> createEvent(@RequestBody Event event) {
		try {
			eventService.createEvent(event);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}

		return ResponseEntity.ok().body("SuccessFully Event Created");
		
	}
	
	@PostMapping("/updateEvent/{id}")
	public ResponseEntity<String> updateEvent(@PathVariable("id") int id, @RequestBody Event updatedEvent) {
		try {
			eventService.updateEvent(id, updatedEvent);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}

		return ResponseEntity.ok().body("SuccessFully Event Updated");
	}
	
	@GetMapping("/getAllEvent")
	public ResponseEntity<List<Event>> getAllEvent() {
		List<Event> eventList = eventService.getAllEvent();
		if (eventList != null) {
			return ResponseEntity.ok().body(eventList);
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@GetMapping("/getAllEvent/{eventCategory}")
	public ResponseEntity<List<Event>> getEventByCategory(@PathVariable("eventCategory") String eventCategory) {
		List<Event> eventList = eventService.getEventByCategory(eventCategory);
		if (eventList != null) {
			return ResponseEntity.ok().body(eventList);
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@GetMapping("/getAllEvent/{eventType}")
	public ResponseEntity<List<Event>> getEventByType(@PathVariable("eventType") EventType eventType) {
		List<Event> eventList = eventService.getEventByType(eventType);
		if (eventList != null) {
			return ResponseEntity.ok().body(eventList);
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@GetMapping("/getAllEvent/{startDate}/{endDate}")
	public ResponseEntity<List<Event>> getEventByDate(@PathVariable("startDate") LocalDate startDate,
										@PathVariable("endDate") LocalDate endDate) {
		
	    List<Event> eventList = eventService.getEventsByDateRange(startDate, endDate);
		if (eventList != null) {
			return ResponseEntity.ok().body(eventList);
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@GetMapping("/searchEvent/{title}")
	public ResponseEntity<List<Event>> searchEvent(@PathVariable("title") String title) {
		List<Event> eventList = eventService.searchEvent(title);
		if (eventList != null) {
			return ResponseEntity.ok().body(eventList);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	


	
}
