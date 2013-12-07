package com.pathfinder.view.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.pathfinder.view.listener.SearchFieldViewListenerSpec;
import com.vaadin.ui.TextField;

public class SearchField extends TextField implements SearchFieldSpec {

	private List<SearchFieldViewListenerSpec> listener = new ArrayList<SearchFieldViewListenerSpec>();

	public SearchField() {
		this.init();
	}

	private void init() {
	}

	@Override
	public void updateTranslations(Locale locale) {
		// TODO Auto-generated method stub
	}

	@Override
	public void addSearchFieldListener(SearchFieldViewListenerSpec listener) {
		this.listener.add(listener);
	}

}
