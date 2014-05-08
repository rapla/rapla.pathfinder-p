package com.pathfinder.util.widgetset;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pathfinder.util.translation.TranslationKeys;
import com.pathfinder.util.translation.Translator;
import com.pathfinder.util.translation.TranslatorSpec;
import com.pathfinder.util.widgetset.client.datetime.DateTimeServerRpc;
import com.pathfinder.util.widgetset.client.datetime.DateTimeState;
import com.pathfinder.view.DateTimeSpec;
import com.vaadin.ui.AbstractComponent;

/**
 * Server-side representation of DateTimeWidget; here the initial time for the
 * widget will be set
 * 
 * @author tim
 * 
 */
public class DateTime extends AbstractComponent implements DateTimeSpec {

	private TranslatorSpec translator = Translator.getInstance();

	private List<BackToHomeScreenListenerSpec> backToHomeListener = new ArrayList<>();

	private DateTimeServerRpc rpc = new DateTimeServerRpc() {

		@Override
		public void synchronizeWithServertime() {
			getState().setTime(new Date().getTime());
		}

		@Override
		public void updateDateTimeFormatter() {
			getState().setDateFormat(
					translator.translate(TranslationKeys.DATE_FORMAT));
			getState().setTimeFormat(
					translator.translate(TranslationKeys.TIME_FORMAT));
		}

		@Override
		public void goBackToHomeScreen() {
			for (BackToHomeScreenListenerSpec listener : backToHomeListener) {
				listener.timeToGoHome();
			}
		}

	};

	public DateTime() {
		registerRpc(rpc);
		rpc.updateDateTimeFormatter();
		rpc.synchronizeWithServertime();
	}

	@Override
	public DateTimeState getState() {
		return (DateTimeState) super.getState();
	}

	@Override
	public void updateTranslations() {
		rpc.updateDateTimeFormatter();
	}

	@Override
	public void addBackToHomeListener(BackToHomeScreenListenerSpec listener) {
		this.backToHomeListener.add(listener);
	}

	@Override
	public void doCleanup() {
		this.backToHomeListener.clear();
	}

}
