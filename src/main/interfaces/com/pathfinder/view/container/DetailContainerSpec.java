package com.pathfinder.view.container;

import java.util.List;

import com.pathfinder.model.Attribut;
import com.pathfinder.model.ResourceModel;
import com.pathfinder.view.components.ComponentSpec;

import de.vksi.c4j.ContractReference;

@ContractReference(DetailContainerSpecContract.class)
public interface DetailContainerSpec extends ComponentSpec {
	void addDetails(ResourceModel resource, List<Attribut> resourceDetails);

	void removeDetails();

	void hideDetailContainer();

	void showDetailContainer();
}
