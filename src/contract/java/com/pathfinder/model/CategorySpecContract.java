package com.pathfinder.model;

import static de.vksi.c4j.Condition.ignored;
import static de.vksi.c4j.Condition.postCondition;
import static de.vksi.c4j.Condition.preCondition;
import static de.vksi.c4j.Condition.result;
import de.vksi.c4j.Target;

public class CategorySpecContract implements CategorySpec {

	@Target
	private CategorySpec target;

	@Override
	public String getName() {
		if (postCondition()) {
			String result = result();
			assert result != null : "result not null";
		}
		return ignored();
	}

	@Override
	public void setName(String name) {
		if (preCondition()) {
			assert name != null : "name not null";
		}
		if (postCondition()) {
			assert target.getName().equals(name) : "value set successfully";
		}
	}

	@Override
	public String getId() {
		if (postCondition()) {
			String result = result();
			assert result != null : "result not null";
		}
		return ignored();
	}

	@Override
	public void setId(String id) {
		if (preCondition()) {
			assert id != null : "id not null";
		}
		if (postCondition()) {
			assert target.getId().equals(id) : "value set successfully";
		}
	}

}
