package com.pathfinder.view.layout;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.pathfinder.util.translation.TranslationKeys;
import com.pathfinder.util.translation.Translator;
import com.pathfinder.view.TranslatabelSpec;
import com.pathfinder.view.components.MenuBar;
import com.pathfinder.view.container.DetailContainer;
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
	private MenuBar menuBar = null;
	private SearchPanel searchPanel = null;

	private DetailContainer<?> detailContainer = null;

	private final VerticalLayout layout = new VerticalLayout();

	private List<MainLayoutViewListenerSpec> listener = new ArrayList<MainLayoutViewListenerSpec>();

	public DesktopLayout(InfoPanel infoPanel, MenuBar menuBar,
			SearchPanel searchPanel) {

		this.infoPanel = infoPanel;
		this.menuBar = menuBar;

		this.searchPanel = searchPanel;

		this.buildLayout();
		this.setCompositionRoot(layout);
	}

	@Override
	public void buildLayout() {
		this.layout.addComponent(infoPanel);
		this.layout.addComponent(searchPanel);
		this.layout.addComponent(menuBar);
		menuBar.addMenuBarListener(this);
	}

	@Override
	public <T> void switchToDetailView() {
		detailContainer = new DetailContainer<T>(null, null, null);
		layout.replaceComponent(searchPanel, detailContainer);
	}

	@Override
	public void switchToSearchView() {
		layout.replaceComponent(detailContainer, searchPanel);
		detailContainer = null;
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
		// detailContainer.updateTranslations(locale);
		searchPanel.updateTranslations(locale);
		menuBar.updateTranslations(locale);
		Page.getCurrent().setTitle(
				Translator.getInstance().translate(TranslationKeys.APP_TITLE));
	}
}
