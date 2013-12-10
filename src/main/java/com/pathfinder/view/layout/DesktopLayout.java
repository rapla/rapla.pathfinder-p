package com.pathfinder.view.layout;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.pathfinder.translation.TranslationKeys;
import com.pathfinder.translation.Translator;
import com.pathfinder.view.TranslatabelSpec;
import com.pathfinder.view.components.DetailContainer;
import com.pathfinder.view.components.MenuBar;
import com.pathfinder.view.container.InfoPanel;
import com.pathfinder.view.container.SearchPanel;
import com.pathfinder.view.listener.MainLayoutViewListenerSpec;
import com.pathfinder.view.listener.MenuBarViewListenerSpec;
import com.vaadin.server.Page;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

/**
 * Defines the main layout of the stele navigation
 * 
 * @author alexh
 * 
 */
public class DesktopLayout extends CustomComponent implements
		DesktopLayoutSpec, MenuBarViewListenerSpec, TranslatabelSpec {
	private InfoPanel infoPanel = null;
	private DetailContainer detailContainer = null;
	private SearchPanel searchPanel = null;
	private MenuBar menuBar = null;

	private final VerticalLayout layout = new VerticalLayout();

	private List<MainLayoutViewListenerSpec> listener = new ArrayList<MainLayoutViewListenerSpec>();

	public DesktopLayout(InfoPanel infoPanel, DetailContainer detailContainer,
			SearchPanel searchPanel, MenuBar menuBar) {
		this.infoPanel = infoPanel;
		this.detailContainer = detailContainer;
		this.searchPanel = searchPanel;
		this.menuBar = menuBar;

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
		Page.getCurrent().setTitle(
				Translator.getInstance().translate(TranslationKeys.APP_TITLE));
	}
}
