package com.pathfinder.view.layout;

import java.util.Locale;

import com.pathfinder.view.components.LanguageMenu;
import com.pathfinder.view.components.MenuBar;
import com.pathfinder.view.container.DetailContainer;
import com.pathfinder.view.container.InfoPanel;
import com.pathfinder.view.container.SearchPanel;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

/**
 * Defines the main layout of the stele navigation
 * 
 * @author alexh
 * 
 */
public class DesktopLayout extends CustomComponent implements DesktopLayoutSpec {
	private InfoPanel infoPanel = null;
	private MenuBar menuBar = null;
	private SearchPanel searchPanel = null;
	private LanguageMenu languageMenu = null;

	private DetailContainer<?> detailContainer = null;

	private final VerticalLayout layout = new VerticalLayout();

	public DesktopLayout(InfoPanel infoPanel, MenuBar menuBar,
			SearchPanel searchPanel, LanguageMenu languageMenu) {

		this.infoPanel = infoPanel;
		this.menuBar = menuBar;

		this.searchPanel = searchPanel;
		this.languageMenu = languageMenu;

		this.buildLayout();
		this.setCompositionRoot(layout);
	}

	@Override
	public void buildLayout() {
		this.layout.addComponent(infoPanel);
		this.layout.addComponent(searchPanel);
		this.layout.addComponent(menuBar);
		this.layout.addComponent(languageMenu);
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
	public void updateTranslations(Locale locale) {
		// TODO Auto-generated method stub
	}
}