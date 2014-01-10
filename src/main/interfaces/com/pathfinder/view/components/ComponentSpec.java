package com.pathfinder.view.components;

import com.pathfinder.view.TranslatabelSpec;
import com.vaadin.ui.Component;

import de.vksi.c4j.ContractReference;

/**
 * ComponentSpec - Defines the methods for all component classes
 * 
 * @author alexh
 * 
 */
@ContractReference(ComponentSpecContract.class)
public interface ComponentSpec extends TranslatabelSpec, Component {

}