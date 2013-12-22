/**
 * This is the presenter from the Keyboard. 
 * 
 * @author Myracle, Alex
 */

package com.pathfinder.presenter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pathfinder.PathfinderUI;
import com.pathfinder.model.CourseModel;
import com.pathfinder.model.KeyboardModel;
import com.pathfinder.model.PersonModel;
import com.pathfinder.model.PoiModel;
import com.pathfinder.model.RoomModel;
import com.pathfinder.view.components.AccordionView;
import com.pathfinder.view.components.Keyboard;
import com.pathfinder.view.components.KeyboardId;
import com.pathfinder.view.components.SearchField;
import com.pathfinder.view.container.SearchPanel;
import com.pathfinder.view.listener.KeyboardViewListenerSpec;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;

/**
 * Presenter which handles keyboard and search logic
 * 
 */
public class SearchPanelPresenter implements KeyboardViewListenerSpec,
		SearchPanelPresenterSpec {

	private static final Logger logger = LogManager
			.getLogger(PathfinderUI.class.getName());

	private final AccordionView accordionView = new AccordionView();
	private final Keyboard keyboard = new Keyboard();
	private final SearchField searchField = new SearchField();
	private final SearchPanel searchPanel = new SearchPanel(accordionView,
			keyboard, searchField);

	private final BeanFieldGroup<KeyboardModel> binder = new BeanFieldGroup<KeyboardModel>(
			KeyboardModel.class);

	public SearchPanelPresenter() {
		this.keyboard.addListener(this);
		this.binder.setBuffered(false);
		this.binder.setItemDataSource(new KeyboardModel());
		this.binder.bind(searchField, KeyboardModel.PROPERTY_SEARCHSTRING);

		this.accordionView
				.addItemClickListenerRoomTable(new TableClickListener());
		this.accordionView
				.addItemClickListenerCourseTable(new TableClickListener());
		this.accordionView
				.addItemClickListenerPersonTable(new TableClickListener());
		this.accordionView
				.addItemClickListenerPoiTable(new TableClickListener());
	}

	// Keyboard ClickListener
	@Override
	public void buttonClick(KeyboardId keyId) {

		KeyboardId id = (KeyboardId) keyId;
		switch (id) {
		case DELETE:
			deleteKeyFromSearchString();
			break;
		case SPACE:
			addKeybordKeyToSearchString(" ");
			break;
		case LEFT:
			setChangePosCounter(getChangePosCounter() - 1);
			break;
		case RIGHT:
			setChangePosCounter(getChangePosCounter() + 1);
			break;
		default:
			addKeybordKeyToSearchString(keyId.getLabel());
			break;
		}

		accordionView.addFilters(getSearchString());
	}

	class TableClickListener implements ItemClickListener {
		@Override
		public void itemClick(ItemClickEvent event) {
			Object object = event.getItemId();
			if (object instanceof RoomModel) {
				logger.trace("Room was clicked");
			} else if (object instanceof CourseModel) {
				logger.trace("Course was clicked");
			} else if (object instanceof PersonModel) {
				logger.trace("Person was clicked");
			} else if (object instanceof PoiModel) {
				logger.trace("Poi was clicked");
			} else {
				logger.trace("Unknown item was clicked");
			}
		}
	}

	public void addKeybordKeyToSearchString(String key) {
		int oldCursorPosition = getChangePosCounter();

		StringBuilder newSearchString = new StringBuilder(getSearchString());

		if (oldCursorPosition < newSearchString.length()
				&& oldCursorPosition >= 0) {
			newSearchString.insert(oldCursorPosition, key);
		} else {
			newSearchString.append(key);
		}
		setSearchString(newSearchString.toString());

		searchField.focus();
		setChangePosCounter(oldCursorPosition + 1);

	}

	public void deleteKeyFromSearchString() {
		int oldCursorPosition = getChangePosCounter();

		StringBuilder newSearchString = new StringBuilder(getSearchString());

		if (oldCursorPosition > 0
				&& oldCursorPosition <= newSearchString.length()) {
			newSearchString.deleteCharAt(oldCursorPosition - 1);

			setSearchString(newSearchString.toString());

			searchField.focus();
			setChangePosCounter(oldCursorPosition - 1);
		} else {
			searchField.focus();
		}
	}

	public void clearSearchString() {
		setSearchString("");
	}

	@Override
	public void setRoomContainer(BeanItemContainer<RoomModel> beanItemContainer) {
		accordionView.setRoomContainer(beanItemContainer);
	}

	@Override
	public void setCourseContainer(
			BeanItemContainer<CourseModel> beanItemContainer) {
		accordionView.setCourseContainer(beanItemContainer);
	}

	@Override
	public void setPersonContainer(
			BeanItemContainer<PersonModel> beanItemContainer) {
		accordionView.setPersonContainer(beanItemContainer);
	}

	@Override
	public void setPoiContainer(BeanItemContainer<PoiModel> beanItemContainer) {
		accordionView.setPoiContainer(beanItemContainer);
	}

	@Override
	public SearchPanel getSearchPanel() {
		return this.searchPanel;
	}

	// TODO Why? Is this necessary?
	public Keyboard getKeyboard() {
		return this.keyboard;
	}

	public int getChangePosCounter() {
		return searchField.getCursorPosition();
	}

	public void setChangePosCounter(int cursorPosition) {
		if (cursorPosition >= 0 && cursorPosition <= getSearchString().length())
			searchField.setCursorPosition(cursorPosition);
	}

	@Override
	public String getSearchString() {
		String returnString = (String) binder.getItemDataSource()
				.getItemProperty(KeyboardModel.PROPERTY_SEARCHSTRING)
				.getValue();
		if (returnString == null)
			returnString = "";
		return returnString;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pathfinder.presenter.SearchPanelPresenterSpec#setSearchString(java
	 * .lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void setSearchString(String value) {
		binder.getItemDataSource()
				.getItemProperty(KeyboardModel.PROPERTY_SEARCHSTRING)
				.setValue(value);
	}
}