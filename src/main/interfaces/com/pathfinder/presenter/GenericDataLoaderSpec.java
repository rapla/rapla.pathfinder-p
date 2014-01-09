package com.pathfinder.presenter;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.pathfinder.model.Attribut;

import de.vksi.c4j.ContractReference;
import de.vksi.c4j.Pure;

@ContractReference(GenericDataLoaderSpecContract.class)
public interface GenericDataLoaderSpec {

	@Pure
	List<JSONObject> getFreeResourcesResult();

	@Pure
	List<JSONObject> getFreeResourcesResources(JSONObject jsonObject);

	@Pure
	List<Attribut> getModelDetails(String modelLink);

}
