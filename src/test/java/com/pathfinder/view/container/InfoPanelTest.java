/**
 * 
 */
package com.pathfinder.view.container;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.pathfinder.util.widgetset.DateTime;
import com.pathfinder.view.components.FreeRoom;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.CustomComponent;

/**
 * @author tim
 * 
 */
public class InfoPanelTest {

	InfoPanelSpec infoPanel;

	@Before
	public void initialize() {
		DateTime dateTime = new DateTime();
		FreeRoom freeRoom = new FreeRoom();
		infoPanel = new InfoPanel(dateTime, freeRoom);
	}

	@Test
	public void buildLayoutTest() {
		infoPanel.buildLayout();

		// Check if Root Layout has two components (AccordionView, Keyboard
		// and Searchfield)
		AbstractOrderedLayout rootLayout = (AbstractOrderedLayout) ((CustomComponent) infoPanel)
				.iterator().next();
		Assert.assertEquals(2, rootLayout.getComponentCount());
	}

	@Test
	public void destroyLayoutTest() {
		infoPanel.buildLayout();
		infoPanel.destroyLayout();

		// Check if Root Layout has 0 components
		AbstractOrderedLayout rootLayout = (AbstractOrderedLayout) ((CustomComponent) infoPanel)
				.iterator().next();
		Assert.assertEquals(0, rootLayout.getComponentCount());
	}
}
