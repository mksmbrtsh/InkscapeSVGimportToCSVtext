import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXParsIconsLayout extends DefaultHandler implements IcsvRowCreator {

	ArrayList<String> outArrayList;
	StringBuilder sb;
	private String thisElement;
	private int id;

	@Override
	public void startDocument() throws SAXException {
		System.out.println("Start parse icons...");
		outArrayList = new ArrayList<String>();
		sb = new StringBuilder();
		sb.append("id;icon;text;x;y;lang;\n");
		id = 1;
	}

	@Override
	public void startElement(String namespaceURI, String localName,
			String qName, Attributes atts) throws SAXException {
		if (qName.equals("g") && atts.getValue("transform") !=null && atts.getValue("transform").contains("matrix") && atts.getValue("id") != null) {
			thisElement = qName;
			sb.append(id);
			sb.append(';');
			id++;
			String icon = atts.getValue("id");
			if(icon.contains("-")){
				sb.append(icon.substring(0, icon.indexOf('-')));
			} else
				sb.append(icon);
			sb.append(';');
			if(atts.getValue("txt")!=null) {
				sb.append(atts.getValue("txt"));
				sb.append(';');
			} else {
				sb.append(';');
			}
			String transform = atts.getValue("transform");
			String[] t = transform.split(",");
			if(t.length != 6){
				int i1=0;
				i1++;
			}
			sb.append(t[4]);
			sb.append(';');
			sb.append(t[5].substring(0, t[5].length() -1));
			sb.append(";1;");
		}
	}

	@Override
	public void endElement(String namespaceURI, String localName, String qName)
			throws SAXException {
		if (thisElement!=null && qName.equals("g")) {
			thisElement = null;
			sb.append("\n");
			outArrayList.add(sb.toString());
			sb = new StringBuilder();
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
	}

	@Override
	public void endDocument() {
		System.out.println("Stop parse icons...");
	}

	@Override
	public ArrayList<String> getArrayList() {
		return outArrayList;
	}
}
