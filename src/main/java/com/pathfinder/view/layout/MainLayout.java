package com.pathfinder.view.layout;

import com.pathfinder.view.components.Detail;
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
	private final Detail detail = new Detail();
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
		this.layout.addComponent(detail);
		// Should not be shown at startup
		this.hideDetail();
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
	public void hideDetail() {
		this.detail.setVisible(false);
	}

	@Override
	public void showDetail() {
		this.detail.setVisible(true);
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
