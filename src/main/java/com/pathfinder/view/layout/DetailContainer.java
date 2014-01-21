package com.pathfinder.view.layout;

import java.util.Date;

import com.pathfinder.model.Attribut;
import com.pathfinder.model.EventModel;
import com.pathfinder.model.ResourceModel;
import com.pathfinder.model.ResourceModel.ResourceType;
import com.pathfinder.presenter.DataLoader;
import com.pathfinder.presenter.DataLoaderSpec;
import com.pathfinder.view.components.DetailEvents;
import com.pathfinder.view.components.DetailEventsSpec;
import com.pathfinder.view.components.DetailImage;
import com.pathfinder.view.components.DetailImageSpec;
import com.pathfinder.view.components.DetailInfo;
import com.pathfinder.view.components.DetailInfoSpec;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * Defines the layout for the DetailContainer: DetailInfo and DetailImage
 * 
 * @author alexh
 * 
 */
public class DetailContainer extends CustomComponent implements
		DetailContainerSpec {
	private final DataLoaderSpec dataLoader = DataLoader.getInstance();
	private final VerticalLayout layout = new VerticalLayout();
	private final HorizontalLayout infoEventsLayout = new HorizontalLayout();
	private final DetailInfoSpec detailInfo = new DetailInfo();
	private final DetailImageSpec detailImage = new DetailImage();
	private final DetailEventsSpec detailEvents = new DetailEvents();
	private ResourceModel resource = null;

	public DetailContainer() {
		this.buildLayout();
		this.setCompositionRoot(layout);
	}

	private void buildLayout() {
		this.hideDetailContainer();
		layout.addComponent(detailImage);
		infoEventsLayout.addComponent(detailInfo);
		infoEventsLayout.addComponent(detailEvents);
		layout.addComponent(infoEventsLayout);

		infoEventsLayout.setSizeFull();
		detailInfo.setSizeFull();
		detailEvents.setSizeFull();
		infoEventsLayout.setExpandRatio(detailInfo, 0.4f);
		infoEventsLayout.setExpandRatio(detailEvents, 0.4f);
		infoEventsLayout.setComponentAlignment(detailEvents,
				Alignment.MIDDLE_RIGHT);
	}

	@Override
	public void addDetails(ResourceModel resource,
			BeanItemContainer<Attribut> resourceDetails) {
		this.resource = resource;

		detailInfo.addDetails(resourceDetails);

		if ((ResourceType.ROOM.toString()).equals(resource.getType())) {
			for (Attribut attribut : resourceDetails.getItemIds()) {
				if ("Raum".equals(attribut.getLabel())) {
					detailImage.setImage(dataLoader.getDhbwEntryPoint()
							+ attribut.getValue());
				}
			}
		}
	}

	@Override
	public void removeDetails() {
		detailInfo.removeDetails();
		detailEvents.removeEvents();
		detailImage.removeImage();
		this.resource = null;
	}

	@Override
	public void hideDetailContainer() {
		this.setVisible(false);
	}

	@Override
	public void showDetailContainer() {
		this.setVisible(true);
	}

	@Override
	public void addCalendarListener(Listener listener) {
		detailEvents.addCalendarListener(listener);
	}

	@Override
	public void updateCalenarEvents(
			BeanItemContainer<EventModel> resourceEvents, Date calendarStartDate) {
		detailEvents.setEvents(resourceEvents, calendarStartDate);
	}

	@Override
	public void updateTranslations() {
		if (resource != null) {
			detailInfo.removeDetails();
			detailInfo.addDetails(dataLoader.getResourceDetails(
					resource.getId(), UI.getCurrent().getLocale()));
		}
		detailEvents.updateTranslations();
	}
}