package com.pathfinder.view.components;

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

	public Detail(Class classFile, BeanItem beanItem) {
		this.typeParameterClass = typeParameterClass;
		this.beanItem = beanItem;
	}

	@Override
	public void refreshDetails() {
		
		BeanFieldGroup<T> binder = new BeanFieldGroup<T>(typeParameterClass);
		binder.buildAndBindMemberFields(beanItem);
	}
}
