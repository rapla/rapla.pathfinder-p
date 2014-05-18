package com.pathfinder.view;

import static de.vksi.c4j.Condition.ignored;
import static de.vksi.c4j.Condition.postCondition;
import static de.vksi.c4j.Condition.result;

import java.util.Date;

import com.pathfinder.model.EventModelSpec;

import de.vksi.c4j.Target;

public class CalendarEventComponentSpecContract implements
		CalendarEventComponentSpec {

	@Target
	private CalendarEventComponentSpec target;

	@Override
	public EventModelSpec getEventModel() {
		if (postCondition()) {
			EventModelSpec result = result();
			assert result != null : "result not null";
		}
		return ignored();
	}

	@Override
	public Date getStart() {
		if (postCondition()) {
			Date result = result();
			assert result != null : "result not null";
		}
		return ignored();
	}

	@Override
	public Date getEnd() {
		if (postCondition()) {
			Date result = result();
			assert result != null : "result not null";
		}
		return ignored();
	}

	@Override
	public String getCaption() {
		if (postCondition()) {
			String result = result();
			assert result != null : "result not null";
		}
		return ignored();
	}

	@Override
	public String getDescription() {
		return ignored();
	}

	@Override
	public String getStyleName() {
		return ignored();
	}

	@Override
	public boolean isAllDay() {
		return ignored();
	}

}
