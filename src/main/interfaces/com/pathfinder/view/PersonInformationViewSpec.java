/**
 * 
 */
package com.pathfinder.view;

/**
 * @author tim
 * 
 */
public interface PersonInformationViewSpec extends TranslatableSpec,
		DestroyableSpec {
	void showInformation(String person, String information);

	void close();
}