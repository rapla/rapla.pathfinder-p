package com.pathfinder.presenter;

import java.util.Iterator;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Before;

import com.pathfinder.PathfinderUI;
import com.pathfinder.view.DateTimeSpec;
import com.pathfinder.view.FreeRoomViewSpec;
import com.pathfinder.view.MenuBarSpec;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Component;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.UI;

public class StelePresenterTest {
	MainPresenter mainPresenter;
	AbstractLayout desktopLayout;
	DateTimeSpec dateTime;
	FreeRoomViewSpec freeRoom;
	MenuBarSpec menuBar;

	@Before
	public void setUp() {
		PathfinderUI ui = new PathfinderUI();
		ui.setLocale(Locale.GERMAN);
		UI.setCurrent(ui);

		mainPresenter = new MainPresenter();
		desktopLayout = mainPresenter.getSteleLayoutView();

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
					}
				}
			}
		}

		Assert.assertTrue(dateTime != null);
		Assert.assertTrue(freeRoom != null);
		Assert.assertTrue(menuBar != null);
	}

	//
	// @Test
	// public void addKeyboardKeyToSearchStringTest() {
	// stelePresenter.keyboardButtonClick(I);
	// stelePresenter.keyboardButtonClick(K);
	// assertEquals("IK", stelePresenter.getSearchString());
	// }
	//
	// @Test
	// public void deleteKeyboardKeyFromSearchStringTest() {
	// stelePresenter.keyboardButtonClick(I);
	// stelePresenter.keyboardButtonClick(K);
	// stelePresenter.keyboardButtonClick(DELETE);
	// assertEquals("I", stelePresenter.getSearchString());
	// }
	//
	// @Test
	// public void clearSearchStringTest() {
	// stelePresenter.keyboardButtonClick(I);
	// stelePresenter.keyboardButtonClick(K);
	// stelePresenter.clearSearchString();
	// assertEquals("", stelePresenter.getSearchString());
	// }
	//
	// @Test
	// public void complexKeyboardKeyTest() {
	//
	// stelePresenter.keyboardButtonClick(L);
	// stelePresenter.keyboardButtonClick(P);
	// assertEquals("LP", stelePresenter.getSearchString());
	//
	// stelePresenter.keyboardButtonClick(DELETE);
	// stelePresenter.keyboardButtonClick(DELETE);
	// stelePresenter.keyboardButtonClick(DELETE);
	// stelePresenter.keyboardButtonClick(DELETE);
	// assertEquals("", stelePresenter.getSearchString());
	//
	// stelePresenter.keyboardButtonClick(T);
	// stelePresenter.keyboardButtonClick(AE);
	// stelePresenter.keyboardButtonClick(S);
	// assertEquals("TÄS", stelePresenter.getSearchString());
	//
	// stelePresenter.keyboardButtonClick(SPACE);
	// assertEquals("TÄS ", stelePresenter.getSearchString());
	//
	// stelePresenter.keyboardButtonClick(SPACE);
	// stelePresenter.keyboardButtonClick(SPACE);
	// assertEquals("TÄS   ", stelePresenter.getSearchString());
	//
	// stelePresenter.keyboardButtonClick(KeyboardId.RIGHT);
	// stelePresenter.keyboardButtonClick(KeyboardId.RIGHT);
	// stelePresenter.keyboardButtonClick(KeyboardId.RIGHT);
	// stelePresenter.keyboardButtonClick(KeyboardId.RIGHT);
	// stelePresenter.keyboardButtonClick(KeyboardId.RIGHT);
	// stelePresenter.keyboardButtonClick(KeyboardId.DELETE);
	// assertEquals("TÄS  ", stelePresenter.getSearchString());
	//
	// stelePresenter.keyboardButtonClick(UE);
	// stelePresenter.keyboardButtonClick(UE);
	// stelePresenter.keyboardButtonClick(LEFT);
	// stelePresenter.keyboardButtonClick(X);
	// assertEquals("TÄS  ÜXÜ", stelePresenter.getSearchString());
	//
	// stelePresenter.keyboardButtonClick(LEFT);
	// stelePresenter.keyboardButtonClick(LEFT);
	// stelePresenter.keyboardButtonClick(LEFT);
	// stelePresenter.keyboardButtonClick(LEFT);
	// stelePresenter.keyboardButtonClick(LEFT);
	// stelePresenter.keyboardButtonClick(LEFT);
	// stelePresenter.keyboardButtonClick(LEFT);
	// stelePresenter.keyboardButtonClick(LEFT);
	// stelePresenter.keyboardButtonClick(LEFT);
	// stelePresenter.keyboardButtonClick(LEFT);
	// stelePresenter.keyboardButtonClick(LEFT);
	// stelePresenter.keyboardButtonClick(OE);
	// assertEquals("ÖTÄS  ÜXÜ", stelePresenter.getSearchString());
	//
	// stelePresenter.keyboardButtonClick(RIGHT);
	// stelePresenter.keyboardButtonClick(RIGHT);
	// stelePresenter.keyboardButtonClick(RIGHT);
	// stelePresenter.keyboardButtonClick(M);
	// assertEquals("ÖTÄSM  ÜXÜ", stelePresenter.getSearchString());
	// }

	@Before
	public void initialize() {
		// TODO
		// ResourceModel model = new ResourceModel("Name", "Link", "Id",
		// new String[] { "Searchfield 1", "Searchfield 2" });
		// detailContainer = new DetailContainer<RoomModel>(RoomModel.class,
		// new BeanItem<RoomModel>(model), "");
	}
}