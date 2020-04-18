package Interfaces;

import factoryCreator.CreditCardType;

public interface FileWriterStrategy<T> {
	
	//T getFileData(CreditCardType cc);
	void createFile(T type);
}
