package com.pathfinder.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vaadin.ui.components.calendar.event.CalendarEvent;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventModel implements CalendarEvent {

	private static final Logger LOGGER = LogManager.getLogger(EventModel.class);

	private String name;
	@JsonProperty("start")
	private String startString;
	@JsonProperty("end")
	private String endString;
	private List<ResourceModel> resources;
	private Date start;
	private Date end;
	private String description;
	private String styleName;
	private boolean isAllDay;
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

	public EventModel() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartString() {
		return startString;
	}

	public void setStartString(String start) {
		try {
			this.start = dateFormat.parse(start);
		} catch (ParseException pe) {
			LOGGER.error("Event's start time couldn't be parsed to date instance. Check data interface!");
		}
		this.startString = start;
	}

	public String getEndString() {
		return endString;
	}

	public void setEndString(String end) {
		try {
			this.end = dateFormat.parse(end);
		} catch (ParseException pe) {
			LOGGER.error("Event's end time couldn't be parsed to date instance. Check data interface!");
		}
		this.endString = end;
	}

	public List<ResourceModel> getResources() {
		return resources;
	}

	public void setResources(List<ResourceModel> resources) {
		this.resources = resources;
	}

	@Override
	public String toString() {
		return "EventModel [name=" + name + ", start=" + startString + ", end="
				+ endString + ", resources=" + resources + "]";
	}

	@Override
	public String getCaption() {
		return name;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public String getStyleName() {
		return this.styleName;
	}

	@Override
	public boolean isAllDay() {
		return isAllDay;
	}

	@Override
	public Date getStart() {
		return this.start;
	}

	@Override
	public Date getEnd() {
		return this.end;
	}

}