package com.pathfinder.model;

import de.vksi.c4j.ContractReference;
import de.vksi.c4j.Pure;

@ContractReference(FreeRoomModelSpecContract.class)
public interface FreeRoomModelSpec {

	@Pure
	String getId();

	void setId(String id);

	@Pure
	String getName();

	void setName(String name);

	@Pure
	String getLink();

	void setLink(String link);

	@Pure
	String getStart();

	void setStart(String start);

	@Pure
	String getEnd();

	void setEnd(String end);

}