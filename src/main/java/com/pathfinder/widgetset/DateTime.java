package com.pathfinder.widgetset;

import java.util.Date;
import java.util.Locale;

import com.pathfinder.view.components.TimeDateSpec;
import com.pathfinder.widgetset.client.datetime.DateTimeServerRpc;
import com.pathfinder.widgetset.client.datetime.DateTimeState;
import com.vaadin.ui.AbstractComponent;

/**
 * Server-side representation of DateTimeWidget; here the initial time for the
 * widget will be set
 * 
 * @author tim
 * 
 */
public class DateTime extends AbstractComponent implements TimeDateSpec {

	private DateTimeServerRpc rpc = new DateTimeServerRpc() {

		@Override
		public void synchronizeWithServertime() {
			getState().setTime(new Date().getTime());
		}
	};

	public DateTime() {
		registerRpc(rpc);
		rpc.synchronizeWithServertime();
	}

	@Override
	public DateTimeState getState() {
		return (DateTimeState) super.getState();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pathfinder.view.TranslatabelSpec#updateTranslations(java.util.Locale)
	 */
	@Override
	public void updateTranslations(Locale locale) {
		// TODO: change format of date-time-representation (e.g. 12:45 am if
		// English)
	}

}
