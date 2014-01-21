package com.pathfinder.presenter;

import static com.pathfinder.view.components.KeyboardId.*;
import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.pathfinder.PathfinderUI;
import com.pathfinder.view.components.AppointmentViewSpec;
import com.pathfinder.view.components.DateTimeSpec;
import com.pathfinder.view.components.FreeRoomViewSpec;
import com.pathfinder.view.components.KeyboardId;
import com.pathfinder.view.components.MenuBarSpec;
import com.pathfinder.view.container.DetailContainerSpec;
import com.pathfinder.view.container.SearchPanelSpec;
import com.pathfinder.view.layout.SteleLayoutSpec;
import com.vaadin.ui.Component;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.UI;

public class DesktopPresenterTest {
	StelePresenter desktopPresenter;
	SteleLayoutSpec desktopLayout;
	DateTimeSpec dateTime;
	FreeRoomViewSpec freeRoom;
	SearchPanelSpec searchPanel;
	DetailContainerSpec detailContainer;
	AppointmentViewSpec appointmentView;
	MenuBarSpec menuBar;

	@Before
	public void setUp() {
		PathfinderUI ui = new PathfinderUI();
		ui.setLocale(Locale.GERMAN);
		UI.setCurrent(ui);

		desktopPresenter = new StelePresenter();
		desktopLayout = desktopPresenter.getDesktopLayoutView();

		HasComponents rootLayout = (HasComponents) ((HasComponents) desktopLayout)
				.iterator().next();
		Iterator<Component> iterator = rootLayout.iterator();
		while (iterator.hasNext()) {
			Component component = iterator.next();
			if (component instanceof DateTimeSpec) {
				dateTime = (DateTimeSpec) component;
			} else if (component instanceof FreeRoomViewSpec) {
				freeRoom = (FreeRoomViewSpec) component;
			} else if (component instanceof SearchPanelSpec) {
				searchPanel = (SearchPanelSpec) component;
			} else if (component instanceof DetailContainerSpec) {
				detailContainer = (DetailContainerSpec) component;
			} else if (component instanceof AppointmentViewSpec) {
				appointmentView = (AppointmentViewSpec) component;
			} else if (component instanceof MenuBarSpec) {
				menuBar = (MenuBarSpec) component;
			}
		}

		Assert.assertTrue(dateTime != null);
		Assert.assertTrue(freeRoom != null);
		Assert.assertTrue(searchPanel != null);
		Assert.assertTrue(detailContainer != null);
		Assert.assertTrue(appointmentView != null);
		Assert.assertTrue(menuBar != null);
	}

	@Test
	public void addKeyboardKeyToSearchStringTest() {
		desktopPresenter.buttonClick(I);
		desktopPresenter.buttonClick(K);
		assertEquals("IK", desktopPresenter.getSearchString());
	}

	@Test
	public void deleteKeyboardKeyFromSearchStringTest() {
		desktopPresenter.buttonClick(I);
		desktopPresenter.buttonClick(K);
		desktopPresenter.buttonClick(DELETE);
		assertEquals("I", desktopPresenter.getSearchString());
	}

	@Test
	public void clearSearchStringTest() {
		desktopPresenter.buttonClick(I);
		desktopPresenter.buttonClick(K);
		desktopPresenter.clearSearchString();
		assertEquals("", desktopPresenter.getSearchString());
	}

	@Test
	public void complexKeyboardKeyTest() {

		desktopPresenter.buttonClick(L);
		desktopPresenter.buttonClick(P);
		assertEquals("LP", desktopPresenter.getSearchString());

		desktopPresenter.buttonClick(DELETE);
		desktopPresenter.buttonClick(DELETE);
		desktopPresenter.buttonClick(DELETE);
		desktopPresenter.buttonClick(DELETE);
		assertEquals("", desktopPresenter.getSearchString());

		desktopPresenter.buttonClick(T);
		desktopPresenter.buttonClick(AE);
		desktopPresenter.buttonClick(S);
		assertEquals("TÄS", desktopPresenter.getSearchString());

		desktopPresenter.buttonClick(SPACE);
		assertEquals("TÄS ", desktopPresenter.getSearchString());

		desktopPresenter.buttonClick(SPACE);
		desktopPresenter.buttonClick(SPACE);
		assertEquals("TÄS   ", desktopPresenter.getSearchString());

		desktopPresenter.buttonClick(KeyboardId.RIGHT);
		desktopPresenter.buttonClick(KeyboardId.RIGHT);
		desktopPresenter.buttonClick(KeyboardId.RIGHT);
		desktopPresenter.buttonClick(KeyboardId.RIGHT);
		desktopPresenter.buttonClick(KeyboardId.RIGHT);
		desktopPresenter.buttonClick(KeyboardId.DELETE);
		assertEquals("TÄS  ", desktopPresenter.getSearchString());

		desktopPresenter.buttonClick(UE);
		desktopPresenter.buttonClick(UE);
		desktopPresenter.buttonClick(LEFT);
		desktopPresenter.buttonClick(X);
		assertEquals("TÄS  ÜXÜ", desktopPresenter.getSearchString());

		desktopPresenter.buttonClick(LEFT);
		desktopPresenter.buttonClick(LEFT);
		desktopPresenter.buttonClick(LEFT);
		desktopPresenter.buttonClick(LEFT);
		desktopPresenter.buttonClick(LEFT);
		desktopPresenter.buttonClick(LEFT);
		desktopPresenter.buttonClick(LEFT);
		desktopPresenter.buttonClick(LEFT);
		desktopPresenter.buttonClick(LEFT);
		desktopPresenter.buttonClick(LEFT);
		desktopPresenter.buttonClick(LEFT);
		desktopPresenter.buttonClick(OE);
		assertEquals("ÖTÄS  ÜXÜ", desktopPresenter.getSearchString());

		desktopPresenter.buttonClick(RIGHT);
		desktopPresenter.buttonClick(RIGHT);
		desktopPresenter.buttonClick(RIGHT);
		desktopPresenter.buttonClick(M);
		assertEquals("ÖTÄSM  ÜXÜ", desktopPresenter.getSearchString());
	}

}