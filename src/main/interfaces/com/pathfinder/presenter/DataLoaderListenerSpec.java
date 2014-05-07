/**
 * 
 */
package com.pathfinder.presenter;

/**
 * Objects which register themselves as listener to the {@link DataLoader}, have
 * to implement this interface to get notified, if data changed
 * 
 * @author tim
 * 
 */
public interface DataLoaderListenerSpec {

	void dataUpdated();

	boolean isTimeToGetRemoved();

	void destroy();
}
