package Interfaces;

import factoryCreator.CreditCardType;

public interface FileReaderStrategy<T> {
	T getFileData(String fileInput, String fileOutput, CreditCardType cc);

}
