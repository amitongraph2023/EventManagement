package com.eventManagement.service;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.eventManagement.dto.EventDto;
import com.eventManagement.model.Event;
import com.eventManagement.model.EventType;
import com.eventManagement.repository.EventRepository;

@Service
public class EventServiceImpl implements EventService {

	private Logger logger = LoggerFactory.getLogger(EventServiceImpl.class.getName());

	public static String uploadImageDir = System.getProperty("user.dir") + "/src/main/resources/static/img";;

	@Autowired
	EventRepository eventRepository;

	@Autowired
	EventFilter eventFilter;

	@Override
	public String createEvent(MultipartFile[] files, EventDto eventDto) {
		// need a method to validate eventDto
		try {
			Event event = new Event();
			event.setAddress(eventDto.getAddress());
			event.setEndDate(eventDto.getEndDate());
			event.setEndTime(eventDto.getEndTime());
			event.setEventCategory(eventDto.getEventCategory());
			event.setEventDetails(eventDto.getEventDetails());
			event.setEventTitle(eventDto.getEventTitle());
			event.setEventType(eventDto.getEventType());
			event.setLink(eventDto.getLink());
			event.setLocation(eventDto.getLocation());
			event.setStartDate(eventDto.getStartDate());
			event.setStartDate(eventDto.getStartDate());
			event.setStartTime(eventDto.getStartDate());
			event.setUserType(eventDto.getUserType());

			List<String> imageNames = saveFileInSystem(files);

			StringBuilder sb = new StringBuilder();
			for (String s : imageNames) {
				sb.append(s).append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
			
			event.setImageName(sb.toString());
			String imgName = imageNames.stream().collect(Collectors.joining());
			event.setImageName(imgName);

			eventRepository.save(event);
		} catch (Exception ex) {
			logger.error("Error occurred while saving event");
			return "Error got while saving event";
		}

		return "Successfully saved event";
	}

	@Override
	public void updateEvent(int id, EventDto eventDto, MultipartFile[] files) throws Exception {

		try {

			Event event = eventRepository.findById(id).get();
			if (event == null) {
				throw new Exception("Event not found");
			}
			event.setAdminId(Long.parseLong(eventDto.getAdminId()));
			event.setAddress(eventDto.getAddress());
			event.setEndDate(eventDto.getEndDate());
			event.setEndTime(eventDto.getEndTime());
			event.setEventCategory(eventDto.getEventCategory());
			event.setEventDetails(eventDto.getEventDetails());
			event.setEventTitle(eventDto.getEventTitle());
			event.setEventType(eventDto.getEventType());
			event.setLink(eventDto.getLink());

			event.setLocation(eventDto.getLocation());
			event.setStartDate(eventDto.getStartDate());
			event.setStartTime(eventDto.getStartDate());
			event.setUserType(eventDto.getUserType());

			List<String> imageNames = saveFileInSystem(files);

			StringBuilder sb = new StringBuilder();
			for (String s : imageNames) {
				sb.append(s).append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
			
			event.setImageName(sb.toString());

			eventRepository.save(event);

		} catch (Exception e) {
			logger.error("Error occurred while saving event");
		}

	}

	@Override
	public List<Event> getAllEvent(Long adminId, String eventCategory, String eventType, String endDate,
			String isDashboard) {

		List<Event> eventList = eventFilter.filterEvents(adminId, eventCategory, eventType, endDate, isDashboard);

		return eventList;
	}

	@Override
	public List<Event> searchEvent(String title) {
		List<Event> eventList = new ArrayList<>();
		title = "%" + title.toLowerCase() + "%";
		try {
			eventList = eventRepository.findEventByTitle(title);

		} catch (Exception e) {
			logger.error("Error occurred while fetching event");
		}

		return eventList;
	}

	public List<String> saveFileInSystem(MultipartFile[] files) throws Exception {

		List<String> fileNames = new ArrayList<>();
		String UPLOAD_DIR = "event\\images\\";
		for (MultipartFile file : files) {
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			try {
				Path path = Paths.get(UPLOAD_DIR + fileName);
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
				fileNames.add(UPLOAD_DIR + fileName);
			} catch (IOException e) {
				throw new Exception("Exception got while saving files : " + e.getMessage());
			} catch (UncheckedIOException e) {
				logger.error("An UncheckedIOException occurred: " + e.getMessage());
			}
		}
		return fileNames;

	}
}
