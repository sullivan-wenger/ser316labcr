package banking.primitive.core;

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
	  Method: createChecking
	  Inputs: name of account
	  Returns: Checking object

	  Description: Creates a new checking account.
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
	public Checking(String name, float balance) {
		super(name, balance);
	}

	/**
	 * A deposit may be made unless the Checking account is closed
	 * @param float is the deposit amount
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
	  Method: withdraw
	  Inputs: amount: amount of money being taken from account's balance.
	  Returns: Boolean. True if withdraw occurred and false if there was an error.

	  Description: Withdraws given amount from the accounts' balance. After 10 withdrawals 
	  a fee of $2 is charged per transaction You may continue to withdraw an overdrawn 
	  account until the balance is below -$100
	*/
	public boolean withdraw(float amount) {
		if (amount > 0.0f) {		
			// KG: incorrect, last balance check should be >=
			if (getState() == State.OPEN || (getState() == State.OVERDRAWN && balance > -100.0f)) {
				balance = balance - amount;
				numWithdraws++;
				if (numWithdraws > 10)
					balance = balance - 2.0f;
				if (balance < 0.0f) {
					setState(State.OVERDRAWN);
				}
				return true;
			}
		}
		return false;
	}

	public String getType() { return "Checking"; }
	
	/**
	  Method: toString
	  Inputs: none
	  Returns: String form of Checking account.

	  Description: Creates a string version of account with the form "Checking: name: balance"
	*/
	public String toString() {
		return "Checking: " + getName() + ": " + getBalance();
	}
	
	private static final long serialVersionUID = 11L;
	private int numWithdraws = 0;
}
