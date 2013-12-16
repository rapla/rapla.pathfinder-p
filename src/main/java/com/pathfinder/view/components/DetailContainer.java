package com.pathfinder.view.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

import com.pathfinder.model.ResourceModel;
import com.pathfinder.view.listener.DetailViewListenerSpec;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

public class DetailContainer extends CustomComponent implements
		DetailContainerSpec {

	private final VerticalLayout vertical = new VerticalLayout();
	private Detail detail = null;
	private BrowserFrame browserFrame = new BrowserFrame();
	private List<DetailViewListenerSpec> listener = new ArrayList<DetailViewListenerSpec>();

	public DetailContainer(Class classFile, BeanItem beanItem) {
		this.detail = new Detail(classFile, beanItem);
		this.buildLayout();
		this.init();
	}

	public void buildLayout() {
		this.setSizeFull();
		detail.setSizeFull();
		browserFrame.setSizeFull();
		vertical.addComponent(detail);
		vertical.addComponent(browserFrame);
		this.setCompositionRoot(vertical);
	}

	private void init() {
		browserFrame.setAlternateText("");
	}

	public void setSrc(String imageSrc) {
		if (StringUtils.isNotEmpty(imageSrc)) {	
			browserFrame.setSource(new ExternalResource(imageSrc));
		}	
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
