package com.pathfinder.view.container;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.pathfinder.view.components.AccordionView;
import com.pathfinder.view.components.SearchField;
import com.vaadin.ui.CustomComponent;

/**
 * @author tim
 * 
 */
public class SearchPanelTest {

	SearchPanel searchPanel;

	@Before
	public void initialize() {
		AccordionView accordionView = new AccordionView();
		SearchField searchField = new SearchField();
		searchPanel = new SearchPanel(accordionView, searchField);
	}

	@Test
	public void hideShowSearchPanelTest() {
		searchPanel.hideSearchPanel();

		Assert.assertFalse(((CustomComponent) searchPanel).isVisible());

		searchPanel.showSearchPanel();

		Assert.assertTrue(((CustomComponent) searchPanel).isVisible());
	}
}