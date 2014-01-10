package com.pathfinder.view.container;

import com.pathfinder.model.ResourceModel;
import com.pathfinder.view.components.ComponentSpec;

import de.vksi.c4j.ContractReference;

@ContractReference(DetailContainerSpecContract.class)
public interface DetailContainerSpec extends ComponentSpec {
	void addDetails(ResourceModel resourceModel);

	void removeDetails();

	void hideDetailContainer();

	void showDetailContainer();
}
