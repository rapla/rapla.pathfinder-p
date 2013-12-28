package com.pathfinder;

import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pathfinder.presenter.DataLoader;
import com.pathfinder.presenter.DataLoaderSpec;
import com.pathfinder.util.properties.ApplicationProperties;
import com.pathfinder.util.properties.ApplicationPropertiesSpec;
import com.pathfinder.util.properties.PropertiesKey;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.BootstrapFragmentResponse;
import com.vaadin.server.BootstrapListener;
import com.vaadin.server.BootstrapPageResponse;
import com.vaadin.server.CustomizedSystemMessages;
import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.SessionInitListener;
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
@VaadinServletConfiguration(productionMode = false, ui = PathfinderUI.class, widgetset = "com.pathfinder.util.widgetset.PathfinderWidgetset")
public class PathfinderServlet extends VaadinServlet {

	private static final Logger logger = LogManager
			.getLogger(PathfinderServlet.class);

	private ApplicationPropertiesSpec properties = ApplicationProperties
			.getInstance();

	private final DataLoaderSpec dataLoader = new DataLoader();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.server.VaadinServlet#servletInitialized()
	 */
	@Override
	protected void servletInitialized() throws ServletException {
		super.servletInitialized();

		this.setDefaultSystemMessages(getService());
		// TODO This method should only be called if the client is the Stele
		this.addMetaTagForIE10Mode();

		this.scheduleDataLoading();
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

	/**
	 * Adds a meta tag to allow always the IE10 mode if client is the Stele
	 */
	private void addMetaTagForIE10Mode() {
		getService().addSessionInitListener(new SessionInitListener() {

			@Override
			public void sessionInit(SessionInitEvent event) {
				event.getSession().addBootstrapListener(
						new BootstrapListener() {
							@Override
							public void modifyBootstrapFragment(
									BootstrapFragmentResponse response) {
							}

							@Override
							public void modifyBootstrapPage(
									BootstrapPageResponse response) {
								response.getDocument().head()
										.prependElement("meta")
										.attr("http-equiv", "X-UA-Compatible")
										.attr("content", "IE=10;chrome=1");
							}
						});
			}
		});
	}

	/**
	 * Load Data once and start timer to load data asynchronously after specific
	 * interval
	 */
	private void scheduleDataLoading() {

		// Load Data once synchronously
		// TODO Why not directly in the dataloaderTask and start in 0,001 seconds?
		dataLoader.loadAllResources();

		TimerTask dataLoaderTask = new TimerTask() {
			@Override
			public void run() {
				// TODO Why are these trace messages not shown?
				logger.trace("Get new data from the RAPLA-Server");
				dataLoader.loadAllResources();
				logger.trace("Updated data from the RAPLA-Server");
			}
		};

		// Start in 0,001 seconds, is repeated every day (24hours)
		long loadInterval = properties
				.getIntProperty(PropertiesKey.DATA_LOAD_INTERVALL);
		new Timer().schedule(dataLoaderTask, loadInterval, loadInterval);

	}
}
