package com.pathfinder.view.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.pathfinder.translation.TranslationKeys;
import com.pathfinder.translation.Translator;
import com.pathfinder.translation.TranslatorSpec;
import com.pathfinder.view.listener.SearchFieldViewListenerSpec;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class SearchField extends TextField implements SearchFieldSpec {

	private TranslatorSpec translator = Translator.getInstance();
	private List<SearchFieldViewListenerSpec> listener = new ArrayList<SearchFieldViewListenerSpec>();

	public SearchField() {
		this.init();
	}

	private void init() {
		this.setInputPrompt(translator.translate(TranslationKeys.SEARCH_PROMP));
	}

	@Override
	public void updateTranslations(Locale locale) {
		this.setInputPrompt(translator.translate(TranslationKeys.SEARCH_PROMP));
	}

	@Override
	public void addSearchFieldListener(SearchFieldViewListenerSpec listener) {
		this.listener.add(listener);
	}

}
