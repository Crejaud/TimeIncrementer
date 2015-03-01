/* Made by Corentin (Corey) Rejaud
 * 02/14/2015
 * Maxeta Intern Project Summer 2015
 */

package corentin.rejaud;

import java.util.Scanner;

public class Driver { //This is the driver class
	static Scanner scan = new Scanner(System.in); //gets user input
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// INTRODUCTION
		System.out.println("Maxeta Intern Project Summer 2015 - Time Incrementer");
		System.out.println("Made by Corentin (Corey) Rejaud");
		/* SETUP:
		 * First, find out if military or standard time (if standard, find AM or PM)
		 * Second, find out hrs
		 * Third, find out mins
		 * Fourth, find out secs
		 */
		
		// DECLARATIONS
		int hrs, mins, secs;
		boolean isMilitary, isAM = true;
		
		// Check for isMilitary
		System.out.print("Set Timer in Military? [Y/N]: ");
		isMilitary = getBooleanOption();
		
		// Check for isAM
		if (!isMilitary) {
			System.out.print("Is it AM? [Y/N]: ");
			isAM = getBooleanOption();
		}
		
		// Check for hrs
		System.out.print("Enter hour: ");
		hrs = getHrsOption(isMilitary);
		
		// Check for mins
		System.out.print("Enter minute: ");
		mins  = getMSOption();
		
		// Check for secs
		System.out.print("Enter second: ");
		secs = getMSOption();
		
		// Create Time Object
		Time t = new Time(hrs, mins, secs, isMilitary, isAM);
		// DONE WITH SETUP
		
		boolean checker = true;
		do{
			int option = getOption();
			if (option == 1) {  //increment hours
				System.out.print("Enter # of times to increment: ");
				int incrHrs = getIDOption();
				//System.out.println(incrHrs); //TEST
				for (int i = 0; i < incrHrs; i++)
					t.IncrementHrs();
			}
			else if (option == 2) { //increment minutes
				System.out.print("Enter # of times to increment: ");
				int incrMins = getIDOption();
				for (int i = 0; i < incrMins; i++)
					t.IncrementMins();
			}
			else if (option == 3) { //increment seconds
				System.out.print("Enter # of times to increment: ");
				int incrSecs = getIDOption();
				for (int i = 0; i < incrSecs; i++)
					t.IncrementSecs();
			}
			else if (option == 4) { //decrement hours
				System.out.print("Enter # of times to decrement: ");
				int decrHrs = getIDOption();
				for (int i = 0; i < decrHrs; i++)
					t.DecrementHrs();
			}
			else if (option == 5) { //decrement minutes
				System.out.print("Enter # of times to decrement: ");
				int decrMins = getIDOption();
				for (int i = 0; i < decrMins; i++)
					t.DecrementMins();
			}
			else if (option == 6) { //decrement seconds
				System.out.print("Enter # of times to decrement: ");
				int decrSecs = getIDOption();
				for (int i = 0; i < decrSecs; i++)
					t.DecrementSecs();
			}
			else if (option == 7) { //change Time Type
				t.Convert();
				System.out.print("Time Type has been set to ");
				if(t.isMilitary())
					System.out.println("Military Time");
				else
					System.out.println("Standard Time");
			}
			else if (option == 8) { //display time
				System.out.println(t); //calls t.toString()
			}
			else if (option == 9) { //quits
				checker = false;
			}
		
		} while (checker);
	}
	
	//option method for both isMilitary and isAM
	static boolean getBooleanOption() {
		String response = scan.next().toLowerCase(); //saves response, not case sensitive
		scan.nextLine();	//skips line for easier readability
		while (!response.equals("y") && !response.equals("n")) { //if not Y or N, loop
			System.out.print("\tYou must enter either Y or N => ");
			response = scan.next().toLowerCase();
			scan.nextLine();
		}
		
		if (response.equals("y")) //if it is y
			return true;
		else //if it is n
			return false;
	}
	
	//option method for finding hrs
	static int getHrsOption(boolean isMilitary) {
		int response = scan.nextInt();
		scan.nextLine();
		if (isMilitary) { //it is Military Time
			while(response < 0 || response > 23) { //Must be in range
				System.out.print("\tYou must enter a valid number 0-23 => ");
				response = scan.nextInt();
				scan.nextLine();
			}
		}
		else { //it is in Standard Time
			while(response < 1 || response > 12) { //Must be in range
				System.out.print("\tYou must enter a valid number 1-12 => ");
				response = scan.nextInt();
				scan.nextLine();
			}
		}
		
		return response;
	}
	
	//option method for mins and secs
	static int getMSOption() {
		int response = scan.nextInt();
		scan.nextLine();
		while(response < 0 || response > 59) { //Must be in range
			System.out.print("\tYou must enter a valid number 0-59 => ");
			response = scan.nextInt();
			scan.nextLine();
		}
		
		return response;
	}
	
	//option method to test functionality of Time Library
	static int getOption() {
		System.out.println("\tChoose action: ");
		System.out.println("(1) Increment Hours, ");
		System.out.println("(2) Increment Minutes, ");
		System.out.println("(3) Increment Seconds, ");
		System.out.println("(4) Decrement Hours, ");
		System.out.println("(5) Decrement Minutes, ");
		System.out.println("(6) Decrement Seconds, ");
		System.out.println("(7) Change Time Type, ");
		System.out.println("(8) Display Time, ");
		System.out.println("(9) Quit");
		int response = scan.nextInt();
		scan.nextLine();
		while (response != 1 && response != 2 && response != 3 && response !=4 && response !=5 && response !=6 && response !=7 && response !=8 && response !=9) {
			System.out.print("\tYou must enter a valid numer 1-9 => ");
			response = scan.nextInt();
			scan.nextLine();
		}
		return response;
	}
	
	//option method to ask # of times to increment or decrement
	static int getIDOption() {
		int response = scan.nextInt();
		scan.nextLine();
		while(response < 0) {
			System.out.print("\tYou must enter a valid number 0+ => ");
			response = scan.nextInt();
			scan.nextLine();
		}
		return response;
	}

}
