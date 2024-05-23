// ---------------------------------------------------------
// Assignment 4
// Written by: Maelys Arnaud 40278798
// For COMP 248 Section H - Fall 2023  
// ---------------------------------------------------------
// This program will simulate the Point of Sale (PoS) for CostLessBites catering 
// ---------------------------------------------------------

// import the Scanner object to scan the input of the user
import java.util.Scanner;
public class PoSDemo {
	
	// in PoS[] initialization you can change, add information (hardcode) that will be accessed in the main 
	public static PoS[] initialization() {
		// hard code the info of the PoSs
		Sales sales1 = new Sales(2,1,0,4,1);
		Sales sales2 = new Sales(0,1,5,2,0);
		Sales sales3 = new Sales(3,2,4,1,2);
		
		PrePaiCard[] cardList1 = {new PrePaiCard("Vegetarian", 40825164, 25, 12), new PrePaiCard("Carnivore", 21703195, 3, 12)};
		PrePaiCard[] cardList2 = {new PrePaiCard("Vigan", 40825164, 7, 12), new PrePaiCard("Vegetarian",21596387, 24, 8)};
		PrePaiCard[] cardList3 = {new PrePaiCard("Pescatarian", 95432806, 1, 6), new PrePaiCard("Halal", 42087913, 18, 12), new PrePaiCard("Kosher", 40735421, 5,4)};
		PrePaiCard[] cardList4 = null;
		
		PoS pos0 = new PoS(sales1, cardList1);
		PoS pos1 = new PoS(sales1, cardList2);
		PoS pos2 = new PoS(sales2, cardList3);
		PoS pos3 = new PoS(sales3, cardList4);
		PoS pos4 = new PoS(sales3, cardList4);
		
		PoS[] posList = {pos0, pos1, pos2, pos3, pos4};
		return posList;
	}
	
	// name key as the Scanner object
	static Scanner key = new Scanner(System.in);
	
	// this method will print all the details of each PoS
	static void optionPrintAll(PoS posList[]) {
		System.out.println("Content of each PoS:\n---------------------");
		// for each PoS of the list
		for (int index = 0; index < posList.length; index++) {
			System.out.println("PoS #" +  index + ":\n" + posList[index].toString()); // print their content
		}
	}
	
	// this method will print the content of one PoS
	static void optionPrintOne(PoS posList[]) {
		System.out.print("Which PoS you want to see the content of? (Enter number 0 to " + (posList.length - 1) + "): ");
		int posChoice = numberSelection(posList, key.nextInt()); // this line asks the user to input his PoS of choice using the number selection method
		System.out.println(posList[posChoice].toString() + "\n"); // print the content of the PoS at the index of posChoice
	}
	
	// this method regroups the choice 3-4-5, where values of different PoS are compared
	static void optionPrintEqual(PoS posList[], int operation) {
		// depending on the operation number, the display will be different 
		if (operation == 3) 
			System.out.println("List of PoSs with same total $ Sales:\n");
		if (operation == 4)
			System.out.println("List of PoSs with same Sales categories:\n");
		if (operation == 5)
			System.out.println("List of PoSs with same $ amount of sales and same number of PrePaiCards:\n");
		// then it will go through a double for loop 
		for (int i = 0; i < posList.length; i++) {
			// second for loop will start at i + 1, so there is no same pairs being compared 
			for (int j = i+1; j < posList.length; j++) {
				// the if condition are the operation choice value and the corresponding values compared 
				if (operation == 3 && posList[i].totalSalesEquals(posList[j])) {
					System.out.println("\tPoSs " + i + " and " + j + " both have " + posList[i].posSales()); // if the conditions are true, it will print it 
				}
				if (operation == 4 && posList[i].equals(posList[j])) {
					System.out.println("\tPoSs " + i + " and " + j + " both have " + posList[i].salesBreakdown());
				}
				if (operation == 5 && posList[i].equals(posList[j]) && (posList[i].posPrepaidCards() == posList[j].posPrepaidCards())) {
					System.out.println("\tPoSs " + i + " and " + j);
				}
			}
		}
		System.out.println("\n"); // this line is to match the output sample
	}
	
