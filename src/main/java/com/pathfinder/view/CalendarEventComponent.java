/**
 * 
 */
package com.pathfinder.view;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pathfinder.model.EventModelSpec;
import com.pathfinder.model.ResourceModel;

/**
 * @author tim
 * 
 */
public class CalendarEventComponent implements CalendarEventComponentSpec {

	private static final Logger LOGGER = LogManager
			.getLogger(CalendarEventComponent.class);

	private EventModelSpec eventModel;
	private Date startDate;
	private Date endDate;

	private DateFormat dateFormat = new SimpleDateFormat("dd.MM.yy HH:mm");

	public CalendarEventComponent(EventModelSpec eventModel) {
		this.eventModel = eventModel;
		this.startDate = parseDate(eventModel.getStartDate() + " "
				+ eventModel.getStart());
		this.endDate = parseDate(eventModel.getEndDate() + " "
				+ eventModel.getEnd());
	}

	private Date parseDate(String dateString) {

		Date date = null;
		try {
			date = dateFormat.parse(dateString);
		} catch (ParseException e) {
			LOGGER.error("Calendar event date could not be parsed! Please check, if Data Loader delivers parseable format. Tried to parse: "
					+ dateString);
		}
		return date;
	}

	@Override
	public Date getStart() {
		return startDate;
	}

	@Override
	public Date getEnd() {
		return endDate;
	}

	@Override
	public boolean isAllDay() {
		return false;
	}

	@Override
	public String getCaption() {
		String caption = eventModel.getName();
		if (eventModel.getResources().size() > 0) {
			caption += " (";
			for (ResourceModel resource : eventModel.getResources()) {
				caption += " " + resource.getName();
			}
			caption += " )";
		}

		return caption;
	}

	@Override
	public EventModelSpec getEventModel() {
		return eventModel;
	}

	@Override
	public String getDescription() {
		return null;
	}

	@Override
	public String getStyleName() {
		return null;
	}

}
