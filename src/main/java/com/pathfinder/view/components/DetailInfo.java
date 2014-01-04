package com.pathfinder.view.components;

import com.pathfinder.model.PoiModel;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.CustomComponent;

/**
 * Defines the detail view for persons, courses, pois or rooms
 * 
 * @author alexh
 * 
 * @param <T>
 */
public class DetailInfo<T> extends CustomComponent implements DetailInfoSpec {
	//private BeanItem<T> beanItem = null;

	public DetailInfo(Class<T> beanType, BeanItem<T> beanItem) {
		PoiModel poi = new PoiModel("", "", "", new String[]{}, "", "");
		BeanItem<PoiModel> beanItem1 = new BeanItem<PoiModel>(poi);
	//	this.beanItem = beanItem;
		BeanFieldGroup<T> binder = new BeanFieldGroup<T>(beanType);
		binder.buildAndBindMemberFields(beanItem1);
	}

	@Override
	public void updateTranslations() {
		// TODO Auto-generated method stub
	}
}
