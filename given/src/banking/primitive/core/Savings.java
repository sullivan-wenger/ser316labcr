package banking.primitive.core;

/**
	Class: Savings.java
	
	Description: Extends Account Class. Implements methods from the abstract class Account.java. Savings.java
	should deal with all accounts that are of type "Savings".
*/
public class Savings extends Account {
	public Savings(String name) {
		super(name);
	}

	public Savings(String name, float balance) throws IllegalArgumentException {
		super(name, balance);
	}

	/**
	 * A deposit comes with a fee of 50 cents per deposit
	 */
	public boolean deposit(float amount) {
		if (getState() == State.CLOSED) {
			return false;
		}
		balance = balance + amount - 0.50F;
		if (balance >= 0.0f) {
			setState(State.OPEN);
		}
		return true;
	}

	/**
	 * A withdrawal. After 3 withdrawals a fee of $1 is added to each withdrawal.
	 * An account whose balance dips below 0 is in an OVERDRAWN state
	 */
	public boolean withdraw(float amount) {
		if (getState() == State.OPEN && amount > 0.0f) {
			balance = balance - amount;
			numWithdraws++;
			if (numWithdraws > 3) {
				balance = balance - 1.0f;
			}
			// KG BVA: should be < 0
			if (balance <= 0.0f) {
				setState(State.OVERDRAWN);
			}
			return true;
		}
		return false;
	}
	
	public String getType() { return "Checking"; }

	public String toString() {
		return "Savings: " + getName() + ": " + getBalance();
	}
	
	private static final long serialVersionUID = 111L;
	private int numWithdraws = 0;
}
