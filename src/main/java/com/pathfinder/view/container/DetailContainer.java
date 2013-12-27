package com.pathfinder.view.container;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.pathfinder.model.ResourceModel;
import com.pathfinder.view.components.DetailImage;
import com.pathfinder.view.components.DetailInfo;
import com.pathfinder.view.listener.DetailViewListenerSpec;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

public class DetailContainer<T> extends CustomComponent implements
		DetailContainerSpec {

	private final VerticalLayout vertical = new VerticalLayout();
	private DetailInfo<?> detailInfo = null;
	private DetailImage detailImage = null;
	private List<DetailViewListenerSpec> listener = new ArrayList<DetailViewListenerSpec>();

	public DetailContainer(Class<T> beanType, BeanItem<T> beanItem,
			String imageSource) {
		this.detailInfo = new DetailInfo<T>(beanType, beanItem);
		//this.detailImage = new DetailImage(caption, imageSource);
		this.buildLayout();
	}

	public void buildLayout() {
		this.detailInfo.setSizeFull();
		this.detailImage.setSizeFull();
		this.setSizeFull();
		this.vertical.addComponent(detailInfo);
		this.vertical.addComponent(detailImage);
		this.setCompositionRoot(vertical);
	}

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
