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
public class Detail<T> extends CustomComponent implements DetailSpec {
	private Class<T> typeParameterClass = null;
	private BeanItem<T> beanItem = null;
	private List<DetailViewListenerSpec> listener = new ArrayList<DetailViewListenerSpec>();

	public Detail(Class classFile, BeanItem beanItem) {
		this.typeParameterClass = typeParameterClass;
		this.beanItem = beanItem;
	}

	@Override
	public void refreshDetails() {

		BeanFieldGroup<T> binder = new BeanFieldGroup<T>(typeParameterClass);
		binder.buildAndBindMemberFields(beanItem);
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
