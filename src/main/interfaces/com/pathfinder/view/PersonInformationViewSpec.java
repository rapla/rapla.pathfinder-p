/**
 * 
 */
package com.pathfinder.view;

/**
 * @author tim
 * 
 */
public interface PersonInformationViewSpec extends TranslatabelSpec {
	void showInformation(String person, String information);

	void close();
}