	//this method will add a card to the pos
	static void optionAddCard(PoS posList[]) {
		System.out.print("Which PoS to you want to add an PrePaiCard to? (Enter number 0 to " + (posList.length - 1) + "): ");
		int posAdd = numberSelection(posList, key.nextInt());
		System.out.print(" --> Type of PrePaiCard (Carnivore, Halal, Kosher, Pescatarian, Vegetarian, Vigan): ");
		String cardType = key.next(); // user will input his type 
		key.nextLine(); // junk line
		// is not in the available types, will reask 
		while (!cardType.equalsIgnoreCase("Carnivore") && !cardType.equalsIgnoreCase("Halal") && !cardType.equalsIgnoreCase("Kosher") 
				&& !cardType.equalsIgnoreCase("Pescatarian") && !cardType.equalsIgnoreCase("Vegetarian") && !cardType.equalsIgnoreCase("Vigan")) {
			System.out.println("Sorry but there is no " + cardType + " card");
			System.out.print(" --> Try again: (Carnivore, Halal, Kosher, Pescatarian, Vegetarian, Vigan): ");
			cardType = key.next();
			key.nextLine();
		}
		System.out.print(" --> Id of the prepaid card owner: ");
		int cardId = key.nextInt();
		key.nextLine();
		System.out.print(" --> Expiry day number and month (seperate by a space): ");
		int cardDay = key.nextInt();
		int cardMonth = key.nextInt();
		key.nextLine(); 
		posList[posAdd].addCard(new PrePaiCard(cardType, cardId, cardDay, cardMonth)); // compile the inputed information to make the new card in the addCard method of the PoS class
		System.out.println("You now have " + posList[posAdd].posPrepaidCards() + " PrePaiCard(s)\n"); // say how many cards there is now 
	}
	
	// method to remove a card
	static void optionRemoveCard(PoS posList[]) {
		System.out.print("Which PoS to you want to remove an PrePaiCard to? (Enter number 0 to " + (posList.length - 1) + "): ");
		int posRemove = numberSelection(posList, key.nextInt());
		// if there is no card in the card list
		if (posList[posRemove].posPrepaidCards() == 0) {
			System.out.println("Sorry that PoS has no PrePaicards"); // print this
		}
		// if there are cards
		else {
			System.out.println("(Enter number 0 to " + (posList[posRemove].posPrepaidCards() - 1) + "):");
			int cardRemove = key.nextInt(); // make the user choose which card to remove 
			key.nextLine();
			// this while will make sure the choice made by the user is valid (it actually exist)
			while (cardRemove < 0 || cardRemove > (posList[posRemove].posPrepaidCards() - 1)) {
    			System.out.println("Sorry but there is no card " + cardRemove);
    			System.out.println("--> Try again: (Enter number 0 to " + (posList[posRemove].posPrepaidCards() - 1) + "):");
    			cardRemove = key.nextInt();
    			key.nextLine();
    		}
			posList[posRemove].removeCard(cardRemove); // use the removeCard of the PoS class
		}
	}
	
	// method to change the expiry date of a card in a PoS
	static void optionUpdateCard(PoS posList[]) {
		System.out.print("Which PoS to you want to update an PrePaiCard from? (Enter number 0 to " + (posList.length - 1) + "): ");
		int posUpdate = numberSelection(posList, key.nextInt());
		if (posList[posUpdate].posPrepaidCards() == 0) {
			System.out.println("Sorry that PoS has no PrePaicards");
		}
		else {
			System.out.println("Which PrePaiCard do you want to update? (Enter number 0 to " + (posList[posUpdate].posPrepaidCards() - 1) + "):");
			int cardChangeExp = key.nextInt();
			key.nextLine();
			while (cardChangeExp < 0 || cardChangeExp > (posList[posUpdate].posPrepaidCards() - 1)) {
    			System.out.println("Sorry but there is no card " + cardChangeExp);
    			System.out.println("--> Try again: (Enter number 0 to " + (posList[posUpdate].posPrepaidCards() - 1) + "):");
    			cardChangeExp = key.nextInt();
    			key.nextLine();
    		}
			System.out.print(" --> Enter new expiry date day number and month (seperate by a space): ");
			int newDay = key.nextInt(); // collect the input made by the user for the new day 
			int newMonth = key.nextInt(); // and month
			posList[posUpdate].changeExpDate(cardChangeExp, newDay, newMonth); // update the dates wil the changeExpDate method of the PoS class
			System.out.println("Expiry Date updated.");
		}
	}
	
