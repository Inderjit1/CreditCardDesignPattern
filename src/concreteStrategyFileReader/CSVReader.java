package concreteStrategyFileReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;

import Interfaces.CreditCard;
import Interfaces.FileReaderStrategy;
import factoryCreator.CreditCardType;
import individualProject.FileOutput;

public class CSVReader implements FileReaderStrategy<ArrayList<FileOutput>> {

	@Override
	public ArrayList<FileOutput> getFileData(String fileInput, String fileOutput, CreditCardType cc) {
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
	
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return output;

	}
}
	
