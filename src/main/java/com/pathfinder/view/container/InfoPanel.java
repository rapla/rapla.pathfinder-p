package com.pathfinder.view.container;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.pathfinder.view.components.FreeRoom;
import com.pathfinder.view.listener.InfoPanelViewListenerSpec;
import com.pathfinder.widgetset.DateTime;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;

public class InfoPanel extends CustomComponent implements InfoPanelSpec {
	private DateTime dateTime = null;
	private FreeRoom freeRoom = null;

	private final HorizontalLayout horizontalLayout = new HorizontalLayout();

	private List<InfoPanelViewListenerSpec> listener = new ArrayList<InfoPanelViewListenerSpec>();

	public InfoPanel(DateTime dateTime, FreeRoom freeRoom) {
		this.dateTime = dateTime;
		this.freeRoom = freeRoom;

		this.buildLayout();
		this.setCompositionRoot(horizontalLayout);
	}

	@Override
	public void buildLayout() {
		horizontalLayout.addComponent(dateTime);
		horizontalLayout.addComponent(freeRoom);
		horizontalLayout.setExpandRatio(dateTime, 0.5f);
		horizontalLayout.setExpandRatio(freeRoom, 0.5f);
		horizontalLayout.setSizeFull();
	}

	@Override
	public void destroyLayout() {
		horizontalLayout.removeAllComponents();
	}

	@Override
	public void addInfoPanelViewListener(InfoPanelViewListenerSpec listener) {
		this.listener.add(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pathfinder.view.layout.TranslatabelSpec#updateTranslations(java.util
	 * .Locale)
	 */
	@Override
	public void updateTranslations(Locale locale) {
		// dateTime.updateTranslations(locale);
		freeRoom.updateTranslations(locale);
	}

}
