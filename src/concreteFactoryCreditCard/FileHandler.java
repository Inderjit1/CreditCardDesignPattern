package concreteFactoryCreditCard;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//import org.json.simple.JSONObject;
import com.google.gson.JsonObject;
import org.json.simple.parser.JSONParser;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.DocumentBuilder;  
import org.w3c.dom.Document;  
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;  
import org.w3c.dom.Element; 

import Interfaces.CreditCard;
import factoryCreator.CreditCardType;


public class FileHandler {
	
	
	/*
	 * Class that holds CSV File Data Structure
	 */
	public static class FileOutput{
		String cardNumber;
		String outCardType;
		String errorField;
		
		public FileOutput(String cardNumber, String outCardType, String errorField){
			this.cardNumber = cardNumber;
			this.outCardType = outCardType;
			this.errorField = errorField;
		}

		public String getCardNumber() {
			return cardNumber;
		}

		public String getOutCardType() {
			return outCardType;
		}

		public String getErrorField() {
			return errorField;
		}
	}
	
	public void CSVReader(CreditCardType cc) throws Exception {
		
		ArrayList<FileOutput> output = new ArrayList<FileOutput>();
		
		String filepath = "C:\\Users\\inder\\Documents\\CMPE 202 - Software Systems Engineering\\Sample.csv";
		String fileDestination = "C:\\Users\\inder\\Documents\\CMPE 202 - Software Systems Engineering\\Results.csv";
		String line = "";
		
		StringBuffer data = new StringBuffer();
		try {
			FileReader file = new FileReader(filepath);
			BufferedReader br = new BufferedReader(file);
			br.readLine();
			
			while((line = br.readLine()) != null ) {
				String []values  = line.split(",");
				BigDecimal bd = new BigDecimal(values[0]);
				
				System.out.println("Data is: " + line);
				
				CreditCard test = cc.getCreditCard(String.valueOf(bd.longValue()), values[1], values[2]);
	
				if(test != null){
					FileOutput fo = new FileOutput(test.getCardNumber(), test.getCardName(), "None");
					System.out.println("test.getCardNumber: "+ test.getCardNumber());
					output.add(fo);
				}else 
				{
					FileOutput fo = new FileOutput(String.valueOf(bd.longValue()),"None", "Error" );
					output.add(fo);
				}
				
			
			}
			
			br.close();
			
			
			FileWriter outfile = new FileWriter(fileDestination);
			BufferedWriter bw = new BufferedWriter(outfile);
			PrintWriter pw = new PrintWriter(bw);
			data.append("CardNumber" + "," + "Type of Card" + "," + "Error\n");
			for(FileOutput f: output) {
				data.append(f.getCardNumber() + ",");
				data.append(f.getOutCardType() + "," );
				data.append(f.getErrorField() + ",");
				data.append("\n");
				
			}
			
			pw.println(data);
			pw.flush();
			pw.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public void JSONReader(CreditCardType cc, String fileInput, String fileOutput) {
		
		ArrayList<JsonObject> output = new ArrayList<JsonObject>();
		StringBuffer data = new StringBuffer();
		System.out.println("Inside json reader");
		JSONParser parse = new JSONParser();
		try {
			Object obj = parse.parse(new FileReader(fileInput));
			System.out.println("OBJ IS: " + obj);
			
			JSONArray jsonObj = (JSONArray)obj;
			Iterator<JSONObject> iterate = jsonObj.iterator();
			while(iterate.hasNext()) {
				JSONObject o = iterate.next();
				Object []col = o.values().toArray();
				CreditCard test = cc.getCreditCard(String.valueOf(col[0]), String.valueOf(col[1]), String.valueOf(col[2]));
				
				
				if(test != null) {
					JsonObject jsonObjToFile = new JsonObject();
					jsonObjToFile.addProperty("Card Number",Long.parseLong(test.getCardNumber()));
					jsonObjToFile.addProperty("Card Type", test.getCardName());
					jsonObjToFile.addProperty("Error","None");
					output.add(jsonObjToFile);
				}
				else {
					JsonObject jsonObjToFile = new JsonObject();
					jsonObjToFile.addProperty("Card Number", col[0].toString());
					jsonObjToFile.addProperty("Card Type", "None");
					jsonObjToFile.addProperty("Error","Error");
					output.add(jsonObjToFile);
				}	
				
			}
			
			
			FileWriter outfile = new FileWriter("C:\\Users\\inder\\Documents\\CMPE 202 - Software Systems Engineering\\ResultsJSON.json");
			BufferedWriter bw = new BufferedWriter(outfile);
			PrintWriter pw = new PrintWriter(bw);
			
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			
			data.append("[");
			
			data.append(System.lineSeparator());
			for(int i = 0; i < output.size();i++) {
				
				if(i == output.size()-1) {
					data.append(output.get(i));
				}
				else {
					data.append(output.get(i) + ",");
				}
				System.out.println("Out print: " + output.get(i));
			}
			data.append(System.lineSeparator());

			data.append("]");
			
			System.out.println(data);
			
		
			Object json = mapper.readValue(data.toString(), Object.class);
			String indented = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
			//String json = gson.toJson(data);
			pw.println(indented);
			pw.flush();
			pw.close();


		}
		catch(FileNotFoundException ex){
			System.out.println("Error message is: " + ex.getLocalizedMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error message is: " + e.getLocalizedMessage());
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("Error message is: " + e.getLocalizedMessage());
		}
	}
	
	public void XMLReader(CreditCardType cc, String fileInput, String fileOutput) {
		ArrayList<FileOutput> output = new ArrayList<FileOutput>();
		StringBuffer data = new StringBuffer();
		System.out.println("Inside json reader");
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
					}else 
					{
						FileOutput fo = new FileOutput(String.valueOf(bd.longValue()),"None", "Error" );
						output.add(fo);
					}
					
					

					Document document = db.newDocument();
					
					Element root  = document.createElement("root");
					document.appendChild(root);
					
					for(FileOutput f: output) {
						Element row = document.createElement("row");
						root.appendChild(row);
						
						Element CardNumber = document.createElement("CardNumber");
						CardNumber.appendChild(document.createTextNode(f.getCardNumber()));
						row.appendChild(CardNumber);
						
						Element CardType = document.createElement("CardType");
						CardType.appendChild(document.createTextNode(f.getOutCardType()));
						row.appendChild(CardType);
						
						Element Error = document.createElement("Error");
						Error.appendChild(document.createTextNode(f.getErrorField()));
						row.appendChild(Error);
						
					}
					
					TransformerFactory tf = TransformerFactory.newInstance();
					Transformer transformer = tf.newTransformer();
					DOMSource s = new DOMSource(document);
					
					transformer.setOutputProperty(OutputKeys.INDENT, "yes");
					transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
					StreamResult result = new StreamResult(new File("C:\\Users\\inder\\Documents\\CMPE 202 - Software Systems Engineering\\ResultsXML.xml"));
					try {
						transformer.transform(s, result);
					} catch (TransformerException e) {
						e.printStackTrace();
					}

					
				}
				else {
					
				}
			}
		} catch (ParserConfigurationException | SAXException | IOException | TransformerConfigurationException e1) {
			e1.printStackTrace();
		}

	}
	
	
}



