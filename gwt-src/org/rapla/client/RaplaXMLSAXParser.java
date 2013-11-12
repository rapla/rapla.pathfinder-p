/*--------------------------------------------------------------------------*
  | Copyright (C) 2006 Christopher Kohlhaas                                  |
  |                                                                          |
  | This program is free software; you can redistribute it and/or modify     |
  | it under the terms of the GNU General Public License as published by the |
  | Free Software Foundation. A copy of the license has been included with   |
  | these distribution in the COPYING file, if not go to www.fsf.org .       |
  |                                                                          |
  | As a special exception, you are granted the permissions to link this     |
  | program with every library, which license fulfills the Open Source       |
  | Definition as published by the Open Source Initiative (OSI).             |
  *--------------------------------------------------------------------------*/
package org.rapla.client;

import java.util.LinkedHashMap;
import java.util.Map;

import org.rapla.components.util.xml.RaplaNonValidatedInput;
import org.rapla.components.util.xml.RaplaSAXAttributes;
import org.rapla.components.util.xml.RaplaSAXHandler;
import org.rapla.framework.RaplaException;
import org.rapla.framework.logger.Logger;
import org.rapla.storage.xml.RaplaSAXParseException;

import com.google.gwt.xml.client.NamedNodeMap;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.XMLParser;

/** Reads the data in xml format from an InputSource into the
    LocalCache and converts it to a newer version if necessary.
 */
public final class RaplaXMLSAXParser implements RaplaNonValidatedInput{
    
    public RaplaXMLSAXParser() {
    }

    
	public void read(String xml, RaplaSAXHandler handler, Logger logger) throws RaplaException {
		Node node = XMLParser.parse(xml);
		toSax(handler, node);
     }

	private void toSax(RaplaSAXHandler handler, Node node)
			throws RaplaSAXParseException {
		NodeList childNodes = node.getChildNodes();
		for ( int i =0;i<childNodes.getLength();i++)
		{
			Node item = childNodes.item(i);
			short nodeType = item.getNodeType();
			if ( nodeType == Node.ELEMENT_NODE || nodeType == Node.DOCUMENT_NODE)
			{
				String namespaceURI = item.getNamespaceURI();
				String name = item.getNodeName();
				String prefix = item.getPrefix();
				String localName;
				if ( prefix != null && name.indexOf( prefix + ":") == 0)
				{
					localName= name.substring( prefix.length()+1);
				}
				else
				{
					localName = name;
				}
				Map<String, String> map = new LinkedHashMap<String,String>();
				if ( item.hasAttributes())
				{
					NamedNodeMap attributes = item.getAttributes();
					if ( attributes != null)
					{
						for ( int j =0;j<attributes.getLength();j++)
						{
							Node att = attributes.item( j );
							String key = att.getNodeName();
							String value = att.getNodeValue();
							map.put( key, value);
						}
					}
				}
				RaplaSAXAttributes atts = new RaplaSAXAttributes(map);
				handler.startElement(namespaceURI, localName, atts);
				toSax( handler, item);
				handler.endElement(namespaceURI, localName);
			}
			else if ( nodeType == Node.TEXT_NODE)
			{
				String nodeValue = item.getNodeValue();
				char[] ch = nodeValue.toCharArray();
				int start = 0;
				int length = nodeValue.length();
				handler.characters(ch, start, length);
			}
		}
	}

    

}


