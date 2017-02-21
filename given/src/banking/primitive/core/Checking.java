/*
	File: Checking.java	
	Author:	Revised by Matthew Corless and Sullivan Wenger
	Date: 2/19/2017	
	
	Description: Checking extends the Account Class. It is used for Accounts with type "Checking". Checking.java 
	implements the classes from the abstract class Account.
*/
package banking.primitive.core;

/**
* Class: Checking
* 
* Description: Implementation of checking account.
*/
public class Checking extends Account {
	
	/**
	  Method: Constructor for Checking class
	  Inputs: name of Checking account
	  Returns: N/A

	  Description: Utilizes Constructor from parent class Account.java.
	*/
	private Checking(String name) {
		super(name);
	}

    /**
    * Method: createChecking
    *
    * @param name the desired name on the checking account
    * @return the created checking account
    * 
	  * Description: Creates a new checking account with a default balance of 0.
	*/
  
    public static Checking createChecking(String name) {
        return new Checking(name);
    }
    
    /**
	    Method: Checking
	    Inputs: name: name of account, balance: starting balance of account
	    Returns: N/A
	
	    Description: Creates a Checking account with the given name and balance.
    */

	/**
	* Method: Checking
	*
	* @param name the name on the account
	*	balance the initial balance of the account
	* @return N/A
	* 
	* Description: Create a checking account with a name and initial balance
	*/
	public Checking(String name, float balance) {
		super(name, balance);
	}

	/**
	 * Method: deposit
	 * 
	 * @param float is the deposit amount
	 * @return true if successful, false otherwise
	 * 
	 * Description: A deposit may be made unless the Checking account is closed or balance is negative
	 */
	public boolean deposit(float amount) {
		if (getState() != State.CLOSED && amount > 0.0f) {
			balance = balance + amount;
			if (balance >= 0.0f) {
				setState(State.OPEN);
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Method: withdraw
	 * 
	 * @param how much to withdraw
	 * @reutrn Boolean. True if withdraw occurred and false if there was an error.
   *
	 * Description: Withdraws given amount from the accounts' balance. After 10 withdrawals 
	 * a fee of $2 is charged per transaction You may continue to withdraw an overdrawn 
	 * account until the balance is below -$100
	*/

	public boolean withdraw(float amount) {
		if (amount > 0.0f) {		
			// KG: incorrect, last balance check should be >=
			if (getState() == State.OPEN || (getState() == State.OVERDRAWN && balance > -100.0f)) {
				balance = balance - amount;
				numWithdraws++;
				if (numWithdraws > 10) {
					balance = balance - 2.0f;
				}
				if (balance < 0.0f) {
					setState(State.OVERDRAWN);
				}
				return true;
			}
		}
		return false;
	}

	/**
	* Method: getType
	*
	* @param N/A
	* @return the string "Checking"
	*
	* Description: 
	* 	Tells which type of account
	* 	The analogous methods on savings returns "Savings"
	*/

	public String getType() {
		return "Checking";
	}
	
	/**
	* Method: toString
	* 
	* @param N/A
	* @return a string form of the account, fits the regex /Checking: .*: \d+\.\d+/
	* 
	* Description: A human comprehensible representation of the account.
  */
  
	public String toString() {
		return "Checking: " + getName() + ": " + getBalance();
	}
	
	private static final long serialVersionUID = 11L;
	private int numWithdraws = 0;
}
