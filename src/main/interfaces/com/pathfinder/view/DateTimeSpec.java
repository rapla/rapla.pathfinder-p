package com.pathfinder.view;

import com.pathfinder.util.widgetset.BackToHomeScreenListenerSpec;

/**
 * TimeDateSpec
 * 
 * @author alexh
 * 
 */
public interface DateTimeSpec extends ComponentSpec {
	void addBackToHomeListener(BackToHomeScreenListenerSpec listener);
}
