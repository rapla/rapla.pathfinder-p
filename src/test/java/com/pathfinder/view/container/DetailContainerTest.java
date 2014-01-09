/**
 * 
 */
package com.pathfinder.view.container;

import org.junit.Assert;
import org.junit.Before;

import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.CustomComponent;

/**
 * @author tim
 * 
 */
public class DetailContainerTest {

	private DetailContainer detailContainer;

	@Before
	public void initialize() {
		// TODO
		// ResourceModel model = new ResourceModel("Name", "Link", "Id",
		// new String[] { "Searchfield 1", "Searchfield 2" });
		// detailContainer = new DetailContainer<RoomModel>(RoomModel.class,
		// new BeanItem<RoomModel>(model), "");
	}

	// TODO: to be continued when DetailImage implemented
	// @Test
	public void buildLayoutTest() {

		detailContainer.buildLayout();

		// Check if Root Layout has three components (AccordionView, Keyboard
		// and Searchfield)
		AbstractOrderedLayout rootLayout = (AbstractOrderedLayout) ((CustomComponent) detailContainer)
				.iterator().next();
		Assert.assertEquals(2, rootLayout.getComponentCount());
	}

	// TODO: to be continued when DetailImage implemented
	// @Test
	public void hideShowTest() {
		Assert.assertTrue(detailContainer.isVisible());
		detailContainer.hideDetailContainer();
		Assert.assertFalse(detailContainer.isVisible());
		detailContainer.showDetailContainer();
		Assert.assertTrue(detailContainer.isVisible());
	}
}