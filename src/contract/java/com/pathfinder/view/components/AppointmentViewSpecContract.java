/**
 * 
 */
package com.pathfinder.view.components;

import static de.vksi.c4j.Condition.ignored;
import static de.vksi.c4j.Condition.postCondition;
import static de.vksi.c4j.Condition.preCondition;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.BrowserFrame;

import de.vksi.c4j.Condition;
import de.vksi.c4j.Target;

/**
 * @author tim
 * 
 */
public class AppointmentViewSpecContract extends ComponentSpecContract
		implements AppointmentViewSpec {

	@Target
	private AppointmentViewSpec target;

	@Override
	public void setUrl(String url) {
		if (preCondition()) {
			assert url != null : "URL not null";
		}
		if (postCondition()) {
			String browserFrameUrl = ((ExternalResource) target
					.getBrowserFrame().getSource()).getURL();
			if (url.equals("")) {
				assert browserFrameUrl.equals("about:blank") : "If url is empty string, browserFrameUrl gets the value about:blank";
			} else {
				assert browserFrameUrl.equals(url) : "If url is not an empty string, broswerFrameUrl has the Value of input url";
			}
		}
	}

	@Override
	public void hideAppointmentView() {
		if (postCondition()) {
			assert !target.isVisible() : "AppointmentView ist not visible";
		}
	}

	@Override
	public void showAppointmentView() {
		if (postCondition()) {
			assert target.isVisible() : "AppointmentView ist visible";
		}
	}

	@Override
	public BrowserFrame getBrowserFrame() {
		if (postCondition()) {
			BrowserFrame browserFrame = Condition.result();
			assert browserFrame != null : "BrowserFrame not null";
		}
		return ignored();
	}

}
