package org.rapla.client;

import org.rapla.framework.Configuration;
import org.rapla.framework.RaplaException;
import org.rapla.storage.dbrm.Connector;
import org.rapla.storage.dbrm.ConnectorFactory;

import com.google.gwt.core.client.GWT;

public class GWTConnectorFactory implements ConnectorFactory {

	String clientVersion;
	
	
	public GWTConnectorFactory(String clientVersion) 
	{
		this.clientVersion = clientVersion;
	}


	public Connector create(Configuration config) throws RaplaException {
		String hostPageBaseURL = GWT.getHostPageBaseURL();
		return new GWTConnector(hostPageBaseURL, clientVersion);
	}

}
