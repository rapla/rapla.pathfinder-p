package com.pathfinder.model;

import java.util.List;

import de.vksi.c4j.ContractReference;
import de.vksi.c4j.Pure;

@ContractReference(EventModelSpecContract.class)
public interface EventModelSpec {

	/**
	 * @return the name
	 */
	@Pure
	String getName();

	/**
	 * @param name
	 *            the name to set
	 */
	void setName(String name);

	/**
	 * @return the start
	 */
	@Pure
	String getStart();

	/**
	 * @param start
	 *            the start to set
	 */
	void setStart(String start);

	/**
	 * @return the startDate
	 */
	@Pure
	String getStartDate();

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	void setStartDate(String startDate);

	/**
	 * @return the end
	 */
	@Pure
	String getEnd();

	/**
	 * @param end
	 *            the end to set
	 */
	void setEnd(String end);

	/**
	 * @return the endDate
	 */
	@Pure
	String getEndDate();

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	void setEndDate(String endDate);

	/**
	 * @return the resources
	 */
	@Pure
	List<ResourceModel> getResources();

	/**
	 * @param resources
	 *            the resources to set
	 */
	void setResources(List<ResourceModel> resources);

}