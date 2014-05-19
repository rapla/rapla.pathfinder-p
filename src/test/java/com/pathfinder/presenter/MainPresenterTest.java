package com.pathfinder.presenter;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.pathfinder.PathfinderUI;
import com.pathfinder.model.Attribute;
import com.pathfinder.model.Device;
import com.pathfinder.model.ResourceModel;
import com.pathfinder.view.DateTimeSpec;
import com.pathfinder.view.FreeRoomViewSpec;
import com.pathfinder.view.MenuBarSpec;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.UI;

public class MainPresenterTest {
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

		mainPresenter = new MainPresenter(ui);
		desktopLayout = mainPresenter.getSteleLayoutView();

	}

	@Test
	public void clearSearchStringTest() {
		mainPresenter.addKeybordKeyToSearchString("T");
		mainPresenter.addKeybordKeyToSearchString("E");
		mainPresenter.addKeybordKeyToSearchString("S");
		mainPresenter.addKeybordKeyToSearchString("T");
		mainPresenter.clearSearchString();
		Assert.assertEquals(0, mainPresenter.getCursorPosition());
		Assert.assertEquals("", mainPresenter.getSearchString());
	}

	@Test
	public void setDeviceSteleTest() {
		Device device = Device.STELE_MIDDLE;
		mainPresenter.setDevice(device);
		Assert.assertNotNull(mainPresenter.getDevice());
	}

	@Test
	public void switchToSearchViewTest() {
		mainPresenter.switchToSearchView();
		Assert.assertTrue(!mainPresenter.getDetailLayout().isVisible());
	}

	@Test
	public void switchToDetailViewTest() {
		mainPresenter.setResource(new ResourceModel());
		mainPresenter.setResourceDetails(new BeanItemContainer<Attribute>(
				Attribute.class));
		mainPresenter.switchToDetailView();
		Assert.assertTrue(mainPresenter.getDetailLayout().isVisible());
	}

	@Test
	public void changeToWheelChairViewTest() {
		mainPresenter.changeToWheelChairView();
		Assert.assertEquals(0, mainPresenter.getLayoutNormal()
				.getComponentCount());
	}

	@Test
	public void changeToNonWheelChairViewTest() {
		mainPresenter.changeToNonWheelChairView();
		Assert.assertEquals(0, mainPresenter.getLayoutWheelChair()
				.getComponentCount());
	}

	@Test
	public void addKeybordKeyToSearchStringTest() {
		mainPresenter.addKeybordKeyToSearchString("K");
		Assert.assertEquals(1, mainPresenter.getCursorPosition());
		mainPresenter.addKeybordKeyToSearchString("E");
		Assert.assertEquals(2, mainPresenter.getCursorPosition());
		mainPresenter.addKeybordKeyToSearchString("Y");
		Assert.assertEquals(3, mainPresenter.getCursorPosition());
		Assert.assertEquals("KEY", mainPresenter.getSearchString());
	}

	@Test
	public void deleteKeyFromSearchStringTest() {
		mainPresenter.addKeybordKeyToSearchString("T");
		mainPresenter.addKeybordKeyToSearchString("E");
		mainPresenter.addKeybordKeyToSearchString("S");
		mainPresenter.addKeybordKeyToSearchString("T");
		Assert.assertEquals(4, mainPresenter.getCursorPosition());
		mainPresenter.deleteKeyFromSearchString();
		Assert.assertEquals(3, mainPresenter.getCursorPosition());
		Assert.assertEquals("TES", mainPresenter.getSearchString());
	}

	@Test
	public void setSearchStringTest() {
		mainPresenter.setSearchString("TEST");
		Assert.assertEquals("TEST", mainPresenter.getSearchString());
	}

	@Test
	public void languageChangedTest() {
		mainPresenter.languageChanged(Locale.GERMAN);
		Assert.assertEquals(Locale.GERMAN, UI.getCurrent().getLocale());
		mainPresenter.languageChanged(Locale.ENGLISH);
		Assert.assertEquals(Locale.ENGLISH, UI.getCurrent().getLocale());
	}

}