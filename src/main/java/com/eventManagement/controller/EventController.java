package com.eventManagement.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eventManagement.dto.EventDto;
import com.eventManagement.model.Event;
import com.eventManagement.service.EventService;

@RestController
@RequestMapping("/event")
public class EventController {

	@Autowired
	EventService eventService;

	@PostMapping("/createEvent")
	public ResponseEntity<String> createEvent(@RequestPart("files") MultipartFile[] files,
			@RequestPart("data") EventDto event) {
		String response = "";
		try {
			response = eventService.createEvent(files, event);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.ok().body(response);
	}

	@PostMapping("/updateEvent/{id}")
	public ResponseEntity<String> updateEvent(@PathVariable("id") int id, @RequestPart EventDto updatedEvent,
			@RequestPart("files") MultipartFile[] files) {
		try {
			eventService.updateEvent(id, updatedEvent, files);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.ok().body("SuccessFully Event Updated");
	}

	@GetMapping("/getAllEvent/{adminId}")
	public ResponseEntity<List<Event>> getAllEvent(@PathVariable("adminId") Long adminId,
			@RequestParam(value = "eventCategory", defaultValue = "all", required = false) String eventCategory,
			@RequestParam(value = "eventType", defaultValue = "all", required = false) String eventType,
			@RequestParam(value = "endDate", defaultValue = "all", required = false) String endDate,
			@RequestParam(value = "isDashboard", defaultValue = "false", required = false) String isDashboard) {

		List<Event> eventList = eventService.getAllEvent(adminId, eventCategory, eventType, endDate, isDashboard);
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
