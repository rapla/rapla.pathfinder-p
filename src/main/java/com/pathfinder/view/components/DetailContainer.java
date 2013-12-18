package com.pathfinder.view.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.pathfinder.model.GSON_GetResources_LEVEL_2;
import com.pathfinder.view.listener.DetailViewListenerSpec;
import com.vaadin.ui.CustomComponent;

public class DetailContainer extends CustomComponent implements
		DetailContainerSpec {

	private List<DetailViewListenerSpec> listener = new ArrayList<DetailViewListenerSpec>();

	@Override
	public void addRoomDetails(Class<GSON_GetResources_LEVEL_2> clazz) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addPersonDetails(Class<GSON_GetResources_LEVEL_2> clazz) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addCourseDetails(Class<GSON_GetResources_LEVEL_2> clazz) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addPoiDetails(Class<GSON_GetResources_LEVEL_2> clazz) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateTranslations(Locale locale) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addDetailContainerViewListener(DetailViewListenerSpec listener) {
		this.listener.add(listener);
	}

}
