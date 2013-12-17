package com.pathfinder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.CustomizedSystemMessages;
import com.vaadin.server.SystemMessages;
import com.vaadin.server.SystemMessagesInfo;
import com.vaadin.server.SystemMessagesProvider;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinServletService;

/**
 * Default Servlet and entry point for the Application
 * 
 * @author tim
 * 
 */
@WebServlet(value = "/*", asyncSupported = true)
@VaadinServletConfiguration(productionMode = false, ui = PathfinderUI.class, widgetset = "com.pathfinder.widgetset.PathfinderWidgetset")
public class PathfinderServlet extends VaadinServlet {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.server.VaadinServlet#servletInitialized()
	 */
	@Override
	protected void servletInitialized() throws ServletException {
		super.servletInitialized();

		setDefaultSystemMessages(getService());
	}

	/**
	 * Sets default system messages according to the desirable locale
	 * 
	 * @param service
	 *            the default servlet's service
	 */
	private void setDefaultSystemMessages(VaadinServletService service) {
		if (service != null) {
			service.setSystemMessagesProvider(new SystemMessagesProvider() {

				@Override
				public SystemMessages getSystemMessages(
						SystemMessagesInfo systemMessagesInfo) {

					CustomizedSystemMessages messages = new CustomizedSystemMessages();
					disableAllMessagesSystemMessagesAndLoadUrl(messages);
					return messages;
				}

			});
		}
	}

	/**
	 * Disables SessionExpired-, CommunicationError-, InternalError- and
	 * OutOfSync-Messages and instead redirects directly to the root url
	 * 
	 * @param messages
	 *            customized system messages object which shall be used to set
	 *            the disabled messages
	 */
	private void disableAllMessagesSystemMessagesAndLoadUrl(
			CustomizedSystemMessages messages) {
		messages.setSessionExpiredNotificationEnabled(false);
		messages.setCommunicationErrorNotificationEnabled(false);
		messages.setInternalErrorNotificationEnabled(false);
		messages.setOutOfSyncNotificationEnabled(false);
	}
}
