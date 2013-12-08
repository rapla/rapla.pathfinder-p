package com.pathfinder.view.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.pathfinder.model.ResourceModel;
import com.pathfinder.view.listener.DetailViewListenerSpec;
import com.vaadin.ui.CustomComponent;

public class DetailContainer extends CustomComponent implements
		DetailContainerSpec {

	private List<DetailViewListenerSpec> listener = new ArrayList<DetailViewListenerSpec>();

	@Override
	public void addRoomDetails(Class<ResourceModel> clazz) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addPersonDetails(Class<ResourceModel> clazz) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addCourseDetails(Class<ResourceModel> clazz) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addPoiDetails(Class<ResourceModel> clazz) {
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
