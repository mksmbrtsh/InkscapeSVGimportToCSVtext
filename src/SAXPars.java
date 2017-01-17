import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXPars extends DefaultHandler {

	ArrayList<String> outArrayList;
	StringBuilder sb;
	private String thisElement;
	private int id;

	@Override
	public void startDocument() throws SAXException {
		System.out.println("Start parse XML...");
		outArrayList = new ArrayList<String>();
		sb = new StringBuilder();
		sb.append("id;x;y;size;color;name;lang\n");
		id = 1;
	}

	@Override
	public void startElement(String namespaceURI, String localName,
			String qName, Attributes atts) throws SAXException {
		if (qName.equals("text")) {
			thisElement = qName;
			sb.append(id);
			sb.append(';');
			id++;
			sb.append(atts.getValue("x"));
			sb.append(';');
			sb.append(atts.getValue("y"));
			sb.append(';');
			String style = atts.getValue("style");
			for (String s : style.split(";")) {
				if (s.startsWith("font-size:")) {
					String size = s.substring("font-size:".length(), s.length() - 2); 
					sb.append(String.valueOf((int)(Math.round(Double.parseDouble(size)))));
					sb.append(';');
				} else if (s.startsWith("fill:")) {
					String color = s.substring("fill:#".length(), s.length());
					sb.append(Integer.parseInt(color,16));
					sb.append(';');
				}
			}
		} else if (qName.equals("tspan")) {
			thisElement = qName;
		}
	}

	@Override
	public void endElement(String namespaceURI, String localName, String qName)
			throws SAXException {
		if (qName.equals("tspan")) {
			thisElement = null;
			sb.append("\n");
			outArrayList.add(sb.toString());
			sb = new StringBuilder();
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (thisElement != null && thisElement.equals("tspan")) {
			sb.append(new String(ch, start, length));
			sb.append(';');
			sb.append("1;");
		}
	}

	@Override
	public void endDocument() {
		System.out.println("Stop parse XML...");
	}
}
