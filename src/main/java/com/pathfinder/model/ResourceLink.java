package com.pathfinder.model;

import com.pathfinder.util.translation.Translator;
import com.pathfinder.util.translation.TranslatorSpec;

public class ResourceLink extends Attribute {

	private final static TranslatorSpec TRANSLATOR = Translator.getInstance();

	private ResourceModel resourceModel;
	private ResourceLinkType resourceLinkType;

	public ResourceLink(ResourceModel resourceModel, String key) {
		this.resourceModel = resourceModel;
		this.resourceLinkType = ResourceLinkType.getTypeByString(key);
	}

	public AttributeKey getKey() {
		return AttributeKey.RESOURCE_LINK;
	}

	public String getLabel() {
		return TRANSLATOR.translate(resourceLinkType.getTranslationKey());
	}

	public String getValue() {
		return resourceModel.getName();
	}

	public ResourceModel getResourceModel() {
		return this.resourceModel;
	}
}
