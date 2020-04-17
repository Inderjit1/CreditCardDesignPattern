package Interfaces;

public interface CreditCard {
	public boolean check(String cardNumber, String cardName, String date );
	public String getCardNumber();
	public String getCardName();
	public String getDate();
}
