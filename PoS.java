// ---------------------------------------------------------
// Assignment 4
// Written by: Maelys Arnaud 40278798
// For COMP 248 Section H - Fall 2023  
// ---------------------------------------------------------
// will contain sales and prepaicards (more than one)
// object of type Sales 
// array of objetcs of type PrePaiCard 
// ---------------------------------------------------------
public class PoS {

	private Sales sales;
	private PrePaiCard[] cardList;
	
	// default constructor 
	public PoS() {
		
	}
	
	//constructor with 2 parameters 
	public PoS(Sales sales, PrePaiCard[] cardList) {
		this.sales = new Sales(sales); // use the copy constructor to deal with privacy leakage issues
		if (cardList != null) {
			this.cardList = new PrePaiCard[cardList.length];
			// to prevent privacy leaks transfer the information of the PrePaiCard list by using a for loop to copy it
			for (int i = 0; i< cardList.length; i++) {
				this.cardList[i] = new PrePaiCard(cardList[i]); // prevent privacy leaks
			}
		}
		else {
			this.cardList = null;
		}
		
	}
	
	// method that compares the total amount made by the sales of two different PoS
	public boolean totalSalesEquals(PoS posToCompare) {
		return (this.sales.salesTotal() == posToCompare.sales.salesTotal());
	}
	
	// method that compares the number of sales for each products of 2 different PoS
	public boolean categorySalesEqual(PoS posToCompare) {
		return (this.sales.getJuniorNum() == posToCompare.sales.getJuniorNum()
				&& this.sales.getTeenNum() == posToCompare.sales.getTeenNum()
				&& this.sales.getMediumNum() == posToCompare.sales.getMediumNum()
				&& this.sales.getBigNum() == posToCompare.sales.getBigNum()
				&& this.sales.getFamilyNum() == posToCompare.sales.getFamilyNum());
	}
	
	// return the total $ sales of a PoS.
	public int posSales() {
		return this.sales.salesTotal() ;
	}
	
	// return the number of prepaid cards in a PoS
	public int posPrepaidCards() {
		int cardListLength;
		// if the list is null the number is 0
		if (this.cardList == null) {
			cardListLength = 0;
		}
		else 
			cardListLength = this.cardList.length;
		return cardListLength; 
	}
	
	// to add a card
	public int addCard(PrePaiCard card) {
		// if the card list is not null
		if (cardList != null) {
			// create a temporary card list to be able to transfer it in the new (bigger) cardList
			PrePaiCard[] tempCardList = new PrePaiCard[this.cardList.length];
			// copy the information of the current cardList into the tempCardList
			for (int g = 0; g < this.cardList.length; g++) {
				tempCardList[g] = new PrePaiCard(cardList[g]);
			}
			int newSize = this.cardList.length + 1; // new size of the cardList
			this.cardList = new PrePaiCard[newSize]; // change the current card list to a bigger one
			// copy the tempCardList in the new cardList, stop before the last slot in the cardList
			for (int j = 0; j < newSize - 1; j++) {
				this.cardList[j] = new PrePaiCard(tempCardList[j]);
			}
			// now add the new PrePaiCard the user want to add in the last slot of the list
			this.cardList[newSize - 1]= new PrePaiCard(card);
		}
		// if the cardList is null
		else {
			this.cardList = new PrePaiCard[1]; // the cardList will now have a length of 1 (one card)
			this.cardList[0] = new PrePaiCard(card); // add the card
		}
		return this.cardList.length; // return the cardList length (number of card in it)
	}
	
	public void removeCard(int cardIndex) {
		// this method can only work if the cardList is non null
		if (this.cardList != null) {
			// if there is only one card
			if (this.cardList.length == 1) {
				this.cardList = null; // the cardList is now empty (null)
			}
			// if there is more than one card
			else {
				// create copy of the current cardList
				PrePaiCard[] tempCardList = new PrePaiCard[this.cardList.length];
				for (int n = 0; n < this.cardList.length; n++) {
					tempCardList[n] = new PrePaiCard(cardList[n]);
				}
				int newSize = this.cardList.length - 1; // new size is reduced by one slot
				this.cardList = new PrePaiCard[newSize]; // the cardList is now smaller than before 
				// copy the temporary card list until the index of the card that has to be removed
				for (int k = 0; k < cardIndex; k++) {
					this.cardList[k] = new PrePaiCard(tempCardList[k]);
				}
				// copy the rest of the temporary card list (but skipping the card the user wants to remove)
				// that is why the index going through the tempCardList will start at cardIndex + 1 
				// but the cardList's index has to be l-1 because we are not skipping the slot in that list 
				for (int l = cardIndex + 1; l <= newSize; l++) {
					this.cardList[l - 1] = new PrePaiCard(tempCardList[l]);
				}
			}
			System.out.println("PrePaiCard was removed successfully\n"); // tell the users the removal was successful
		}
	}
	// change the card expiry date
	public void changeExpDate(int cardIndex, int newDay, int newMonth) {
		// to do this the card list has to be non null (not empty)
		if (this.cardList != null) {
			this.cardList[cardIndex].setDay(newDay); // change the day of the card at the cardIndex by the new value
			this.cardList[cardIndex].setMonth(newMonth);
		}
	}
	// change the sales of the PoS 
	public int changePosSales(int addJun, int addTeen, int addMed, int addBig, int addFam) {
		this.sales.addSales(addJun, addTeen, addMed, addBig, addFam); // will be using the addSales method of the Sales class
		return this.sales.salesTotal(); // return the new total of the sales
	}
	// compare the total of sales of 2 different PoS
	public boolean equals(PoS otherPos) {
		return (this.totalSalesEquals(otherPos) && this.categorySalesEqual(otherPos));
	}
	
	//to see content of one PoS
	public String toString() {
		String contentOfPos = this.sales.toString() + "\n" ; // will put the details of sales of the object in the String variable
		// if there are card in the cardList
		if (this.cardList != null) {
			// go through the list
			for (int m = 0; m < this.cardList.length;m++) {
				contentOfPos += this.cardList[m].toString() + "\n"; // concatenate the corresponding card informations
			} 
		}
		// if no card in the list
		else 
			contentOfPos += "No PrePaiCards\n"; // no prepaicards 
		return contentOfPos; // return the string
	}
	
	// method that returns the sales break down by calling the .toString method of the Sales class
	public String salesBreakdown() {
		return this.sales.toString(); // return that string
	}
}
