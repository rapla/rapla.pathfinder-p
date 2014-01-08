/**
 * This is the presenter from the Keyboard. 
 * 
 * @author Myracle, Alex
 */

package com.pathfinder.presenter;

import com.pathfinder.model.CourseModel;
import com.pathfinder.model.KeyboardModel;
import com.pathfinder.model.PersonModel;
import com.pathfinder.model.PoiModel;
import com.pathfinder.model.RoomModel;
import com.pathfinder.view.TranslatabelSpec;
import com.pathfinder.view.components.AccordionView;
import com.pathfinder.view.components.AccordionViewSpec;
import com.pathfinder.view.components.Keyboard;
import com.pathfinder.view.components.KeyboardId;
import com.pathfinder.view.components.KeyboardSpec;
import com.pathfinder.view.components.SearchField;
import com.pathfinder.view.components.SearchFieldSpec;
import com.pathfinder.view.container.DetailContainer;
import com.pathfinder.view.container.DetailContainerSpec;
import com.pathfinder.view.container.SearchPanel;
import com.pathfinder.view.container.SearchPanelSpec;
import com.pathfinder.view.listener.KeyboardViewListenerSpec;
import com.pathfinder.view.listener.SearchFieldViewListenerSpec;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

/**
 * Presenter which handles keyboard and search logic
 * 
 */
public class SearchPanelPresenter implements KeyboardViewListenerSpec,
		SearchFieldViewListenerSpec, SearchPanelPresenterSpec, TranslatabelSpec {

	private final AccordionViewSpec accordionView = new AccordionView();
	private final KeyboardSpec keyboard = new Keyboard();
	private final SearchFieldSpec searchField = new SearchField();
	private final SearchPanelSpec searchPanel = new SearchPanel(
			(AccordionView) accordionView, (Keyboard) keyboard,
			(SearchField) searchField);
	private DetailContainerSpec detailContainer = null;

	private final BeanFieldGroup<KeyboardModel> keyboardBinder = new BeanFieldGroup<KeyboardModel>(
			KeyboardModel.class);

	public SearchPanelPresenter() {
		this.keyboard.addListener(this);
		this.keyboardBinder.setBuffered(false);
		this.keyboardBinder.setItemDataSource(new KeyboardModel());
		this.keyboardBinder.bind(searchField.getSearchField(),
				KeyboardModel.PROPERTY_SEARCHSTRING);

		this.accordionView
				.addItemClickListenerRoomTable(new TableClickListener());
		this.accordionView
				.addItemClickListenerCourseTable(new TableClickListener());
		this.accordionView
				.addItemClickListenerPersonTable(new TableClickListener());
		this.accordionView
				.addItemClickListenerPoiTable(new TableClickListener());

		this.searchField
				.addDeleteAllClickListener(new DeleteAllClickListener());
	}

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
	}

	class DeleteAllClickListener implements ClickListener {

		@Override
		public void buttonClick(ClickEvent event) {
			clearSearchString();
		}

	}

	class TableClickListener implements ItemClickListener {
		@Override
		public void itemClick(ItemClickEvent event) {
			Object object = event.getItemId();

			detailContainer = new DetailContainer(object, null);

			// if (object instanceof RoomModel) {
			// logger.trace("Room was clicked - ItemID: "
			// + ((RoomModel) object).getName());
			// accordionView.deselectClickedItem((Table) event.getSource(),
			// event.getItemId());
			// } else if (object instanceof CourseModel) {
			// logger.trace("Course was clicked");
			// } else if (object instanceof PersonModel) {
			// logger.trace("Person was clicked");
			// } else if (object instanceof PoiModel) {
			// logger.trace("Poi was clicked");
			// } else {
			// logger.trace("Unknown item was clicked");
			// }
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

		searchField.getSearchField().focus();
		setChangePosCounter(oldCursorPosition + 1);
	}

	public void deleteKeyFromSearchString() {
		int oldCursorPosition = getChangePosCounter();

		StringBuilder newSearchString = new StringBuilder(getSearchString());

		if (oldCursorPosition > 0
				&& oldCursorPosition <= newSearchString.length()) {
			newSearchString.deleteCharAt(oldCursorPosition - 1);

			setSearchString(newSearchString.toString());

			searchField.getSearchField().focus();
			setChangePosCounter(oldCursorPosition - 1);
		} else {
			searchField.getSearchField().focus();
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
		return (SearchPanel) searchPanel;
	}

	public int getChangePosCounter() {
		return searchField.getSearchField().getCursorPosition();
	}

	public void setChangePosCounter(int cursorPosition) {
		if (cursorPosition >= 0 && cursorPosition <= getSearchString().length())
			searchField.getSearchField().setCursorPosition(cursorPosition);
	}

	@Override
	public String getSearchString() {
		String returnString = (String) keyboardBinder.getItemDataSource()
				.getItemProperty(KeyboardModel.PROPERTY_SEARCHSTRING)
				.getValue();
		if (returnString == null)
			returnString = "";
		return returnString;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setSearchString(String value) {
		keyboardBinder.getItemDataSource()
				.getItemProperty(KeyboardModel.PROPERTY_SEARCHSTRING)
				.setValue(value);
		accordionView.useFiltersForAllTables(getSearchString());
	}

	@Override
	public void updateTranslations() {
		searchPanel.updateTranslations();
	}

	@Override
	public int getCursorPosition() {
		return searchField.getSearchField().getCursorPosition();
	}
}