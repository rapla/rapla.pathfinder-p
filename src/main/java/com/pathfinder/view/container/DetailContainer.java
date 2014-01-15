package com.pathfinder.view.container;

import com.pathfinder.model.Attribut;
import com.pathfinder.model.EventModel;
import com.pathfinder.model.ResourceModel;
import com.pathfinder.presenter.DataLoader;
import com.pathfinder.presenter.DataLoaderSpec;
import com.pathfinder.view.components.DetailEvents;
import com.pathfinder.view.components.DetailEventsSpec;
import com.pathfinder.view.components.DetailImage;
import com.pathfinder.view.components.DetailImageSpec;
import com.pathfinder.view.components.DetailInfo;
import com.pathfinder.view.components.DetailInfoSpec;
import com.vaadin.data.util.BeanItemContainer;
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
		this.addStyling();
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
		infoEventsLayout.setExpandRatio(detailInfo, 0.5f);
		infoEventsLayout.setExpandRatio(detailEvents, 0.5f);
	}

	private void addStyling() {
		// TODO this.setPrimaryStyleName("detail-container");
	}

	@Override
	public void addDetails(ResourceModel resource,
			BeanItemContainer<Attribut> resourceDetails,
			BeanItemContainer<EventModel> resourceEvents) {
		this.removeDetails();

		detailInfo.addDetails(resourceDetails);
		detailEvents.setEvents(resourceEvents);
		if ("room".equals(resource.getType())) {
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
	public void updateTranslations() {
		if (resource != null) {
			detailInfo.removeDetails();
			detailInfo.addDetails(dataLoader.getResourceDetails(
					resource.getId(), UI.getCurrent().getLocale()));
			detailEvents.removeEvents();
			detailEvents.setEvents(dataLoader.getEvent(resource.getId()));
		}
		detailEvents.updateTranslations();
	}
}