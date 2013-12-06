package com.pathfinder.view;

import com.pathfinder.view.components.FreeRoomView;
import com.pathfinder.view.components.SearchField;
import com.pathfinder.view.components.TimeDate;
import com.pathfinder.view.components.TreeStructure;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

public class SearchGroup extends CustomComponent implements SearchGroupSpec{

	private TreeStructure treeStructure = new TreeStructure();
//	private Keyboard keyboard = new Keyboard();
	private SearchField searchField = new SearchField();

	private VerticalLayout layout = new VerticalLayout();

	public SearchGroup() {
		this.buildLayout();
		this.setCompositionRoot(layout);
	}

	@Override
	public void buildLayout() {
		this.layout.addComponent(treeStructure);
//		this.layout.addComponent(keyboard);
		this.layout.addComponent(searchField);
	}

	@Override
	public void destroyLayout() {
		layout.removeAllComponents();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

}
