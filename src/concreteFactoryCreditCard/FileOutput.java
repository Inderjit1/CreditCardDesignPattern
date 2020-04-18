package concreteFactoryCreditCard;

public class FileOutput {
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
