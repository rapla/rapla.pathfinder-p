package com.pathfinder.model;

public class Attribut {
	private String label;
	private String value;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "ResourceDetailRowModel [label=" + label + ", value=" + value
				+ "]";
	}
}
