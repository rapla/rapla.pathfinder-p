package org.rapla.plugin.pathfinder.client;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.rapla.plugin.freiraum.common.RaplaJsonService;
import org.rapla.plugin.freiraum.common.ResourceDescriptor;
import org.rapla.plugin.freiraum.common.ResourceDetail;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwtjsonrpc.common.AsyncCallback;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Pathfinder implements EntryPoint {

	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		final Button button1 = new Button("test1");
		final Button button2 = new Button("test2");
		final Button button3 = new Button("test3");
		final Label errorLabel = new Label();
		button1.addStyleName("sendButton");
		button2.addStyleName("sendButton");
		button3.addStyleName("sendButton");
		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("button1").add(button1);
		RootPanel.get("button2").add(button2);
		RootPanel.get("button3").add(button3);
		RootPanel.get("errorLabelContainer").add(errorLabel);

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
		dialogVPanel.add(new HTML("<b>Sending request to the server:</b>"));
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
				button1.setEnabled(true);
				button1.setFocus(true);
				button2.setEnabled(true);
				button2.setFocus(true);
				button3.setEnabled(true);
				button3.setFocus(true);
			}
		});
		
		
		final RaplaJsonService cs = GWT.create(RaplaJsonService.class);
		String address = GWT.getModuleBaseURL() + "../rapla/json/RaplaJsonService";
		((ServiceDefTarget) cs).setServiceEntryPoint(address);


		// Create a handler for the sendButton and nameField
		abstract class MyHandler implements ClickHandler {
			
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				
				errorLabel.setText("");
				// Then, we send the input to the server.
				serverResponseLabel.setText("");
				button1.setEnabled(false);
				button2.setEnabled(false);
				button3.setEnabled(false);
				try
				{
					send();
				}
				catch (Throwable ex) {
					logger.log(Level.SEVERE, "error",ex);
				}
				dialogBox.setText("Remote Procedure Call");
				serverResponseLabel.removeStyleName("serverResponseLabelError");
				dialogBox.center();
				closeButton.setFocus(true);
				
			}
			
			abstract void send();
		}

		button1.addClickHandler(new MyHandler()
		{
			void send() {
					cs.getResources("room","",new AsyncCallback<List<ResourceDescriptor>>() {
						
						@Override
						public void onSuccess(List<ResourceDescriptor> test) {
							serverResponseLabel.setHTML("Success " + test);
						}
						
						@Override
						public void onFailure(Throwable arg0) {
							serverResponseLabel.setHTML("Failure");
						}
					});
			}
			
		}
		);
		
		button2.addClickHandler(new MyHandler()
		{
			void send() {
					cs.getResource("",new AsyncCallback<ResourceDetail>() {
						@Override
						public void onSuccess(ResourceDetail test) {
							serverResponseLabel.setHTML("Success " + test);
						}
						
						@Override
						public void onFailure(Throwable arg0) {
							serverResponseLabel.setHTML("Failure");
						}
					});
					
		
			}
			
		}
		);
	}

	
}
