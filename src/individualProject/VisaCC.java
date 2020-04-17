package individualProject;

import java.util.regex.Pattern;

import Interfaces.CreditCard;

public class VisaCC implements CreditCard{
	
	String cardNumber;
	String cardName;
	String date; 
	
	public VisaCC() {};
	public VisaCC(String cardNumber, String cardName, String date ) {
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
			String regex = "^[5]\\d{15}|[5]\\d{12}$";
			if(Pattern.matches(regex, cardNumber)) {
				return true;
			}

				
			return false;	
		}

}
