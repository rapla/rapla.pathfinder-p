package com.pathfinder.view.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.pathfinder.view.listener.DetailViewListenerSpec;
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
	private BeanItem<T> beanItem = null;
	
	private List<DetailViewListenerSpec> listener = new ArrayList<DetailViewListenerSpec>();

	public DetailInfo(Class<T> beanType, BeanItem<T> beanItem) {
		this.beanItem = beanItem;
		BeanFieldGroup<T> binder = new BeanFieldGroup<T>(beanType);
		binder.buildAndBindMemberFields(this.beanItem);
	}

	@Override
	public void updateTranslations(Locale locale) {
		// TODO Auto-generated method stub
	}

	@Override
	public void addDetailListener(DetailViewListenerSpec listener) {
		this.listener.add(listener);
	}
}
