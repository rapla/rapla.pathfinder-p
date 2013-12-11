package com.pathfinder.view.components;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.pathfinder.translation.TranslationKeys;
import com.pathfinder.translation.Translator;
import com.pathfinder.translation.TranslatorSpec;
import com.pathfinder.view.listener.TimeDateViewListenerSpec;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

public class TimeDate extends Label implements TimeDateSpec {

	private List<TimeDateViewListenerSpec> listener = new ArrayList<TimeDateViewListenerSpec>();
	private final static TranslatorSpec TRANSLATOR = Translator.getInstance();

	/**
	 * Default interval in milliseconds for updating the date-time-text
	 */
	private final static int REFRESH_TIME_INTERVAL = 60000;

	public TimeDate() {
		this.refreshTimeAndDate();
		// startTimeUpdater();
	}

	@Override
	public synchronized void refreshTimeAndDate() {
		DateFormat formatter = DateFormat.getDateTimeInstance(
				DateFormat.DEFAULT, DateFormat.SHORT, UI.getCurrent()
						.getLocale());
		String timestring = formatter.format(new Date()) + " "
				+ TRANSLATOR.translate(TranslationKeys.OCLOCK);
		this.setValue(timestring);
	}

	@Override
	public void updateTranslations(Locale locale) {
		refreshTimeAndDate();
	}

	@Override
	public void addTimeDateListener(TimeDateViewListenerSpec listener) {
		this.listener.add(listener);
	}

	/**
	 * Starts a new Thread to update the time in a given interval
	 */
	private void startTimeUpdater() {
		Thread timeUpdater = new Thread(new Runnable() {

			@Override
			public void run() {

				while (UI.getCurrent() != null) {
					UI.getCurrent().access(new Runnable() {
						@Override
						public void run() {
							refreshTimeAndDate();
							UI.getCurrent().push();
						}
					});

					try {
						Thread.sleep(REFRESH_TIME_INTERVAL);
					} catch (InterruptedException e) {
						// Time Updater stopped --> ignore exception
						// because it's not a vital function
					}
				}
			}
		});
		timeUpdater.start();
	}
}