	// add sales to a PoS
	static void optionAddSales(PoS posList[]) {
		System.out.print("Which PoS to you want to add Sales to? (Enter number 0 to " + (posList.length - 1) + "): ");
		int posAddSales = numberSelection(posList, key.nextInt());
		System.out.print("How many junior, teen ,medium,big and family meal menu do you want to add?\n"
				+ "Enter 5 numbers seperated by a space): ");
		posList[posAddSales].changePosSales(key.nextInt(), key.nextInt(), key.nextInt(), key.nextInt(), key.nextInt()); // each int information inputed by the user is used in the method changePosSales
		System.out.println("You now have $" + (double) posList[posAddSales].posSales() + "\n"); //casting int to double for the wanted format 
	}
	
	// this method was created because the choice of the index of the PoS often reoccurs 
	// it will make sure the input is a valid value 
	static int numberSelection (PoS posList[], int input) {
		key.nextLine();
		while (input < 0 || input > 4) {
			System.out.println("Sorry but there is no PoS number " + input);
			System.out.print("--> Try again: (Enter number 0 to " + (posList.length - 1) + "): ");
			input = key.nextInt();
		}
		return input;
	}	
	
	//----------------------------------------------------------------------------------------------------------------------
	// for the main method, ask the user to choose an action 
	// depending on their input, the program will react in an according way
	//----------------------------------------------------------------------------------------------------------------------
	public static void main(String[] args) {
		// since we hard-coded the values in the PoSs, to change inputs go modify the initialization function
		PoS[] posList = initialization();
		
		// welcome banner
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n"
				+ "| Welcome to Concordia CostLessBites Catering Sales Counter Application         |\n" 
				+ "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		
		int choice; // initialize the choice variable before the do-while loop
		do {
			// print the menu
			System.out.println("| What would you like to do?                                                    |");
	        System.out.println("| 1 >> See the content of all PoSs                                              |");
	        System.out.println("| 2 >> See the content of one PoS                                               |");
	        System.out.println("| 3 >> List PoSs with same $ amount of sales                                    |");
	        System.out.println("| 4 >> List PoSs with same number of Sales categories                           |");
	        System.out.println("| 5 >> List PoSs with same $ amount of Sales and same number of prepaid cards   |");
	        System.out.println("| 6 >> Add a Prepaid Card to an existing PoS                                    |");
	        System.out.println("| 7 >> Remove an existing prepaid card from a PoS                               |");
	        System.out.println("| 8 >> Update the expiry date of an existing Prepaid card                       |");
	        System.out.println("| 9 >> Add Sales to a PoS                                                       |");
	        System.out.println("| 0 >> To quit                                                                  |");
	        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
	        System.out.print("Please enter your choice and press <Enter>:");
	        
	        choice = key.nextInt(); // scan the user's answer
	        key.nextLine();
	        
	        // use switch statement to access the wanted method
	        switch(choice) {
	        	case 1:
	        		optionPrintAll(posList);
	        		break;
	        		
	        	case 2:
	        		optionPrintOne(posList);
	        		break;
	        		
	        	case 3: case 4: case 5:
	        		optionPrintEqual(posList, choice);
	        		break;
	        		
	        	case 6:
	        		optionAddCard(posList);
	        		break;
	        
	        	case 7: 
	        		optionRemoveCard(posList);
	        		break;

	        	case 8:
	        		optionUpdateCard(posList);
	        		break;

	        	case 9:
	        		optionAddSales(posList);
	        		break;

	        	case 0: // because it is a do while loop, the whole loop will run before exiting the loop
	        		break; 
	        		
	        	default:
	        		System.out.println("Sorry that is not a valid choice. Try again."); // if the choice value is not in the available choices 
	        }

		} while(choice != 0); // the loop will run as long as choice is not equal to 0
		
		System.out.println("Thank you for using Concordia CostLessBites Catering Sales Counter Application!"); // bye 
		
		key.close();
	}
}
