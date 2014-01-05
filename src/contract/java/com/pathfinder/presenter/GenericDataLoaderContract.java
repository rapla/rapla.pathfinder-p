package com.pathfinder.presenter;

import static de.vksi.c4j.Condition.ignored;
import static de.vksi.c4j.Condition.postCondition;
import static de.vksi.c4j.Condition.preCondition;

import java.util.List;

import org.json.simple.JSONObject;

import de.vksi.c4j.ClassInvariant;
import de.vksi.c4j.Target;

public class GenericDataLoaderContract implements GenericDataLoaderSpec {

	@Target
	private GenericDataLoader target;

	@ClassInvariant
	public void classInvariant() {
		// TODO: write invariants if required
	}

	public GenericDataLoaderContract() {
		super();
		if (preCondition()) {
		}
		if (postCondition()) {
		}
	}

	@Override
	public List<JSONObject> getFreeResourcesResult() {
		if (preCondition()) {
		}
		if (postCondition()) {
		}
		return ignored();
	}

	@Override
	public List<JSONObject> getFreeResourcesResources(JSONObject jsonObject) {
		if (preCondition()) {
		}
		if (postCondition()) {
		}
		return ignored();
	}

}
