/**
 * 
 */
package com.pathfinder.view.components;

import static de.vksi.c4j.Condition.postCondition;
import static de.vksi.c4j.Condition.preCondition;

import java.util.List;

import com.pathfinder.view.listener.KeyboardViewListenerSpec;

import de.vksi.c4j.Condition;
import de.vksi.c4j.Target;

/**
 * @author tim
 * 
 */
public class KeyboardSpecContract extends ComponentSpecContract implements
		KeyboardSpec {

	@Target
	private KeyboardSpec target;
	private int listenerCountBefore = -5;

	@Override
	public void addListener(KeyboardViewListenerSpec listener) {
		if (preCondition()) {
			assert listener != null : "Listener not null";
			listenerCountBefore = target.getListener().size();
		}
		if (postCondition()) {
			int listenerCountAfter = target.getListener().size();
			assert listenerCountAfter == listenerCountBefore + 1 : "One more listener added";
			listenerCountBefore = -5;
		}
	}

	@Override
	public List<KeyboardViewListenerSpec> getListener() {
		if (postCondition()) {
			List<KeyboardViewListenerSpec> resultList = Condition.result();
			assert resultList != null : "Result list not null";
		}
		return Condition.ignored();
	}

}
