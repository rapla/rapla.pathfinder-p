package com.pathfinder.model.gson;

import java.util.List;

public class GsonGetResourceDetailLevel32 {
	String name;
	String start;
	String end;
	List<GsonGetResourcesLevel2> resources;
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
	public List<GsonGetResourcesLevel2> getResources() {
		return resources;
	}
	public void setResources(List<GsonGetResourcesLevel2> resources) {
		this.resources = resources;
	}


}
