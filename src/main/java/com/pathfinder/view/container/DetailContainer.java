package com.pathfinder.view.container;

import com.pathfinder.model.CourseModel;
import com.pathfinder.model.PersonModel;
import com.pathfinder.model.PoiModel;
import com.pathfinder.model.RoomModel;
import com.pathfinder.view.components.DetailImage;
import com.pathfinder.view.components.DetailInfo;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

public class DetailContainer<T> extends CustomComponent implements
		DetailContainerSpec {

	private final VerticalLayout vertical = new VerticalLayout();
	private DetailInfo<?> detailInfo = null;
	private DetailImage detailImage = null;

	public DetailContainer(Class<T> beanType, BeanItem<T> beanItem,
			String imageSource) {
		this.detailInfo = new DetailInfo<T>(beanType, beanItem);
		// this.detailImage = new DetailImage(caption, imageSource);
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
	public void hideDetailContainer() {
		this.setVisible(false);
	}

	@Override
	public void showDetailContainer() {
		this.setVisible(true);
	}

	@Override
	public void addRoomDetails(Class<RoomModel> clazz) {
		// TODO Auto-generated method stub
	}

	@Override
	public void addCourseDetails(Class<CourseModel> clazz) {
		// TODO Auto-generated method stub
	}

	@Override
	public void addPersonDetails(Class<PersonModel> clazz) {
		// TODO Auto-generated method stub
	}

	@Override
	public void addPoiDetails(Class<PoiModel> clazz) {
		// TODO Auto-generated method stub
	}

	@Override
	public void updateTranslations() {
		// TODO Auto-generated method stub
	}
}