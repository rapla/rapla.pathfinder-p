package org.rapla.client;

import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.rapla.ConnectInfo;
import org.rapla.components.util.DateTools;
import org.rapla.entities.domain.Allocatable;
import org.rapla.entities.domain.Appointment;
import org.rapla.entities.domain.AppointmentFormater;
import org.rapla.entities.domain.Reservation;
import org.rapla.facade.ClientFacade;
import org.rapla.framework.RaplaContext;
import org.rapla.framework.RaplaException;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Test_gwt implements EntryPoint {

	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		RaplaGWTClient raplaGWTClient; 
		try {
			raplaGWTClient = new RaplaGWTClient();
		} catch (RaplaException e) {
			e.printStackTrace();
			return;
		}
		final RaplaContext context = raplaGWTClient.getContext();

		final Button sendButton = new Button("Send");
		final TextBox nameField = new TextBox();
		nameField.setText("admin");
		final TextBox passwordField = new PasswordTextBox();
		passwordField.setText("");
		
		final Label errorLabel = new Label();
//		Container c = new TestContainer();
//		provide(c);
//		Test hallo = c.getContext().get( Test.class);
//		hallo.message();
		// We can add style names to widgets
		sendButton.addStyleName("sendButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("nameFieldContainer").add(nameField);
		RootPanel.get("passwordFieldContainer").add(passwordField);
		RootPanel.get("sendButtonContainer").add(sendButton);
		RootPanel.get("errorLabelContainer").add(errorLabel);

		// Focus the cursor on the name field when the app loads
		nameField.setFocus(true);
		nameField.selectAll();

		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);
		final Logger logger = Logger.getLogger("componentClass");
		logger.log(Level.INFO, "GWT Applet started");

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				sendButton.setEnabled(true);
				sendButton.setFocus(true);
			}
		});
		
		

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				sendNameToServer();
				//Logger logger = Logger.getLogger("componentClass");
				//logger.log(Level.SEVERE, "hallo",new Exception("hallo"));
				
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer();
					
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a response.
			 */
			private void sendNameToServer() {
				// First, we validate the input.
				errorLabel.setText("");
				String textToServer = nameField.getText();
				String password = passwordField.getText();


				// Then, we send the input to the server.
				sendButton.setEnabled(false);
				textToServerLabel.setText(textToServer);
				serverResponseLabel.setText("");
				try
				{
					ConnectInfo connectInfo = new ConnectInfo(textToServer, password.toCharArray());
					ClientFacade facade = context.lookup(ClientFacade.class);
					AppointmentFormater formater = context.lookup(AppointmentFormater.class);
					facade.login(connectInfo);
					Allocatable[] allocatables = facade.getAllocatables();
					StringBuilder builder = new StringBuilder();
					builder.append( "<h2>Ressources</h2>");
					for ( Allocatable alloc:allocatables)
					{
						builder.append( alloc.getName( Locale.GERMANY));
						builder.append( "<br/>");
					}
					builder.append( "<h2>Reservations</h2>");
					
					Reservation[] reservations = facade.getReservations( allocatables, new Date(), DateTools.addDays( new Date(), 5));
					for (Reservation r: reservations)
					{
						builder.append( r.getName( Locale.GERMANY));
						builder.append( "[");
						for ( Appointment app:r.getAppointments())
						{
							String summary = formater.getSummary( app);
							builder.append(summary);
						}
						builder.append( "]");
						builder.append( "<br/>");
					}
					String result = builder.toString();
					serverResponseLabel.setHTML(result);
					
				}
				catch (Throwable ex) {
					logger.log(Level.SEVERE, "hallo",ex);
				}
				dialogBox.setText("Remote Procedure Call");
				serverResponseLabel
						.removeStyleName("serverResponseLabelError");
				dialogBox.center();
				closeButton.setFocus(true);
				
//				greetingService.greetServer(textToServer,
//						new AsyncCallback<String>() {
//							public void onFailure(Throwable caught) {
//								// Show the RPC error message to the user
//								dialogBox
//										.setText("Remote Procedure Call - Failure");
//								serverResponseLabel
//										.addStyleName("serverResponseLabelError");
//								serverResponseLabel.setHTML(SERVER_ERROR);
//								dialogBox.center();
//								closeButton.setFocus(true);
//							}
//
//							public void onSuccess(String result) {
//								dialogBox.setText("Remote Procedure Call");
//								serverResponseLabel
//										.removeStyleName("serverResponseLabelError");
//								serverResponseLabel.setHTML(result);
//								dialogBox.center();
//								closeButton.setFocus(true);
//							}
//						});
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
		nameField.addKeyUpHandler(handler);
	}

	
//    private void provide(Container c) {
//    	Injector<Test> test = GWT.create(TestImpl.class);
//    	c.provide(Test.class, test);
//    }
//
//
//	private Injector<Test> GWT_create(Class<TestImpl> class1) {
//		return new Injector<Test>()
//			{
//				public Test create(Container cont) {
//					return new TestImpl( cont.getContext());
//				}
//			
//			};
//	}
}
