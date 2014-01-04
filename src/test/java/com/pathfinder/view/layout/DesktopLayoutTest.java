/**
 * 
 */
package com.pathfinder.view.layout;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.pathfinder.util.widgetset.DateTime;
import com.pathfinder.view.components.AccordionView;
import com.pathfinder.view.components.FreeRoom;
import com.pathfinder.view.components.Keyboard;
import com.pathfinder.view.components.MenuBar;
import com.pathfinder.view.components.SearchField;
import com.pathfinder.view.container.DetailContainer;
import com.pathfinder.view.container.InfoPanel;
import com.pathfinder.view.container.SearchPanel;
import com.vaadin.ui.Component;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.VerticalLayout;

/**
 * @author tim
 * 
 */
public class DesktopLayoutTest {

	private DesktopLayout desktopLayout;

	@Before
	public void initialize() {
		DateTime dateTime = new DateTime();
		FreeRoom freeRoom = new FreeRoom();
		InfoPanel infoPanel = new InfoPanel(dateTime, freeRoom);
		MenuBar menuBar = new MenuBar();
		AccordionView accordionView = new AccordionView();
		Keyboard keyboard = new Keyboard();
		SearchField searchField = new SearchField();
		SearchPanel searchPanel = new SearchPanel(accordionView, keyboard,
				searchField);
		this.desktopLayout = new DesktopLayout(infoPanel, menuBar, searchPanel);
	}

	@Test
	public void buildLayoutTest() {
		desktopLayout.buildLayout();
		Iterator<Component> iterator = desktopLayout.iterator();

		// Assure DesktopLayout has three Components: InfoPanel, SearchPanel and
		// MenuBar
		VerticalLayout layout = (VerticalLayout) iterator.next();
		Assert.assertEquals(3, layout.getComponentCount());
	}

	@Test
	public void destroyLayoutTest() {
		desktopLayout.buildLayout();
		Iterator<Component> iterator = desktopLayout.iterator();
		VerticalLayout layout = (VerticalLayout) iterator.next();
		Assert.assertEquals(3, layout.getComponentCount());

		desktopLayout.destroyLayout();
		Assert.assertEquals(0, layout.getComponentCount());
	}

	@Test
	public void switchToDetailViewTest() {
		desktopLayout.buildLayout();
		desktopLayout.switchToDetailView();

		// Iterate over child components of DesktopLayout and assure there is a
		// DetailContainer, but no SearchPanel
		Map<String, Boolean> returnMap = checkIfSearchPanelOrDetailContainerExists();

		Assert.assertTrue(returnMap.get("detailContainerExists"));
		Assert.assertFalse(returnMap.get("searchPanelExists"));
	}

	@Test
	public void switchToSearchViewTest() {
		desktopLayout.buildLayout();
		desktopLayout.switchToSearchView();

		// Iterate over child components of DesktopLayout and assure there is no
		// DetailContainer, but a SearchPanel
		Map<String, Boolean> returnMap = checkIfSearchPanelOrDetailContainerExists();

		Assert.assertFalse(returnMap.get("detailContainerExists"));
		Assert.assertTrue(returnMap.get("searchPanelExists"));
	}

	/**
	 * Utility method to check if DesktopLayout contains a SearchPanel or a
	 * DetailContainer
	 * 
	 * @return Map with two boolean values, indicating, if SearchPanel or
	 *         DetailContainer exists in Layout
	 */
	private Map<String, Boolean> checkIfSearchPanelOrDetailContainerExists() {
		HasComponents layout = (HasComponents) desktopLayout.iterator().next();
		Iterator<Component> iterator = layout.iterator();

		boolean detailContainerExists = false;
		boolean searchPanelExists = false;
		while (iterator.hasNext()) {
			Component component = iterator.next();
			if (component instanceof DetailContainer<?>) {
				detailContainerExists = true;
			} else if (component instanceof SearchPanel) {
				searchPanelExists = true;
			}
		}

		Map<String, Boolean> returnMap = new HashMap<String, Boolean>();
		returnMap.put("detailContainerExists", detailContainerExists);
		returnMap.put("searchPanelExists", searchPanelExists);
		return returnMap;
	}

}
