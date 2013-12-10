package com.pathfinder.model;

import java.util.List;

public class EventModel {
	String name;
	String start;
	String end;
	List<ResourceModel> resources;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public List<ResourceModel> getResources() {
		return resources;
	}
	public void setResources(List<ResourceModel> resources) {
		this.resources = resources;
	}


}
