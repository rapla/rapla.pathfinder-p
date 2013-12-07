package com.pathfinder.presenter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.pathfinder.model.KeyboardModel;
import com.pathfinder.view.components.KeyboardView;

public class KeyboardPresenterTest {
	KeyboardModel keyboardModel;
	KeyboardView keyboardView;
	KeyboardPresenter keyboardPresenter;

	@Before
	public void setUp() {
		keyboardModel = new KeyboardModel();
		keyboardView = new KeyboardView();
		keyboardPresenter = new KeyboardPresenter(keyboardModel, keyboardView);
	}

	@Test
	public void addKeyboardKeyToSearchStringTest() {
		keyboardPresenter.buttonClick("I");
		keyboardPresenter.buttonClick("K");
		assertEquals("IK", keyboardModel.getSearchString());
	}

	@Test
	public void deleteKeyboardKeyFromSearchStringTest() {
		keyboardPresenter.buttonClick("I");
		keyboardPresenter.buttonClick("K");
		keyboardPresenter.buttonClick("DELETE");
		assertEquals("I", keyboardModel.getSearchString());
	}

	@Test
	public void clearSearchStringTest() {
		keyboardPresenter.buttonClick("I");
		keyboardPresenter.buttonClick("K");
		keyboardPresenter.clearSearchString();
		assertEquals("", keyboardModel.getSearchString());
	}

}
