package com.pathfinder.model.gson;

import java.util.List;

public class GSON_GetResourceDetail_LEVEL_3_2 {
	String name;
	String start;
	String end;
	List<GSON_GetResources_LEVEL_2> resources;
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
	public List<GSON_GetResources_LEVEL_2> getResources() {
		return resources;
	}
	public void setResources(List<GSON_GetResources_LEVEL_2> resources) {
		this.resources = resources;
	}


}
