package individualProject;

import java.util.regex.Pattern;

import Interfaces.CreditCard;

public class MasterCC implements CreditCard{
	
	String cardNumber;
	String cardName;
	String date; 
	
	public MasterCC() {};

	public MasterCC(String cardNumber, String cardName, String date ) {
		this.cardNumber = cardNumber;
		this.cardName = cardName;
		this.date = date;
	};
//
//	
//
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
//			int firstDigit = Integer.parseInt(String.valueOf(cardNumber.charAt(0)));
//			int secondDigit = Integer.parseInt(String.valueOf(cardNumber.charAt(1)));
//			int cardLength = cardNumber.length();
		
			String regex = "^[5][1-5]\\d{14}$";
			if(Pattern.matches(regex, cardNumber)) {
				return true;
			}
			
			return false;
			
		}

}
