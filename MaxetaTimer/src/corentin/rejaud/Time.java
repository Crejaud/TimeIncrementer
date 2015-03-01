/* Made by Corentin (Corey) Rejaud
 * 02/14/2015
 * Maxeta Interns Project Summer 2015
 */

package corentin.rejaud;

public class Time {

	private int myHrs, myMins, mySecs;
	private boolean isMilitary, isAM;
	/* isMilitary = False; Display in Standard time
	 * isMilitary = True; Display in Military time
	 * isAM = False; It is PM
	 * isAM = True; It is AM
	 */
	
	// Default Constructor, sets all times to 0, AM, and Military Time
	public Time() {
		myHrs = 0;
		myMins = 0;
		mySecs = 0;
		isMilitary = true;
		isAM = true;
	}
	
	// Main Constructor
	public Time(int h, int m, int s, boolean Mil, boolean AM) {
		myHrs = h;
		myMins = m;
		mySecs = s;
		isMilitary = Mil;
		if (isMilitary && myHrs <= 12) // Safety reasons
			AM = true;
		if (isMilitary && myHrs > 12) // Safety reasons
			AM = false;
		isAM = AM;
	}

	//Increment by 1
	public void IncrementHrs() {
		if (isMilitary()) { //it is military time
			/* 2 Cases:
			 * 1) hrs = 11 -> 12: change to PM
			 * 2) hrs = 23 -> 0: change to AM
			 * Otherwise, simply increment
			 */
			
			setHrs(getHrs() + 1); //Increment by 1
			if (getHrs() == 12)
				setMeridiem(false); //change to PM
			if (getHrs() == 24) {
				setHrs(0);       //Set hrs to 0
				setMeridiem(true); //change to AM
			}
			
			return; //returns since we may have changed meridiem
		}
		else { //it is standard time
			/* Cases:
			 * 1) hrs = 11 -> 12: change meridiem
			 * 2) hrs = 12 -> 1
			 */
			
			setHrs(getHrs() + 1); //Increment by 1
			if (getHrs() == 12)
				changeMeridiem(); //change to either AM or PM
			if (getHrs() == 13)
				setHrs(1);        //Set hrs to 1
			
		}
	}
	public void IncrementMins() {
		setMins(getMins() + 1); //Increment by 1
		if (getMins() == 60) {
			setMins(0); 		//Set mins to 0
			IncrementHrs(); 	//Increment Hrs by 1
		}
	}
	public void IncrementSecs() {
		setSecs(getSecs() + 1); //Increment by 1
		if (getSecs() == 60) {
			setSecs(0);			//Set secs to 0
			IncrementMins();	//Increment Mins by 1
		}
	}
	
	//Decrement by 1
	public void DecrementHrs() {
		if (isMilitary()) { //it is military time
			/* 2 Cases:
			 * 1) hrs = 0 -> -1: set to 23 and change to PM
			 * 2) hrs = 12 -> 11: change to AM
			 */
			
			setHrs(getHrs() - 1);
			if (getHrs() == -1) { 	// Case 1
				setMeridiem(false); //set to PM
				setHrs(23);
			}
			if (getHrs() == 11) 	// Case 2
				setMeridiem(true); //set to AM
		}
		else { //it is standard time
			/* 2 Cases:
			 * 1) hrs = 1 -> 0: set to 12
			 * 2) hrs = 12 -> 11: change meridiem
			 */
			
			setHrs(getHrs() - 1);
			if (getHrs() == 0) 		// Case 1
				setHrs(12);
			if (getHrs() == 11)  	// Case 2
				changeMeridiem();
			
		}
	}
	public void DecrementMins() {
		setMins(getMins() - 1); //Decrement by 1
		if (getMins() == -1) { 
			setMins(59);		//Set mins to 59
			DecrementHrs();		//Decrement hrs by 1
		}
	}
	public void DecrementSecs() {
		setSecs(getSecs() - 1); //Decrement by 1
		if (getSecs() == -1) {
			setSecs(59);		//Set secs to 59
			DecrementMins();	//Decrement mins by 1
		}
	}
	
	// Converts between military and standard time
	public void Convert() {
		if (isMilitary()) { //if it is military time, change to standard time
			/* 2 Cases:
			 * 1) Hrs = 0 -> add 12 and set to AM
			 * 2) It's PM (check if myHrs is != 12)
			 */
			if (0 <= getHrs() && getHrs() <= 11) { // It's AM
				setMeridiem(true); //Ensures it is AM
				if (getHrs() == 0) // if myHrs = 0, set to 12
					setHrs(12);
			}
			else { //It's PM
				setMeridiem(false); //Ensures it is PM
				if (getHrs() != 12) // if myHrs is anything but 12, subtract 12 from myHrs
					setHrs(getHrs() - 12);
			}
			
			changeTimeType(); //Changes isMilitary to False
		}
		
		else { //if it is standard time, change to Military time
			/* 2 Cases:
			 * 1) myHrs = 12 & isAM = True -> myHrs = 0
			 * 2) myHrs != 12 & isAM = False -> myHrs += 12
			 * Otherwise, don't change myHrs
			 */
			// Case 1
			if (getHrs() == 12 && isAM())
				setHrs(0); //sets hrs to 0
			// Case 2
			if (getHrs() != 12 && !isAM())
				setHrs(getHrs() + 12); // adds 12 to Hrs
			
			changeTimeType(); //Changes isMilitary to True
		}
	}
	
	// ACCESSORS
	public int getHrs() {
		return myHrs;
	}
	
	public int getMins() {
		return myMins;
	}
	
	public int getSecs() {
		return mySecs;
	}
	
	public boolean isMilitary() {
		return isMilitary;
	}
	
	public boolean isAM() {
		return isAM;
	}
	
	// MUTATORS
	// Excepts that h will be within range; User should not have access to this
	private void setHrs(int h) {
		myHrs = h;
	}
	
	private void setMins(int m) {
		myMins = m;
	}
	
	private void setSecs(int s) {
		mySecs = s;
	}
	
	/* I chose this title for method since
	 * AM = Ante Meridiem
	 * PM = Post Meridiem
	 */
	// Only available when in Standard Time
	public void changeMeridiem() {
		if (!isMilitary)
			isAM = !isAM;
	}
	// Part of Convert(), user should not have access to this
	private void setMeridiem(boolean AM) {
		isAM = AM;
	}
	
	// Part of Convert(), User should not have access to this
	private void changeTimeType() {
		isMilitary = !isMilitary;
	}
	
	// This overrides toString() for easy use in System.out.print()
	public String toString() {
		String s = ""; //s will be the string format of time
		
		if (getHrs() < 10) //if hrs is only 1 digit, add 0 in front
			s += "0";
		s += myHrs + ":";
		if (getMins() < 10) //if mins is only 1 digit, add 0 in front
			s += "0";
		s += myMins + ":";
		if (getSecs() < 10) //if secs is only 1 digit, add 0 in front
			s += "0";
		s += mySecs;
		
		if (isMilitary()) //it is Military Time
			return s;
		else { //it is Standard Time
			if (isAM()) //it is AM
				return s + " AM";
			else		//it is PM
				return s + " PM";
		}
			
	}
}
