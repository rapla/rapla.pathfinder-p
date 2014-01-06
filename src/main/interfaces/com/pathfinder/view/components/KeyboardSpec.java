package com.pathfinder.view.components;

import java.util.List;

import com.pathfinder.view.listener.KeyboardViewListenerSpec;

import de.vksi.c4j.ContractReference;
import de.vksi.c4j.Pure;

@ContractReference(KeyboardSpecContract.class)
public interface KeyboardSpec extends ComponentSpec {
	void addListener(KeyboardViewListenerSpec listener);

	@Pure
	List<KeyboardViewListenerSpec> getListener();
}
