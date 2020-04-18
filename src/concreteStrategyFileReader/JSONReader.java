package concreteStrategyFileReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.JsonObject;

import Interfaces.CreditCard;
import Interfaces.FileReaderStrategy;
import factoryCreator.CreditCardType;

public class JSONReader implements FileReaderStrategy<ArrayList<JsonObject>> {

	@Override
	public ArrayList<JsonObject> getFileData(String fileInput, String fileOutput, CreditCardType cc) {
		ArrayList<JsonObject> output = new ArrayList<JsonObject>();
		StringBuffer data = new StringBuffer();
		System.out.println("Inside json reader");
		//String fileInput = "C:\\Users\\inder\\Documents\\CMPE 202 - Software Systems Engineering\\SampleJSON.json";
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
	}
		catch(Exception e){
			System.out.println("Error parsing JSON file: " + e.getMessage());
		}
		
		return output;

	}
}
