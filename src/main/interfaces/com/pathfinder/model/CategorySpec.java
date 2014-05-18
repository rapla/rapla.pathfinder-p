package com.pathfinder.model;

import de.vksi.c4j.ContractReference;
import de.vksi.c4j.Pure;

@ContractReference(CategorySpecContract.class)
public interface CategorySpec {

	@Pure
	String getName();

	void setName(String name);

	@Pure
	String getId();

	void setId(String id);

	@Pure
	String toString();

}