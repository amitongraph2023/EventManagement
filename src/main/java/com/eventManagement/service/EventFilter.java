package com.eventManagement.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventManagement.model.Event;

@Service
public class EventFilter {

	private Logger logger = LoggerFactory.getLogger(EventFilter.class.getName());

	@Autowired
	EntityManager entityManager;

	public List<Event> filterEvents(Long adminId, String eventCategory, String eventType, String endDate,
			String isDashboard) {
		List<Event> eventList = null;
		try {
			String query = "SELECT e FROM Event e where e.adminId = " + adminId;

			if (!eventType.equals("all")) {
				query = query + " AND e.eventType = :eventType";
			}
			if (!eventCategory.equals("all")) {
				query = query + " AND e.eventCategory = :eventCategory";
			}
			if (!endDate.equals("all")) {
				query = query + " AND e.endDate = :endDate";
			}

			TypedQuery<Event> eventQuery = entityManager.createQuery(query, Event.class);
			if (!eventType.equals("all")) {
				eventQuery.setParameter("eventType", eventType);
			}
			if (!eventCategory.equals("all")) {
				eventQuery.setParameter("eventCategory", eventCategory);
			}
			if (!endDate.equals("all")) {
				eventQuery.setParameter("endDate", endDate);
			}
			if (isDashboard.equals("true")) {
				eventQuery.setMaxResults(5);
			}

			eventList = eventQuery.getResultList();
			
		} catch (Exception ex) {
			logger.error("Exception caught while getting event List :" + ex.getMessage());
		}
		return eventList;
	}

}
