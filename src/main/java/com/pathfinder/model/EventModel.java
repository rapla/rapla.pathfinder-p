package com.pathfinder.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventModel implements EventModelSpec {

	private String name;
	private String start;
	private String startDate;
	private String end;
	private String endDate;
	private List<ResourceModel> resources;

	/**
	 * @return the name
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the start
	 */
	@Override
	public String getStart() {
		return start;
	}

	/**
	 * @param start
	 *            the start to set
	 */
	@Override
	public void setStart(String start) {
		this.start = start;
	}

	/**
	 * @return the startDate
	 */
	@Override
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	@Override
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the end
	 */
	@Override
	public String getEnd() {
		return end;
	}

	/**
	 * @param end
	 *            the end to set
	 */
	@Override
	public void setEnd(String end) {
		this.end = end;
	}

	/**
	 * @return the endDate
	 */
	@Override
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	@Override
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the resources
	 */
	@Override
	public List<ResourceModel> getResources() {
		return resources;
	}

	/**
	 * @param resources
	 *            the resources to set
	 */
	@Override
	public void setResources(List<ResourceModel> resources) {
		this.resources = resources;
	}

}