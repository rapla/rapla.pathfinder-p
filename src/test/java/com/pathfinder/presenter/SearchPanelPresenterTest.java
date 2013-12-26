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

public class SearchPanelPresenterTest {
	KeyboardModel keyboardModel;
	SearchPanelPresenter searchPanelPresenter;

	@Before
	public void setUp() {
		searchPanelPresenter = new SearchPanelPresenter();
	}

	@Test
	public void addKeyboardKeyToSearchStringTest() {
		searchPanelPresenter.buttonClick(I);
		searchPanelPresenter.buttonClick(K);
		assertEquals("IK", searchPanelPresenter.getSearchString());
	}

	@Test
	public void deleteKeyboardKeyFromSearchStringTest() {
		searchPanelPresenter.buttonClick(I);
		searchPanelPresenter.buttonClick(K);
		searchPanelPresenter.buttonClick(DELETE);
		assertEquals("I", searchPanelPresenter.getSearchString());
	}

	@Test
	public void clearSearchStringTest() {
		searchPanelPresenter.buttonClick(I);
		searchPanelPresenter.buttonClick(K);
		searchPanelPresenter.clearSearchString();
		assertEquals("", searchPanelPresenter.getSearchString());
	}

	@Test
	public void complexKeyboardKeyTest() {

		searchPanelPresenter.buttonClick(L);
		searchPanelPresenter.buttonClick(P);
		assertEquals("LP", searchPanelPresenter.getSearchString());

		searchPanelPresenter.buttonClick(DELETE);
		searchPanelPresenter.buttonClick(DELETE);
		searchPanelPresenter.buttonClick(DELETE);
		searchPanelPresenter.buttonClick(DELETE);
		assertEquals("", searchPanelPresenter.getSearchString());

		searchPanelPresenter.buttonClick(T);
		searchPanelPresenter.buttonClick(AE);
		searchPanelPresenter.buttonClick(S);
		assertEquals("TÄS", searchPanelPresenter.getSearchString());

		searchPanelPresenter.buttonClick(SPACE);
		assertEquals("TÄS ", searchPanelPresenter.getSearchString());

		searchPanelPresenter.buttonClick(SPACE);
		searchPanelPresenter.buttonClick(SPACE);
		assertEquals("TÄS   ", searchPanelPresenter.getSearchString());

		searchPanelPresenter.buttonClick(KeyboardId.RIGHT);
		searchPanelPresenter.buttonClick(KeyboardId.RIGHT);
		searchPanelPresenter.buttonClick(KeyboardId.RIGHT);
		searchPanelPresenter.buttonClick(KeyboardId.RIGHT);
		searchPanelPresenter.buttonClick(KeyboardId.RIGHT);
		searchPanelPresenter.buttonClick(KeyboardId.DELETE);
		assertEquals("TÄS  ", searchPanelPresenter.getSearchString());

		searchPanelPresenter.buttonClick(UE);
		searchPanelPresenter.buttonClick(UE);
		searchPanelPresenter.buttonClick(LEFT);
		searchPanelPresenter.buttonClick(X);
		assertEquals("TÄS  ÜXÜ", searchPanelPresenter.getSearchString());

		searchPanelPresenter.buttonClick(LEFT);
		searchPanelPresenter.buttonClick(LEFT);
		searchPanelPresenter.buttonClick(LEFT);
		searchPanelPresenter.buttonClick(LEFT);
		searchPanelPresenter.buttonClick(LEFT);
		searchPanelPresenter.buttonClick(LEFT);
		searchPanelPresenter.buttonClick(LEFT);
		searchPanelPresenter.buttonClick(LEFT);
		searchPanelPresenter.buttonClick(LEFT);
		searchPanelPresenter.buttonClick(LEFT);
		searchPanelPresenter.buttonClick(LEFT);
		searchPanelPresenter.buttonClick(OE);
		assertEquals("ÖTÄS  ÜXÜ", searchPanelPresenter.getSearchString());

		searchPanelPresenter.buttonClick(RIGHT);
		searchPanelPresenter.buttonClick(RIGHT);
		searchPanelPresenter.buttonClick(RIGHT);
		searchPanelPresenter.buttonClick(M);
		assertEquals("ÖTÄSM  ÜXÜ", searchPanelPresenter.getSearchString());
	}

}
