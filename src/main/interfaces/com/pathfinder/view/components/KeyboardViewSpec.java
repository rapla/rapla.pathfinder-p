package com.pathfinder.view.components;

public interface KeyboardViewSpec {

	interface KeyboardViewListener {
		void buttonClick(String key);
	}

	void addListener(KeyboardViewListener listener);

}
