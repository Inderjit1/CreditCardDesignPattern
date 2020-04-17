package individualProject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;

import Interfaces.CreditCard;


public class FileHandler {
	
	
	/*
	 * Class that holds CSV File Data Structure
	 */
	public static class FileOutput{
		String cardNumber;
		String outCardType;
		String errorField;
		
		FileOutput(String cardNumber, String outCardType, String errorField){
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
	
	
}



