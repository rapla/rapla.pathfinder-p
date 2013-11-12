package org.rapla.plugin.gwt.server;

import org.rapla.framework.RaplaContext;
import org.rapla.servletpages.DefaultHTMLMenuEntry;

public class GWTMenuEntry extends DefaultHTMLMenuEntry 
{
	public GWTMenuEntry(RaplaContext context) {
		super(context);
	}
		
	@Override
	public String getName() {
		return "HTML-Client";
	}
	@Override
	public String getLinkName() {
		return "Test_gwt.html";
	}
		
}
