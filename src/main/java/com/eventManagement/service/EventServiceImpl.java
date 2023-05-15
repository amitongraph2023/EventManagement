package com.eventManagement.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.eventManagement.model.Event;
import com.eventManagement.model.EventType;
import com.eventManagement.repository.EventRepository;
import com.eventManagement.util.FileUploadUtil;

@Service
public class EventServiceImpl implements EventService{
	
	private Logger log = LoggerFactory.getLogger(EventServiceImpl.class.getName());
	
	public static String fileStorageLocation = System.getProperty("user.dir")
			+ "/src/main/resources/static/img";;
			
	@Autowired
	EventRepository eventRepository;
	
	@Override
	public void createEvent(Event event) throws Exception {		
		List<MultipartFile> images = event.getImages();
	    if (images == null || images.size() == 0) {
	        throw new Exception("At least one image is required.");
	    }
	    if (images.size() > 5) {
	        throw new Exception("Up to five images can be uploaded.");
	    }
	    
	    for (MultipartFile image : images) {
	        String imageFormat = image.getContentType();
	        if (!imageFormat.equals("image/jpeg") && !imageFormat.equals("image/png") && !imageFormat.equals("image/jpg")) {
	            throw new Exception("Invalid image format. Only JPG, JPEG, and PNG are supported.");
	        }
	        
	        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
	        String uploadDir = fileStorageLocation;
	              
	        try {
	            FileUploadUtil.saveFile(uploadDir, fileName, image);
	            if (event.getImageNames() == null) {
	                event.setImageNames(new ArrayList<>());
	            }
	            event.getImageNames().add(fileName);
	        } catch(Exception e) {
	            log.error("Error occurred while saving image");
	        }
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
				updatedEvent.getLink(), updatedEvent.getEventDetails(), updatedEvent.getImageNames());
		
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
