// ---------------------------------------------------------
// Assignment 4
// Written by: Maelys Arnaud 40278798
// For COMP 248 Section H - Fall 2023  
// ---------------------------------------------------------
// to keep track of the prepaid card type customers used 
// ---------------------------------------------------------
public class PrePaiCard {

	private String type;
	private int customerId;
	private int day; // expiry day
	private int month; // expiry month
	
	
	//default constructor
	public PrePaiCard() {
		
	}
	
	// constructor with 4 parameters to set the initial value of each attribute.
	public PrePaiCard(String type, int customerId, int day, int month) {
		if (day<1 || day>31) // if the date is not between 1 and 31, set it to zero
			this.day = 0;
		else 
			this.day = day;
		if (month<1 || month>12) // if the number of the month is not between 1 and 12, set it to zero
			this.month = 0;
		else 
			this.month = month;
		this.type = type;
		this.customerId = customerId;
	}
	
	//copy constructor = create a different object that is a copy of the argument 
	public PrePaiCard (PrePaiCard card) {
		this.day = card.getDay();
		this.month = card.getMonth();
		this.type = card.getType();
		this.customerId = card.getCustomerId();
	}
	
	// accessor methods for all attributes 
	public int getDay() {
		return day; }
	
	public int getMonth() {
		return month; }
	
	public String getType() {
		return type; }
	
	public int getCustomerId() {
		return customerId; }
	
	// mutator methods for expiry date + expiry month
	// used to change the expiry date in #8
	public void setDay(int day) {
		if (day < 1|| day > 31) 
			this.day = 0;
		else 
			this.day = day;
	}
	
	public void setMonth(int month) {
		if (month < 1 || month > 12)
			this.month = 0;
		else
			this.month = month;
	}
	
	// method to print the PrePaiCards informations 
	public String toString() {
		String dayDate = "";
		String monthDate = "";
		
		// if the date numbers are lower than 10, add a zero in front of it
		if (this.day<10)
			dayDate += "0";
		if (this.month<10)
			monthDate += "0";
		
		dayDate += this.day;
		monthDate += this.month;
		// return the complete string
		return (type + " - " + customerId + " - " + dayDate + "/" + monthDate + ".");
	}
	
	// compare the informations of two different PrePaiCards 
	public boolean equals(PrePaiCard otherCard) {
		return ((this.day == otherCard.getDay()) && (this.month == otherCard.getMonth()) 
				&& (this.type == otherCard.getType()) && (this.customerId == otherCard.getCustomerId()));
	}
}
