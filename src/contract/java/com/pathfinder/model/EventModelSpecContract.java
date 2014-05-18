package com.pathfinder.model;

import static de.vksi.c4j.Condition.ignored;
import static de.vksi.c4j.Condition.postCondition;
import static de.vksi.c4j.Condition.preCondition;
import static de.vksi.c4j.Condition.result;

import java.util.List;

import de.vksi.c4j.Target;

public class EventModelSpecContract implements EventModelSpec {

	@Target
	private EventModelSpec target;

	@Override
	public String getName() {
		if (postCondition()) {
			String result = result();
			assert result != null : "result not null";
		}
		return ignored();
	}

	@Override
	public void setName(String name) {
		if (preCondition()) {
			assert name != null : "name not null";
		}
		if (postCondition()) {
			assert target.getName().equals(name) : "value set successfully";
		}
	}

	@Override
	public String getStart() {
		if (postCondition()) {
			String result = result();
			assert result != null : "result not null";
		}
		return ignored();
	}

	@Override
	public void setStart(String start) {
		if (preCondition()) {
			assert start != null : "start not null";
		}
		if (postCondition()) {
			assert target.getStart().equals(start) : "value set successfully";
		}
	}

	@Override
	public String getStartDate() {
		if (postCondition()) {
			String result = result();
			assert result != null : "result not null";
		}
		return ignored();
	}

	@Override
	public void setStartDate(String startDate) {
		if (preCondition()) {
			assert startDate != null : "startDate not null";
		}
		if (postCondition()) {
			assert target.getStartDate().equals(startDate) : "value set successfully";
		}
	}

	@Override
	public String getEnd() {
		if (postCondition()) {
			String result = result();
			assert result != null : "result not null";
		}
		return ignored();
	}

	@Override
	public void setEnd(String end) {
		if (preCondition()) {
			assert end != null : "end not null";
		}
		if (postCondition()) {
			assert target.getEnd().equals(end) : "value set successfully";
		}
	}

	@Override
	public String getEndDate() {
		if (postCondition()) {
			String result = result();
			assert result != null : "result not null";
		}
		return ignored();
	}

	@Override
	public void setEndDate(String endDate) {
		if (preCondition()) {
			assert endDate != null : "endDate not null";
		}
		if (postCondition()) {
			assert target.getEndDate().equals(endDate) : "value set successfully";
		}
	}

	@Override
	public List<ResourceModel> getResources() {
		if (postCondition()) {
			String result = result();
			assert result != null : "result not null";
		}
		return ignored();
	}

	@Override
	public void setResources(List<ResourceModel> resources) {
		if (preCondition()) {
			assert resources != null : "resources not null";
		}
		if (postCondition()) {
			assert target.getResources().equals(resources) : "value set successfully";
		}
	}

}
