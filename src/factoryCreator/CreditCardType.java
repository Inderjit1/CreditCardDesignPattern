package factoryCreator;

import java.util.regex.Pattern;

import Interfaces.CreditCard;
import individualProject.AmExCC;
import individualProject.DiscoverCC;
import individualProject.MasterCC;
import individualProject.VisaCC;

public class CreditCardType {
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

	private String cardNumber;
	private String cardName;  
	private String date;
	
	
	public CreditCardType() {}
	
	CreditCardType(String cardNumber, String cardName, String date){
		this.cardNumber = cardNumber;
		this.cardName = cardName;
		this.date = date;
	}
	
	public CreditCard getCreditCard(String cardNumber, String cardName, String date) {
		String discoverRegex = "^[6][0][1][1]\\d{12}$";
		String masterRegex = "^[5][1-5]\\d{14}$";;
		String visaRegex = "^[4]\\d{15}|[4]\\d{12}$";
		String amExRegex = "^[3][4]\\d{13}|[3][7]\\d{13}$";
		
		
		if(Pattern.matches(discoverRegex, cardNumber)) {
			System.out.println("This is a Discover Card");
			return new DiscoverCC(cardNumber, "Discover", date);
		} 
		
		else if(Pattern.matches(masterRegex, cardNumber)) {
			System.out.println("This is a Master Card");
			return new MasterCC(cardNumber, "Master", date);
		} 
		
		else if(Pattern.matches(visaRegex, cardNumber)) {
			System.out.println("This is a Visa Card");
			return new VisaCC(cardNumber, "Visa", date);
		} 
		
		else if(Pattern.matches(amExRegex, cardNumber)) {
			System.out.println("This is a AmericanExpress Card");
			return new AmExCC(cardNumber, "American Express", date);
		} 
		else {
			System.out.println("Not a valid card");
			return null;
		}
		
		
	}
	

}
