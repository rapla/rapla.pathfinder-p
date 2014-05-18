package com.pathfinder.model;

import static de.vksi.c4j.Condition.ignored;
import static de.vksi.c4j.Condition.postCondition;
import static de.vksi.c4j.Condition.preCondition;
import static de.vksi.c4j.Condition.result;
import de.vksi.c4j.Target;

public class FreeRoomModelSpecContract implements FreeRoomModelSpec {

	@Target
	private FreeRoomModelSpec target;

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
	public String getLink() {
		if (postCondition()) {
			String result = result();
			assert result != null : "result not null";
		}
		return ignored();
	}

	@Override
	public void setLink(String link) {
		if (preCondition()) {
			assert link != null : "link not null";
		}
		if (postCondition()) {
			assert target.getLink().equals(link) : "value set successfully";
		}
	}

	@Override
	public String getStart() {
		if (postCondition()) {
			String result = result();
			assert result != null : "result not null";
		}
		return ignored();
	}

	@Override
	public void setStart(String start) {
		if (preCondition()) {
			assert start != null : "start not null";
		}
		if (postCondition()) {
			assert target.getStart().equals(start) : "value set successfully";
		}
	}

	@Override
	public String getEnd() {
		if (postCondition()) {
			String result = result();
			assert result != null : "result not null";
		}
		return ignored();
	}

	@Override
	public void setEnd(String end) {
		if (preCondition()) {
			assert end != null : "end not null";
		}
		if (postCondition()) {
			assert target.getEnd().equals(end) : "value set successfully";
		}
	}

}
