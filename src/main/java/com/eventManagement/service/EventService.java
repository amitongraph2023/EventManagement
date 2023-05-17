package com.eventManagement.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.eventManagement.dto.EventDto;
import com.eventManagement.model.Event;

public interface EventService {

	public String createEvent(MultipartFile[] files, EventDto event);
	
	public void updateEvent(int id, EventDto updatedEvent, MultipartFile[] files) throws Exception;
	
	List<Event> getAllEvent(Long adminId,String eventCategory,String eventType,String endDate, String isDashboard);
	
	List<Event> searchEvent(String searchText);
	
}
