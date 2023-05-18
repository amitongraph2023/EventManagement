package com.eventManagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Event {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer eventId;
	
	@Column
	private Long adminId;

	@Column
	private String eventTitle;
	
	@Column
	private String startDate;
	
	@Column
    private String endDate;
	
	@Column
    private String startTime;
	
	@Column
    private String endTime;
	
	@Column
	private String eventCategory;
	
	@Column
	private String eventType;
	
	@Column
	private String userType;
	
	@Column
	private String location;
	
	@Column
	private String address;
	
	@Column
	private String link;
	
	@Column
	private String eventDetails;
	
	@Column
	private String imageName;
	
	public Integer getEventId() {
		return eventId;
	}
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
	public Long getAdminId() {
		return adminId;
	}
	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}
	public String getEventTitle() {
		return eventTitle;
	}
	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getEventCategory() {
		return eventCategory;
	}
	public void setEventCategory(String eventCategory) {
		this.eventCategory = eventCategory;
	}
	
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
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

	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
		
	public Event() {
		super();
	}
	
	public Event(Long adminId, String eventTitle, String startDate, String endDate, String startTime, String endTime,
			String eventCategory, String eventType, String userType, String location, String address, String link,
			String eventDetails, String imageName) {
		super();
		this.adminId = adminId;
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
		this.imageName = imageName;
	}
	
	
	
}
