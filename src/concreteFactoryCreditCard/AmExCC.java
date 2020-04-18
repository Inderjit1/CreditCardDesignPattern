package concreteFactoryCreditCard;

import Interfaces.CreditCard;

import java.util.regex.Pattern;

public class AmExCC implements CreditCard{
	
	String cardNumber;
	String cardName;
	String date; 
	
	public AmExCC() {};
	public AmExCC(String cardNumber, String cardName, String date ) {
		this.cardNumber = cardNumber;
		this.cardName = cardName;
		this.date = date;
	};
	
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public boolean check(String cardNumber, String cardName, String date ) {
		
			// Amex Requirements: First digit is a 3 and second digit a 4 or 7. Length is 15 digits.
			String regex = "^[3][4]\\d{13}|[3][7]\\d{13}$";
			if(Pattern.matches(regex, cardNumber)) {
				return true;
			}
			
			return false;
			
		}

}
