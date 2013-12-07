package com.pathfinder.view.components;

public interface KeyboardSpec {

	interface KeyboardViewListener {
		void buttonClick(String key);
	}

	void addListener(KeyboardViewListener listener);

}
