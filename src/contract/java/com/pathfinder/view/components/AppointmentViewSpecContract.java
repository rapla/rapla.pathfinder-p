/**
 * 
 */
package com.pathfinder.view.components;

import static de.vksi.c4j.Condition.postCondition;
import static de.vksi.c4j.Condition.preCondition;
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
	public void setAppointmentUrl(String url) {
		if (preCondition()) {
			assert url != null : "URL not null";
		}
		if (postCondition()) {
			// String browserFrameUrl = ((ExternalResource) target
			// .getBrowserFrame().getSource()).getURL();
			// if (url.equals("")) {
			// assert browserFrameUrl.equals("about:blank") :
			// "If url is empty string, browserFrameUrl gets the value about:blank";
			// } else {
			// assert browserFrameUrl.equals(url) :
			// "If url is not an empty string, broswerFrameUrl has the Value of input url";
			// }
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
}
