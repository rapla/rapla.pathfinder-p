package com.pathfinder.presenter;

import static com.pathfinder.view.components.KeyboardId.*;
import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.pathfinder.PathfinderUI;
import com.pathfinder.view.components.DateTimeSpec;
import com.pathfinder.view.components.FreeRoomViewSpec;
import com.pathfinder.view.components.KeyboardId;
import com.pathfinder.view.components.MenuBarSpec;
import com.pathfinder.view.layout.DetailContainerSpec;
import com.pathfinder.view.layout.SteleLayoutSpec;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Component;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.UI;

public class StelePresenterTest {
	StelePresenter stelePresenter;
	SteleLayoutSpec desktopLayout;
	DateTimeSpec dateTime;
	FreeRoomViewSpec freeRoom;
	DetailContainerSpec detailContainer;
	MenuBarSpec menuBar;

	@Before
	public void setUp() {
		PathfinderUI ui = new PathfinderUI();
		ui.setLocale(Locale.GERMAN);
		UI.setCurrent(ui);

		stelePresenter = new StelePresenter();
		desktopLayout = stelePresenter.getDesktopLayoutView();

		HasComponents rootLayout = (HasComponents) ((HasComponents) desktopLayout)
				.iterator().next();
		Iterator<Component> iterator = rootLayout.iterator();
		while (iterator.hasNext()) {
			Component component = iterator.next();
			if (component instanceof DateTimeSpec) {
				dateTime = (DateTimeSpec) component;
			} else if (component instanceof MenuBarSpec) {
				menuBar = (MenuBarSpec) component;
			} else if (component instanceof AbstractOrderedLayout) {
				Iterator<Component> contentIterator = ((AbstractOrderedLayout) component)
						.iterator();
				while (contentIterator.hasNext()) {
					Component contentComponent = contentIterator.next();
					if (contentComponent instanceof FreeRoomViewSpec) {
						freeRoom = (FreeRoomViewSpec) contentComponent;
					} else if (contentComponent instanceof DetailContainerSpec) {
						detailContainer = (DetailContainerSpec) contentComponent;
					}

				}
			}

		}

		Assert.assertTrue(dateTime != null);
		Assert.assertTrue(freeRoom != null);
		Assert.assertTrue(detailContainer != null);
		Assert.assertTrue(menuBar != null);
	}

	@Test
	public void addKeyboardKeyToSearchStringTest() {
		stelePresenter.buttonClick(I);
		stelePresenter.buttonClick(K);
		assertEquals("IK", stelePresenter.getSearchString());
	}

	@Test
	public void deleteKeyboardKeyFromSearchStringTest() {
		stelePresenter.buttonClick(I);
		stelePresenter.buttonClick(K);
		stelePresenter.buttonClick(DELETE);
		assertEquals("I", stelePresenter.getSearchString());
	}

	@Test
	public void clearSearchStringTest() {
		stelePresenter.buttonClick(I);
		stelePresenter.buttonClick(K);
		stelePresenter.clearSearchString();
		assertEquals("", stelePresenter.getSearchString());
	}

	@Test
	public void complexKeyboardKeyTest() {

		stelePresenter.buttonClick(L);
		stelePresenter.buttonClick(P);
		assertEquals("LP", stelePresenter.getSearchString());

		stelePresenter.buttonClick(DELETE);
		stelePresenter.buttonClick(DELETE);
		stelePresenter.buttonClick(DELETE);
		stelePresenter.buttonClick(DELETE);
		assertEquals("", stelePresenter.getSearchString());

		stelePresenter.buttonClick(T);
		stelePresenter.buttonClick(AE);
		stelePresenter.buttonClick(S);
		assertEquals("TÄS", stelePresenter.getSearchString());

		stelePresenter.buttonClick(SPACE);
		assertEquals("TÄS ", stelePresenter.getSearchString());

		stelePresenter.buttonClick(SPACE);
		stelePresenter.buttonClick(SPACE);
		assertEquals("TÄS   ", stelePresenter.getSearchString());

		stelePresenter.buttonClick(KeyboardId.RIGHT);
		stelePresenter.buttonClick(KeyboardId.RIGHT);
		stelePresenter.buttonClick(KeyboardId.RIGHT);
		stelePresenter.buttonClick(KeyboardId.RIGHT);
		stelePresenter.buttonClick(KeyboardId.RIGHT);
		stelePresenter.buttonClick(KeyboardId.DELETE);
		assertEquals("TÄS  ", stelePresenter.getSearchString());

		stelePresenter.buttonClick(UE);
		stelePresenter.buttonClick(UE);
		stelePresenter.buttonClick(LEFT);
		stelePresenter.buttonClick(X);
		assertEquals("TÄS  ÜXÜ", stelePresenter.getSearchString());

		stelePresenter.buttonClick(LEFT);
		stelePresenter.buttonClick(LEFT);
		stelePresenter.buttonClick(LEFT);
		stelePresenter.buttonClick(LEFT);
		stelePresenter.buttonClick(LEFT);
		stelePresenter.buttonClick(LEFT);
		stelePresenter.buttonClick(LEFT);
		stelePresenter.buttonClick(LEFT);
		stelePresenter.buttonClick(LEFT);
		stelePresenter.buttonClick(LEFT);
		stelePresenter.buttonClick(LEFT);
		stelePresenter.buttonClick(OE);
		assertEquals("ÖTÄS  ÜXÜ", stelePresenter.getSearchString());

		stelePresenter.buttonClick(RIGHT);
		stelePresenter.buttonClick(RIGHT);
		stelePresenter.buttonClick(RIGHT);
		stelePresenter.buttonClick(M);
		assertEquals("ÖTÄSM  ÜXÜ", stelePresenter.getSearchString());
	}

}