package com.pathfinder.presenter;

import java.util.Locale;

import com.pathfinder.view.components.ComponentSpec;
import com.pathfinder.view.layout.MainLayout;
import com.pathfinder.view.listener.MainLayoutViewListenerSpec;
import com.vaadin.ui.CustomComponent;

/**
 * To see something when you build the project
 * 
 * @author alexh
 * 
 */
public class MainPresenter implements MainLayoutViewListenerSpec, ComponentSpec {
	private final MainLayout mainLayout = new MainLayout();

	public MainPresenter() {
		mainLayout.addMainLayoutViewListener(this);
	}

	public CustomComponent getMainLayoutView() {
		return mainLayout;
	}

	@Override
	public void updateTranslations(Locale locale) {
		mainLayout.updateTranslations(locale);
	}

	@Override
	public void languageChanged(Locale locale) {
		updateTranslations(locale);
	}
}
