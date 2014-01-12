package com.pathfinder.view.components;

import java.util.List;

import com.pathfinder.model.Attribut;

/**
 * DetailSpec
 * 
 * @author alexh
 * 
 */
public interface DetailInfoSpec extends ComponentSpec {
	void addDetails(List<Attribut> resourceDetails);

	void removeDetails();
}
