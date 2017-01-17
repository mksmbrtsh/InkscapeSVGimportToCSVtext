import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;


public class Main {

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		SAXParserFactory factory = SAXParserFactory.newInstance(); 
		SAXParser parser = factory.newSAXParser(); 
		SAXPars saxp = new SAXPars(); 
		File f = new File("c:\\map_v3_12.svg");
		if(f.exists()){
			parser.parse(f, saxp);
			FileWriter writer = new FileWriter("output.txt"); 
			for(String str: saxp.outArrayList) {
			  writer.write(str);
			}
			writer.close();
		}
	}

}
