package com.pathfinder.view.container;

import java.util.List;

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
	}

	@Override
	public void addDetails(ResourceModel resource,
			List<Attribut> resourceDetails, List<EventModel> resourceEvents) {
		this.removeDetails();

		detailInfo.addDetails(resourceDetails);
		detailEvents.setEvents(resourceEvents);
		if ("room".equals(resource.getType())) {
			for (Attribut attribut : resourceDetails) {
				if ("Raum".equals(attribut.getLabel())) {
					// TODO dataLoader.getDhbwEntryPoint() +
					detailImage.setImage(attribut.getValue());
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
			// TODO: Load events from DataLoader
			// detailEvents.setEvents();
		}
	}
}