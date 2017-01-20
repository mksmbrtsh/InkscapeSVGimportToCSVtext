import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class Main {

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		SAXParserFactory factory = SAXParserFactory.newInstance(); 
		SAXParser parser = factory.newSAXParser(); 
		DefaultHandler saxp = new SAXParsIconsLayout(); 
		File f = new File("c:\\map_v3_12.svg");
		if(f.exists()){
			parser.parse(f, saxp);
			FileWriter writer = new FileWriter("output_icons.txt"); 
			for(String str: ((IcsvRowCreator)saxp).getArrayList()) {
			  writer.write(str);
			}
			writer.close();
		}
		saxp = new SAXParsTextLayout(); 
		if(f.exists()){
			parser.parse(f, saxp);
			FileWriter writer = new FileWriter("output_labels.txt"); 
			for(String str: ((IcsvRowCreator)saxp).getArrayList()) {
			  writer.write(str);
			}
			writer.close();
		}
	}

}
