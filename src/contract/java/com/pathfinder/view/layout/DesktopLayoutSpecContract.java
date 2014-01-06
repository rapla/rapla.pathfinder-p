/**
 * 
 */
package com.pathfinder.view.layout;

import static de.vksi.c4j.Condition.ignored;
import static de.vksi.c4j.Condition.postCondition;
import static de.vksi.c4j.Condition.preCondition;

import com.pathfinder.view.ViewSpecContract;
import com.pathfinder.view.components.FreeRoomViewSpec;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button.ClickListener;

import de.vksi.c4j.Condition;
import de.vksi.c4j.Target;

/**
 * @author tim
 * 
 */
public class DesktopLayoutSpecContract extends ViewSpecContract implements
		DesktopLayoutSpec {

	@Target
	private DesktopLayoutSpec target;

	@Override
	public void addClickListenerAppointmentButton(ClickListener listener) {
		if (preCondition()) {
			assert listener != null : "Listener not null";
		}
	}

	@Override
	public void addLanguageValueChangeListener(ValueChangeListener listener) {
		if (preCondition()) {
			assert listener != null : "Listener not null";
		}
	}

	@Override
	public void hideAppointmentButton() {
	}

	@Override
	public void showAppointmentButton() {
	}

	@Override
	public void switchToSearchView() {
	}

	@Override
	public <T> void switchToDetailView() {
	}

	@Override
	public void switchToAppointmentView() {
	}

	@Override
	public void setAppointmentUrl(String url) {
	}

	@Override
	public FreeRoomViewSpec getFreeRoom() {
		if (postCondition()) {
			FreeRoomViewSpec freeRoomViewSpec = Condition.result();
			assert freeRoomViewSpec != null : "Result not null";
		}
		return ignored();
	}

}
