/**
 * 
 */
package com.pathfinder.view;

import java.util.List;

import com.pathfinder.model.ResourceModel;
import com.vaadin.ui.Button.ClickListener;

/**
 * @author tim
 * 
 */
public interface EventSelectionViewSpec extends TranslatabelSpec {

	void showEventResourceSelection(List<ResourceModel> resources);

	void addButtonClickListener(ClickListener listener);

	void close();

}