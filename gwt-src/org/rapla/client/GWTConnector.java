package org.rapla.client;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.rapla.framework.RaplaException;
import org.rapla.storage.dbrm.Connector;
import org.rapla.storage.dbrm.RemoteMethodSerialization;

import com.google.gwt.xhr.client.XMLHttpRequest;

public class GWTConnector implements Connector {
    String sessionId;
    String server;
    String clientVersion;
	
	public GWTConnector(String server, String clientVersion) {
		this.server = server;
        this.clientVersion = clientVersion;
	}

	public String getInfo() {
		return server;
	}

	public static class SynchronousXHR extends XMLHttpRequest {

        protected SynchronousXHR() {
        }

        public native final void synchronousOpen(String method,String uri)
        /*-{
                this.open(method, uri, false);
        }-*/;

    }
	
	@Override
	public Object call(Class<?> service, String methodName, Class<?>[] parameterTypes,	Class<?> returnType, Object[] args,	RemoteMethodSerialization remoteMethodSerialization) throws IOException, RaplaException 
	{
	    if ( service != null)
	    {
	        methodName = service.getName() +"/" + methodName; 
	    }
	    Map<String, String> argMap = remoteMethodSerialization.serializeArguments(parameterTypes, args);
	    
		//XMLHttpRequest request = XMLHttpRequest.create();
		SynchronousXHR request = (SynchronousXHR) SynchronousXHR.create();
		String url = getInfo() + "rapla/rpc/" + methodName; 
		url = addSessionId(url);
		String httpMethod = "POST";
		request.synchronousOpen(httpMethod, url);
		//request.open(httpMethod, url);
		//open(request, "post", url);
		request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
	    if ( sessionId != null)
	    {
	    	request.setRequestHeader("Cookie","JSESSIONID=" + sessionId);
	    }
		StringBuilder builder = new StringBuilder();
		addParams(builder, argMap);
		String requestData = builder.toString();
		request.send(requestData);
		
    	String message = request.getResponseHeader("X-Error-Stacktrace");
        if ( message != null)
        {
        	String classname = request.getResponseHeader("X-Error-Classname");
        	String param = request.getResponseHeader("X-Error-Param");
        	RaplaException ex = remoteMethodSerialization.deserializeException( classname, message, param);
        	throw ex;
        }
		String resultString = request.getResponseText();
        Object result = remoteMethodSerialization.deserializeReturnValue(returnType, resultString);
		
		String cookies = request.getResponseHeader("Set-Cookie");
		updateSession( cookies);
		//String responseHeaders = request.getAllResponseHeaders();
		return result;
	}
	
    private void updateSession( String entry )
    {
        Map<String,String> cookies = new HashMap<String,String>();
        if ( entry != null)
        {
            String[] splitted = entry.split(";");
            if ( splitted.length > 0)
            {
                String[] first = splitted[0].split("=");
                cookies.put(first[0], first[1]);
            }
        }
        String sessionId = cookies.get("JSESSIONID");
        if ( sessionId != null)
        {
            this.sessionId = sessionId;
        }
    }

	
    private void addParams(Appendable writer, Map<String,String> args ) throws IOException
    {
    	writer.append( "v="+URLEncoder.encode(clientVersion,"utf-8"));
        for (Iterator<String> it = args.keySet().iterator();it.hasNext();)
        {
        	writer.append( "&");
            String key = it.next();
            String value= args.get( key);
            {
                String pair = key;
                writer.append( pair);
                if ( value != null)
                {
                	writer.append("="+ URLEncoder.encode(value,"utf-8"));
                }
            }
           
        }
    }
	
	  public final native void open(XMLHttpRequest request,String httpMethod, String url) /*-{
	    request.open(httpMethod, url, false);
	  }-*/;
	
	private String addSessionId(String methodURL)  {
		if ( sessionId != null)
		{
			String newURL = methodURL + ";jsessionid=" +sessionId ;
			return newURL;
		}
		return methodURL;
	}

}
