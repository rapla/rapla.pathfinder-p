package com.pathfinder.util.widgetset.client.datetime;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Widget;
import com.pathfinder.util.widgetset.DateTime;
import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;

/**
 * This connector allows interaction between server and widget; it's main
 * purpose is to handle synchronization
 * 
 * @author tim
 * 
 */
@Connect(DateTime.class)
public class DateTimeConnector extends AbstractComponentConnector {

	DateTimeServerRpc rpc = RpcProxy.create(DateTimeServerRpc.class, this);

	/**
	 * timer triggers synchronization of widget and server time
	 */
	private Timer synchronizeWithServerTimer;

	private Timer backToHomeScreenTimer;

	/**
	 * Intervall in which the current widget's time will be synchronized with
	 * the server time
	 */
	private final static int SYNCHRONIZE_INTERVALL = 10 * 60 * 1000;

	private final static int CHECK_IF_TIME_TO_GO_HOME_INTERVALL = 5 * 1000;

	private long timeOld = 0;

	public DateTimeConnector() {

		synchronizeWithServerTimer = new Timer() {

			@Override
			public void run() {
				rpc.synchronizeWithServertime();
			}
		};
		synchronizeWithServerTimer.scheduleRepeating(SYNCHRONIZE_INTERVALL);

		backToHomeScreenTimer = new Timer() {

			@Override
			public void run() {
				rpc.goBackToHomeScreen();
			}
		};
		backToHomeScreenTimer
				.scheduleRepeating(CHECK_IF_TIME_TO_GO_HOME_INTERVALL);

	}

	@Override
	protected Widget createWidget() {
		return GWT.create(DateTimeWidget.class);
	}

	@Override
	public DateTimeWidget getWidget() {
		return (DateTimeWidget) super.getWidget();
	}

	@Override
	public DateTimeState getState() {
		return (DateTimeState) super.getState();
	}

	@Override
	public void onStateChanged(StateChangeEvent stateChangeEvent) {
		super.onStateChanged(stateChangeEvent);

		if (getState().getDateFormat() != null)
			getWidget().setDateFormat(getState().getDateFormat());
		if (getState().getTimeFormat() != null)
			getWidget().setTimeFormat(getState().getTimeFormat());
		if (getState().getTime() != timeOld) {
			timeOld = getState().getTime();
			getWidget().setTime(getState().getTime());
		}
	}

}
