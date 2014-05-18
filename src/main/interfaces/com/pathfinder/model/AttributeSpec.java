package com.pathfinder.model;

import de.vksi.c4j.ContractReference;
import de.vksi.c4j.Pure;

/**
 * Defines an attribute that can be delivered by rapla when calling a method to
 * get resource details
 * 
 * @author tim
 *
 */
@ContractReference(AttributeSpecContract.class)
public interface AttributeSpec {

	@Pure
	AttributeKey getKey();

	void setKey(String key);

	@Pure
	String getLabel();

	void setLabel(String label);

	@Pure
	String getValue();

	void setValue(String value);

	@Pure
	String getPerson();

	void setPerson(String person);

	@Pure
	String getInformation();

	void setInformation(String information);

	@Pure
	String toString();

}