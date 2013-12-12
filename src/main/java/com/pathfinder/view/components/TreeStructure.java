package com.pathfinder.view.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.pathfinder.view.listener.TreeStructureViewListenerSpec;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.CustomComponent;

public class TreeStructure extends CustomComponent implements TreeStructureSpec {

	private final Accordion accordion = new Accordion();
	
	private List<TreeStructureViewListenerSpec> listener = new ArrayList<TreeStructureViewListenerSpec>();

	public TreeStructure() {
		init();
		setCompositionRoot(accordion);
	}

	private void init() {
		accordion.setSizeFull();
	}

	@Override
	public void updateTranslations(Locale locale) {
		// TODO Auto-generated method stub
	}

	@Override
	public void addTreeStructureSpecListener(
			TreeStructureViewListenerSpec listener) {
		this.listener.add(listener);
	}

}
