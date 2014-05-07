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
public interface EventSelectionViewSpec extends TranslatableSpec, DestroyableSpec {

	void showEventResourceSelection(List<ResourceModel> resources);

	void addButtonClickListener(ClickListener listener);

	void close();

}