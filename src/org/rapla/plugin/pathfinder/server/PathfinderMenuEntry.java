package org.rapla.plugin.pathfinder.server;

import org.rapla.framework.RaplaContext;
import org.rapla.servletpages.DefaultHTMLMenuEntry;

public class PathfinderMenuEntry extends DefaultHTMLMenuEntry 
{
	public PathfinderMenuEntry(RaplaContext context) {
		super(context);
	}
		
	@Override
	public String getName() {
		return "Pathfinder-Client";
	}
	@Override
	public String getLinkName() {
		return "pathfinder.html";
	}
		
}
