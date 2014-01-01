package com.pathfinder.view.components;

import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;

/**
 * Language Menu
 * DropMenu for Changing Language Settings
 * 
 * @author Dennis.Weber
 *
 */

public class LanguageMenu extends CustomComponent implements LanguageMenuSpec {
	private final HorizontalLayout horizontalLayout = new HorizontalLayout();
	private final String[] languages = {"deutsch", "english"};
	
	
	private NativeSelect dropUpMenu = new NativeSelect();
	
	public LanguageMenu() {		
		for(int i=0; i < languages.length; i++){
			dropUpMenu.addItem(languages[i]);
			dropUpMenu.setItemCaption(i, languages[i]);
		}
		dropUpMenu.setNullSelectionAllowed(false);
		dropUpMenu.setValue(languages[0]);
		dropUpMenu.setImmediate(true);
		dropUpMenu.setPrimaryStyleName("languages");
		buildMainLayout();
		setCompositionRoot(horizontalLayout);
	}

	private void buildMainLayout() {
		horizontalLayout.addComponent(dropUpMenu);
	}
	
	public void addValueChangeListener(ValueChangeListener listener){
		this.dropUpMenu.addValueChangeListener(listener);
	}
	
	public String[] getLanguages(){
		return languages;
	}
}
