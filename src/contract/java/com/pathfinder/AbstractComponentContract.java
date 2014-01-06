/**
 * 
 */
package com.pathfinder;

import static de.vksi.c4j.Condition.ignored;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import com.vaadin.server.ClientMethodInvocation;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Extension;
import com.vaadin.server.Resource;
import com.vaadin.server.ServerRpcManager;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinResponse;
import com.vaadin.shared.communication.SharedState;
import com.vaadin.ui.Component;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.UI;

/**
 * Implements dummy methods for all methods of Interface {@link Component} so
 * that these methods don't have to be implemented in all contract
 * 
 * @author tim
 * 
 */
public class AbstractComponentContract implements Component {

	@Override
	public String getStyleName() {
		// Dummy method stub
		return ignored();
	}

	@Override
	public void setStyleName(String style) {
		// Dummy method stub

	}

	@Override
	public void addStyleName(String style) {
		// Dummy method stub

	}

	@Override
	public void removeStyleName(String style) {
		// Dummy method stub

	}

	@Override
	public String getPrimaryStyleName() {
		// Dummy method stub
		return ignored();
	}

	public void setPrimaryStyleName(String style) {
		// Dummy method stub

	}

	@Override
	public boolean isEnabled() {
		// Dummy method stub
		return false;
	}

	@Override
	public void setEnabled(boolean enabled) {
		// Dummy method stub

	}

	@Override
	public boolean isVisible() {
		// Dummy method stub
		return false;
	}

	@Override
	public void setVisible(boolean visible) {
		// Dummy method stub

	}

	@Override
	public void setParent(HasComponents parent) {
		// Dummy method stub

	}

	@Override
	public HasComponents getParent() {
		// Dummy method stub
		return ignored();
	}

	@Override
	public boolean isReadOnly() {
		// Dummy method stub
		return false;
	}

	@Override
	public void setReadOnly(boolean readOnly) {
		// Dummy method stub

	}

	@Override
	public String getCaption() {
		// Dummy method stub
		return ignored();
	}

	@Override
	public void setCaption(String caption) {
		// Dummy method stub

	}

	@Override
	public Resource getIcon() {
		// Dummy method stub
		return ignored();
	}

	@Override
	public void setIcon(Resource icon) {
		// Dummy method stub

	}

	@Override
	public UI getUI() {
		// Dummy method stub
		return ignored();
	}

	@Override
	public void attach() {
		// Dummy method stub

	}

	@Override
	public Locale getLocale() {
		// Dummy method stub
		return ignored();
	}

	@Override
	public void setId(String id) {
		// Dummy method stub

	}

	@Override
	public String getId() {
		// Dummy method stub
		return ignored();
	}

	@Override
	public String getDescription() {
		// Dummy method stub
		return ignored();
	}

	@Override
	public void addListener(Listener listener) {
		// Dummy method stub

	}

	@Override
	public void removeListener(Listener listener) {
		// Dummy method stub

	}

	@Override
	public void addAttachListener(AttachListener listener) {
		// Dummy method stub

	}

	@Override
	public void removeAttachListener(AttachListener listener) {
		// Dummy method stub

	}

	@Override
	public void addDetachListener(DetachListener listener) {
		// Dummy method stub

	}

	@Override
	public void removeDetachListener(DetachListener listener) {
		// Dummy method stub

	}

	@Override
	public List<ClientMethodInvocation> retrievePendingRpcCalls() {
		// Dummy method stub
		return ignored();
	}

	@Override
	public boolean isConnectorEnabled() {
		// Dummy method stub
		return false;
	}

	@Override
	public Class<? extends SharedState> getStateType() {
		// Dummy method stub
		return ignored();
	}

	@Override
	public void requestRepaint() {
		// Dummy method stub

	}

	@Override
	public void markAsDirty() {
		// Dummy method stub

	}

	@Override
	public void requestRepaintAll() {
		// Dummy method stub

	}

	@Override
	public void markAsDirtyRecursive() {
		// Dummy method stub

	}

	@Override
	public boolean isAttached() {
		// Dummy method stub
		return false;
	}

	@Override
	public void detach() {
		// Dummy method stub

	}

	@Override
	public Collection<Extension> getExtensions() {
		// Dummy method stub
		return ignored();
	}

	@Override
	public void removeExtension(Extension extension) {
		// Dummy method stub

	}

	@Override
	public void beforeClientResponse(boolean initial) {
		// Dummy method stub

	}

	@Override
	public JSONObject encodeState() throws JSONException {
		// Dummy method stub
		return ignored();
	}

	@Override
	public boolean handleConnectorRequest(VaadinRequest request,
			VaadinResponse response, String path) throws IOException {
		// Dummy method stub
		return false;
	}

	@Override
	public ServerRpcManager<?> getRpcManager(String rpcInterfaceName) {
		// Dummy method stub
		return ignored();
	}

	@Override
	public ErrorHandler getErrorHandler() {
		// Dummy method stub
		return ignored();
	}

	@Override
	public void setErrorHandler(ErrorHandler errorHandler) {
		// Dummy method stub

	}

	@Override
	public String getConnectorId() {
		// Dummy method stub
		return ignored();
	}

	@Override
	public float getWidth() {
		// Dummy method stub
		return 0;
	}

	@Override
	public float getHeight() {
		// Dummy method stub
		return 0;
	}

	@Override
	public Unit getWidthUnits() {
		// Dummy method stub
		return ignored();
	}

	@Override
	public Unit getHeightUnits() {
		// Dummy method stub
		return ignored();
	}

	@Override
	public void setHeight(String height) {
		// Dummy method stub

	}

	@Override
	public void setWidth(float width, Unit unit) {
		// Dummy method stub

	}

	@Override
	public void setHeight(float height, Unit unit) {
		// Dummy method stub

	}

	@Override
	public void setWidth(String width) {
		// Dummy method stub

	}

	@Override
	public void setSizeFull() {
		// Dummy method stub

	}

	@Override
	public void setSizeUndefined() {
		// Dummy method stub

	}
}
