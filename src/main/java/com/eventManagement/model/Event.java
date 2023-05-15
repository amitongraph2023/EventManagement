package com.eventManagement.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "EVENT")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer eventId;
	
	private String eventTitle;
	private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
	private String eventCategory;
	private EventType eventType;
	private UserType userType;
	private String location;
	private String address;
	private String link;
	private String eventDetails;
	private List<String> imageNames;
	private List<MultipartFile> images;
	
	public Integer getEventId() {
		return eventId;
	}
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
	public String getEventTitle() {
		return eventTitle;
	}
	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public LocalTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
	public String getEventCategory() {
		return eventCategory;
	}
	public void setEventCategory(String eventCategory) {
		this.eventCategory = eventCategory;
	}
	
	public EventType getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = EventType.fromValue(eventType);
	}
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = UserType.fromValue(userType);
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getEventDetails() {
		return eventDetails;
	}
	public void setEventDetails(String eventDetails) {
		this.eventDetails = eventDetails;
	}

	public List<String> getImageNames() {
		return imageNames;
	}
	public void setImageNames(List<String> imageNames) {
		this.imageNames = imageNames;
	}
	
	
	public List<MultipartFile> getImages() {
		return images;
	}
	public void setImages(List<MultipartFile> images) {
		this.images = images;
	}
	public Event() {
		super();
	}
	
	
	public Event(String eventTitle, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime,
			String eventCategory, EventType eventType, UserType userType, String location, String address, String link,
			String eventDetails, List<String> imageNames) {
		super();
		this.eventTitle = eventTitle;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.eventCategory = eventCategory;
		this.eventType = eventType;
		this.userType = userType;
		this.location = location;
		this.address = address;
		this.link = link;
		this.eventDetails = eventDetails;
		this.imageNames = imageNames;
	}
	
	
	
	
	
}
