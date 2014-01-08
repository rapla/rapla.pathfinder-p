package com.pathfinder.presenter;

import static com.pathfinder.view.components.KeyboardId.AE;
import static com.pathfinder.view.components.KeyboardId.DELETE;
import static com.pathfinder.view.components.KeyboardId.I;
import static com.pathfinder.view.components.KeyboardId.K;
import static com.pathfinder.view.components.KeyboardId.L;
import static com.pathfinder.view.components.KeyboardId.LEFT;
import static com.pathfinder.view.components.KeyboardId.M;
import static com.pathfinder.view.components.KeyboardId.OE;
import static com.pathfinder.view.components.KeyboardId.P;
import static com.pathfinder.view.components.KeyboardId.RIGHT;
import static com.pathfinder.view.components.KeyboardId.S;
import static com.pathfinder.view.components.KeyboardId.SPACE;
import static com.pathfinder.view.components.KeyboardId.T;
import static com.pathfinder.view.components.KeyboardId.UE;
import static com.pathfinder.view.components.KeyboardId.X;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.pathfinder.model.KeyboardModel;
import com.pathfinder.view.components.KeyboardId;

public class DesktopPresenterTest {
	KeyboardModel keyboardModel;
	DesktopPresenter desktopPresenter;

	@Before
	public void setUp() {
		desktopPresenter = new DesktopPresenter();
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
