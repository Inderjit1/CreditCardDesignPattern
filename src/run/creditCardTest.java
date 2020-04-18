package run;

import Context.Context;
import factoryCreator.CreditCardType;
import individualProject.FileHandler;

public class creditCardTest {

	
	public static void main(String []args) throws Exception {
		//System.out.println("Arguments is: " + args[0]);

		
		String fileInput = "C:\\Users\\inder\\Documents\\CMPE 202 - Software Systems Engineering\\SampleJSON.json";
		//String fileInput = "C:\\Users\\inder\\Documents\\CMPE 202 - Software Systems Engineering\\SampleXML.xml";
		String fileOutput = "";
		CreditCardType cc = new CreditCardType();
		FileHandler fh = new FileHandler();
		//fh.CSVReader(cc);;
		//fh.JSONReader(cc, fileInput, fileOutput);
		//fh.XMLReader(cc, fileInput, fileOutput);
		
		Context context = new Context();
		context.chooseStrategy(fileInput, fileOutput, cc);
		context.doStrategy(fileInput, fileOutput, cc);
		
		
//		String []split = fileInput.split("\\.");
//		System.out.println("TEsting" + split.length);
//		for(int i = 0; i < split.length; i++) {
//			System.out.println("Item is: " + split[i]);
//		}


	}
	
	

}
