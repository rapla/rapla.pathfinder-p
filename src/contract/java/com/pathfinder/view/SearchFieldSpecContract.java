/**
 * 
 */
package com.pathfinder.view;

import static de.vksi.c4j.Condition.ignored;
import static de.vksi.c4j.Condition.postCondition;
import static de.vksi.c4j.Condition.preCondition;

import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.TextField;

import de.vksi.c4j.ClassInvariant;
import de.vksi.c4j.Condition;
import de.vksi.c4j.Target;

/**
 * @author tim
 * 
 */
public class SearchFieldSpecContract extends ComponentSpecContract implements
		SearchFieldSpec {

	@Target
	private SearchFieldSpec target;
	private int listenerCountBefore = -5;

	@ClassInvariant
	public void classInvariant() {
		// TODO: write invariants if required
	}

	@Override
	public void addSearchFieldTextChangeListener(TextChangeListener listener) {
		if (preCondition()) {
			assert listener != null : "Listener not null";
			assert target.getSearchField() != null : "SearchField-Textfield not null";
			listenerCountBefore = target.getSearchField()
					.getListeners(TextChangeEvent.class).size();
		}
		if (postCondition()) {
			int listenerCountAfter = target.getSearchField()
					.getListeners(TextChangeEvent.class).size();
			assert listenerCountAfter == listenerCountBefore + 1 : "SearchField has one listener more than before";
			listenerCountBefore = -5;
		}
	}

	@Override
	public void addDeleteAllClickListener(ClickListener listener) {
		if (preCondition()) {
			assert listener != null : "Listener not null";
			listenerCountBefore = target.getDeleteAllButton()
					.getListeners(ClickEvent.class).size();
		}
		if (postCondition()) {
			int listenerCountAfter = target.getDeleteAllButton()
					.getListeners(ClickEvent.class).size();
			assert listenerCountAfter == listenerCountBefore + 1 : "DeleteButton has one listener more than before";
			listenerCountBefore = -5;
		}
	}

	@Override
	public TextField getSearchField() {
		if (postCondition()) {
			TextField textField = Condition.result();
			assert textField != null : "Result not null";
		}
		return ignored();
	}

	@Override
	public Button getDeleteAllButton() {
		if (postCondition()) {
			Button button = Condition.result();
			assert button != null : "Result not null";
		}
		return ignored();
	}

	@Override
	public void focusSearchField() {
		// TODO Auto-generated method stub
	}

	@Override
	public int getCursorPosition() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setCursorPosition(int cursorPosition) {
		// TODO Auto-generated method stub
	}

	@Override
	public void hideSearchField() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showSearchField() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSearchFieldValue(String newValue) {
		// TODO Auto-generated method stub
		
	}
}