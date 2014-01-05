package com.pathfinder.presenter;

import java.util.List;

import org.json.simple.JSONObject;

import de.vksi.c4j.Pure;

public interface GenericDataLoaderSpec {

	@Pure
	List<JSONObject> getFreeResourcesResult();

	@Pure
	List<JSONObject> getFreeResourcesResources(JSONObject jsonObject);

}
