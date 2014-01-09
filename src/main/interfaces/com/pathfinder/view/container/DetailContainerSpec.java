package com.pathfinder.view.container;

import com.pathfinder.model.ResourceModel;
import com.pathfinder.view.components.ComponentSpec;

import de.vksi.c4j.ContractReference;

@ContractReference(DetailContainerSpecContract.class)
public interface DetailContainerSpec extends ComponentSpec {
	void hideDetailContainer();

	void showDetailContainer();

	void addDetails(ResourceModel resourceModel);
}
