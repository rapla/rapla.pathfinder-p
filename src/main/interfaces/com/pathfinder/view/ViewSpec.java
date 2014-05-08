package com.pathfinder.view;

import com.vaadin.ui.Component;

import de.vksi.c4j.ContractReference;

/**
 * ViewSpec - Defines methods for all layout classes
 * 
 * @author alexh
 * 
 */
@ContractReference(ViewSpecContract.class)
public interface ViewSpec extends TranslatableSpec, DestroyableSpec, Component {
}
