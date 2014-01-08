package com.pathfinder.presenter;

import static de.vksi.c4j.Condition.ignored;
import static de.vksi.c4j.Condition.postCondition;
import static de.vksi.c4j.Condition.preCondition;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import de.vksi.c4j.ClassInvariant;
import de.vksi.c4j.Target;

public class GenericDataLoaderSpecContract implements GenericDataLoaderSpec {

	@Target
	private GenericDataLoaderSpec target;

	@ClassInvariant
	public void classInvariant() {
		// TODO: write invariants if required
	}

	@Override
	public List<JSONObject> getFreeResourcesResult() {
		if (preCondition()) {
			// TODO: write preconditions if required
		}
		if (postCondition()) {
			// TODO: write postconditions if required
		}
		return ignored();
	}

	@Override
	public List<JSONObject> getFreeResourcesResources(JSONObject jsonObject) {
		if (preCondition()) {
			// TODO: write preconditions if required
		}
		if (postCondition()) {
			// TODO: write postconditions if required
		}
		return ignored();
	}

	@Override
	public JSONArray getModelDetails(String modelLink) {
		if (preCondition()) {
			// TODO: write preconditions if required
		}
		if (postCondition()) {
			// TODO: write postconditions if required
		}
		return ignored();
	}

}
