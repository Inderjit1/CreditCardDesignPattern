package Context;

import Interfaces.FileReaderStrategy;
import concreteStrategyFileReader.CSVReader;
import concreteStrategyFileReader.JSONReader;
import concreteStrategyFileReader.XMLReader;
import factoryCreator.CreditCardType;

public class Context {
	private FileReaderStrategy readerStrategy;
	
	
	public void changeStrategy(FileReaderStrategy readerStrategy) {
		this.readerStrategy = readerStrategy;
	}
	
	public void chooseStrategy(String fileInput, String fileOutput, CreditCardType cc) {
		String fileInputExtension = fileInput.split("\\.")[1];
		String fileOutputExtension = fileOutput.split("\\.")[1];
		
		if(fileInputExtension != fileOutputExtension) {
			System.out.println("File output extension is different then file input extension");
			return;
		}
		
		switch(fileOutputExtension) {
			case "csv":
				changeStrategy(new CSVReader());
			case "json":
				changeStrategy(new JSONReader());
			case "xml":
				changeStrategy(new XMLReader());
			default:
				System.out.println("Unsupported file type");
				break;
			
		}
		//String fileExt = fileInput.split(".")[0];
		//System.out.println("What is fileExt: " + fileExt);
	}
	
	public void doStrategy(String fileInput, String fileOutput, CreditCardType cc) {
		try {
			readerStrategy.getFileData(fileInput, fileOutput, cc);
		}
		catch (Exception e){
			System.out.println("Error with strategy choice: " + e.getMessage());
			
		}
	}
}
