package com.pathfinder.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.pathfinder.model.EventModel;
import com.pathfinder.model.EventModelSpec;
import com.pathfinder.model.ResourceModel;

public class CalendarEventComopnentTest {

	private CalendarEventComponentSpec component;
	private EventModelSpec eventModel;

	@Before
	public void initialize() {
		eventModel = new EventModel();
		eventModel.setStartDate("01.01.14");
		eventModel.setStart("12:00");
		eventModel.setEndDate("01.01.14");
		eventModel.setEnd("14:00");
		eventModel.setName("EventName");
		ResourceModel resourceModel = new ResourceModel();
		List<ResourceModel> list = new ArrayList<>();
		list.add(resourceModel);
		eventModel.setResources(list);
		component = new CalendarEventComponent(eventModel);
	}

	@Test
	public void getEventModelTest() {
		assertEquals(eventModel, component.getEventModel());
	}

	@Test
	public void getStartTest() throws ParseException {
		DateFormat df = new SimpleDateFormat("dd.MM.yy HH:mm");
		assertEquals(df.parse("01.01.14 12:00"), component.getStart());
	}

	@Test
	public void getEndTest() throws ParseException {
		DateFormat df = new SimpleDateFormat("dd.MM.yy HH:mm");
		assertEquals(df.parse("01.01.14 14:00"), component.getEnd());
	}

	@Test
	public void getCaptionTest() {
		assertEquals("EventName (  )", component.getCaption());
	}

	@Test
	public void getDescriptionTest() {
		assertNull(component.getDescription());
	}

	@Test
	public void getStyleName() {
		assertNull(component.getStyleName());
	}

	@Test
	public void isAllDay() {
		assertFalse(component.isAllDay());
	}

}
