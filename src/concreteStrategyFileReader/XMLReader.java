package concreteStrategyFileReader;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.simple.parser.JSONParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Interfaces.CreditCard;
import Interfaces.FileReaderStrategy;
import factoryCreator.CreditCardType;
import individualProject.FileOutput;


public class XMLReader implements FileReaderStrategy<ArrayList<FileOutput>> {

	@Override
	public ArrayList<FileOutput> getFileData(String fileInput, String fileOutput, CreditCardType cc) {
		ArrayList<FileOutput> output = new ArrayList<FileOutput>();
		StringBuffer data = new StringBuffer();
		System.out.println("Inside json reader");
		//String fileInput = "C:\\Users\\inder\\Documents\\CMPE 202 - Software Systems Engineering\\SampleXML.xml";
		JSONParser parse = new JSONParser();
		
		System.out.println("Inside XML reader");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(new File(fileInput));
			doc.getDocumentElement().normalize();
			System.out.println("Root Element: " + doc.getDocumentElement().getNodeName());
			
			NodeList nodelist = doc.getElementsByTagName("row");
			for(int i = 0; i < nodelist.getLength(); i++) {
				Node node = nodelist.item(i);
				//System.out.println("Node name: "+ node.getNodeName());
				if(node.getNodeType() == Node.ELEMENT_NODE) {
					
					Element element = (Element) node;

					BigDecimal bd = new BigDecimal(element.getElementsByTagName("CardNumber").item(0).getTextContent());
					System.out.println("Card before test: " + (bd.longValue()));
					CreditCard test = cc.getCreditCard(String.valueOf((bd.longValue())), element.getElementsByTagName("ExpirationDate").item(0).getTextContent(), 
							element.getElementsByTagName("NameOfCardholder").item(0).getTextContent());
					
					if(test != null){
						System.out.println("Card Nameis: " + test.getCardNumber());
						FileOutput fo = new FileOutput(test.getCardNumber(), test.getCardName(), "None");
						System.out.println("test.getCardNumber: "+ test.getCardNumber());
						output.add(fo);
					}
					else {
						FileOutput fo = new FileOutput(String.valueOf(bd.longValue()),"None", "Error" );
						output.add(fo);
					}
					
					}

				}
		}
		catch(Exception e) {
			System.out.println("Error parsing XML File: " + e.getMessage());
		}
		
		return output;
	}
}
