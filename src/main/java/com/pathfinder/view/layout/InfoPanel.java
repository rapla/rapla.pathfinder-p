package com.pathfinder.view.layout;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.pathfinder.view.components.FreeRoom;
import com.pathfinder.view.components.TimeDate;
import com.pathfinder.view.listener.InfoPanelViewListenerSpec;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;

public class InfoPanel extends CustomComponent implements InfoPanelSpec {
	private TimeDate timeDate = null;
	private FreeRoom freeRoom = null;

	private final HorizontalLayout horizontalLayout = new HorizontalLayout();

	private List<InfoPanelViewListenerSpec> listener = new ArrayList<InfoPanelViewListenerSpec>();

	public InfoPanel(TimeDate timeDate, FreeRoom freeRoom) {
		this.timeDate = timeDate;
		this.freeRoom = freeRoom;
		
		this.buildLayout();
		this.setCompositionRoot(horizontalLayout);
	}

	@Override
	public void buildLayout() {
		horizontalLayout.addComponent(timeDate);
		horizontalLayout.addComponent(freeRoom);
		horizontalLayout.setExpandRatio(timeDate, 0.5f);
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
		timeDate.updateTranslations(locale);
		freeRoom.updateTranslations(locale);
	}

}
