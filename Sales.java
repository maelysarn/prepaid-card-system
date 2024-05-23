// ---------------------------------------------------------
// Assignment 4
// Written by: Maelys Arnaud 40278798
// For COMP 248 Section H - Fall 2023  
// ---------------------------------------------------------
// this class is made to keep track of the number of sales sold on-demand
// ---------------------------------------------------------
public class Sales {
	
	// private static final price values because it is the same for every object and it won't change 
	private static final int JUNIOR_PRICE = 5;
	private static final int TEEN_PRICE = 10;
	private static final int MEDIUM_PRICE = 12;
	private static final int BIG_PRICE = 15;
	private static final int FAMILY_PRICE = 20;
	
	// these are attributes for the number of each products 
	private int juniorNum;
	private int teenNum;
	private int mediumNum;
	private int bigNum;
	private int familyNum;
	
	
	// Default constructor where you initialize all numbers of sales made
	public Sales(){
	
	}
	
	//Constructor with 5 parameters 
	// Constructors to  initialize the Sales data field 
	public Sales(int juniorNum, int teenNum, int mediumNum, int bigNum, int familyNum) {
		this.juniorNum = juniorNum; //attribute of the concerned object : (this.name) = parameter (name)
		this.teenNum = teenNum;
		this.mediumNum = mediumNum;
		this.bigNum = bigNum;
		this.familyNum = familyNum;
	}
	
	//Copy constructor = create a different object that is a copy of the argument :)
	public Sales(Sales sales) {
		this.juniorNum = sales.getJuniorNum(); 
		this.teenNum = sales.getTeenNum();
		this.mediumNum = sales.getMediumNum();
		this.bigNum = sales.getBigNum();
		this.familyNum = sales.getFamilyNum();
	}
	
	// mutators : to change the current value of an attribute to a new value 
	public void setJuniorNum(int juniorNum) { 
		this.juniorNum = juniorNum;  }
	
	public void setTeenNum(int teenNum) { 
		this.teenNum = teenNum;  }
	
	public void setMediumNum(int mediumNum) { 
		this.mediumNum = mediumNum;  }
	
	public void setBigNum(int bigNum) { 
		this.bigNum = bigNum;  }
	
	public void setFamilyNum(int familyNum) {
		this.familyNum = familyNum;  }
	
	//accessor (getName()) and mutator (setName()) for all attributes (to return the wanted value):
	public int getJuniorNum() { 
		return juniorNum;  }
	
	public int getTeenNum() { 
		return teenNum;   }
	
	public int getMediumNum() { 
		return mediumNum;   }
	
	public int getBigNum() { 
		return bigNum;   }
	
	public int getFamilyNum() {
		return familyNum; }
	
	
	// this method is used to add new sales to the current one
	public void addSales(int addJun, int addTeen, int addMed, int addBig, int addFam) {
		juniorNum += addJun;
		teenNum += addTeen;
		mediumNum += addMed;
		bigNum += addBig;
		familyNum += addFam;
	}
	
	// method that returns the total sales of the object
	public int salesTotal() {
		int totalSales = JUNIOR_PRICE*juniorNum + TEEN_PRICE*teenNum + MEDIUM_PRICE*mediumNum + 
				BIG_PRICE*bigNum + FAMILY_PRICE*familyNum;
		return totalSales;
	}
	
	// method to print the sales of an object
	public String toString() {
		return (juniorNum + " x $" + JUNIOR_PRICE + " + " + teenNum + " x $" + TEEN_PRICE + " + " + mediumNum 
				+ " x $" + MEDIUM_PRICE + " + "+ bigNum + " x $" + BIG_PRICE + " + " 
				+ familyNum + " x $" + FAMILY_PRICE);
	}
	
	// compare the sales of 2 different objects
	public boolean equals(Sales otherSales) {
		
		return ((this.juniorNum == otherSales.getJuniorNum()) 
				&& (this.teenNum == otherSales.getTeenNum())
				&& (this.mediumNum == otherSales.getMediumNum()) 
				&& (this.bigNum == otherSales.getBigNum()) 
				&& (this.familyNum == otherSales.getFamilyNum()) );
	}
	
	
	
}
