package com.pathfinder.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pathfinder.util.translation.TranslationKeys;
import com.pathfinder.util.translation.Translator;
import com.pathfinder.util.translation.TranslatorSpec;
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
	private final GregorianCalendar parsedDate = new GregorianCalendar();
	private final GregorianCalendar todayDate = new GregorianCalendar();
	private final TranslatorSpec translator = Translator.getInstance();

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
		this.start = parseAndCreateDate(start);
		this.startString = start;
	}

	public String getEndString() {
		return endString;
	}

	public void setEndString(String end) {
		this.end = parseAndCreateDate(end);
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
		return name + buildRoomString(resources);
	}

	private String buildRoomString(List<ResourceModel> rooms) {
		String returnString = "";
		if (rooms != null && rooms.size() > 0) {
			boolean firstEntry = true;
			StringBuilder sb = new StringBuilder(" ("
					+ translator.translate(TranslationKeys.ROOM));
			for (ResourceModel room : rooms) {
				if ("room".equals(room.getType())) {
					firstEntry = false;
					sb.append(" " + room.getName());
				}
			}
			sb.append(")");
			if (!firstEntry)
				returnString = sb.toString();
		}
		return returnString;
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

	private Date parseAndCreateDate(String dateString) {
		Date returnDate = null;
		try {
			parsedDate.setTime(dateFormat.parse(dateString));
			todayDate.setTime(new Date());
			int year = todayDate.get(Calendar.YEAR);
			int month = todayDate.get(Calendar.MONTH);
			int date = todayDate.get(Calendar.DATE);
			parsedDate.set(year, month, date);

			returnDate = parsedDate.getTime();
		} catch (ParseException pe) {
			LOGGER.error("Event's time couldn't be parsed to date instance. Check data formatter of this class!");
		}

		return returnDate;
	}

}