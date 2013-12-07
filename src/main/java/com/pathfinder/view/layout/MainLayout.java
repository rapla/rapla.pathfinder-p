package com.pathfinder.view.layout;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.pathfinder.view.components.DetailContainer;
import com.pathfinder.view.components.MenuBar;
import com.pathfinder.view.listener.MainLayoutViewListenerSpec;
import com.pathfinder.view.listener.MenuBarViewListenerSpec;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * Defines the main layout of the stele navigation
 * 
 * @author alexh
 * 
 */
public class MainLayout extends CustomComponent implements MainLayoutSpec,
		MenuBarViewListenerSpec, TranslatabelSpec {
	private final InfoPanel infoPanel = new InfoPanel();
	private final DetailContainer detailContainer = new DetailContainer();
	private final SearchPanel searchPanel = new SearchPanel();
	private final MenuBar menuBar = new MenuBar();
	private static final Locale DEFAULT_LANGUAGE = Locale.GERMAN;

	private final VerticalLayout layout = new VerticalLayout();

	private List<MainLayoutViewListenerSpec> listener = new ArrayList<MainLayoutViewListenerSpec>();

	public MainLayout() {
		UI.getCurrent().setLocale(DEFAULT_LANGUAGE);
		this.buildLayout();
		this.setCompositionRoot(layout);
	}

	@Override
	public void buildLayout() {
		this.layout.addComponent(infoPanel);
		this.layout.addComponent(detailContainer);
		// Should not be shown at startup
		this.hideDetailContainer();
		this.layout.addComponent(searchPanel);
		this.layout.addComponent(menuBar);
		menuBar.addMenuBarListener(this);
	}

	@Override
	public void hideInfoPanel() {
		this.infoPanel.setVisible(false);
	}

	@Override
	public void showInfoPanel() {
		this.infoPanel.setVisible(true);
	}

	@Override
	public void hideDetailContainer() {
		this.detailContainer.setVisible(false);
	}

	@Override
	public void showDetailContainer() {
		this.detailContainer.setVisible(true);
	}

	@Override
	public void hideSearchPanel() {
		this.searchPanel.setVisible(false);
	}

	@Override
	public void showSearchPanel() {
		this.searchPanel.setVisible(true);
	}

	@Override
	public void destroyLayout() {
		layout.removeAllComponents();
	}

	@Override
	public void addMainLayoutViewListener(MainLayoutViewListenerSpec listener) {
		this.listener.add(listener);
	}

	@Override
	public void languageChanged(Locale locale) {
		for (MainLayoutViewListenerSpec mlListener : listener) {
			mlListener.languageChanged(locale);
		}
	}

	@Override
	public void updateTranslations(Locale locale) {
		infoPanel.updateTranslations(locale);
		detailContainer.updateTranslations(locale);
		searchPanel.updateTranslations(locale);
		menuBar.updateTranslations(locale);
	}
}
