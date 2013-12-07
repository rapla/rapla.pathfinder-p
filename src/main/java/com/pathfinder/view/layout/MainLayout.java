package com.pathfinder.view.layout;

import com.pathfinder.view.components.DetailContainer;
import com.pathfinder.view.components.MenuBar;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

/**
 * Defines the main layout of the stele navigation
 * 
 * @author alexh
 * 
 */
public class MainLayout extends CustomComponent implements MainLayoutSpec {
	private final InfoPanel infoPanel = new InfoPanel();
	private final DetailContainer detailContainer = new DetailContainer();
	private final SearchPanel searchPanel = new SearchPanel();
	private final MenuBar menuBar = new MenuBar();

	private final VerticalLayout layout = new VerticalLayout();

	public MainLayout() {
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
}
