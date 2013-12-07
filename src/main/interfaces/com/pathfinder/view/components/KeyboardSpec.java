package com.pathfinder.view.components;

public interface KeyboardSpec extends ComponentSpec {

	interface KeyboardViewListener {
		void buttonClick(String key);
	}

	void addListener(KeyboardViewListener listener);

}
