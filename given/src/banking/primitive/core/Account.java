package banking.primitive.core;

public abstract class Account implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    protected enum State {
        OPEN, CLOSED, OVERDRAWN
    };

    protected float balance = 0.0F;
    protected String name;
    private State state;

    protected Account(String n) {
        name = n;
        state = State.OPEN;
    }

    protected Account(String n, float b) {
        this(n); 
        balance = b;
    }

    /**
     * Method: getName
     * 
     * @param N/A
     * @return name of the Account
     * 
     * Description: Get the name on the account.
     */
    public final String getName() {
        return name;
    }

    /**
     * Method: getBalance
     * 
     * @param N/A
     * @return balance in the Account
     * 
     * Description: Get the current balance in the account
     */
    public final float getBalance() {
        return balance;
    }

    /**
     * Method: deposit
     * 
     * @param parameter
     *            amount is a deposit and must be > 0
     * @return true if the deposit was successful, false if not due to amount or
     *         invalid state
     * 
     * Description: Adds money to an account. May not be done if the account is CLOSED
     */
    public abstract boolean deposit(float amount);

    /**
     * Method: withdraw
     * 
     * @param parameter
     *            amount is a withdrawal and must be > 0
     * @return true if the deposit was successful, false if not due to amount or
     *         invalid state
     * 
     * Description: 
     * Takes money out of an account. If the balance falls below 0 then the
     * account is moved to an OVERDRAWN state
     */
    public abstract boolean withdraw(float amount);

    /**
     * Method: getType
     * 
     * @param N/A
     * @return either "Checking" or "Savings"
     * 
     * Description: Tell whether the account is checking or savings.
     */
    public abstract String getType();

    protected final State getState() {
        return state;
    }

    protected final void setState(State s) {
        state = s;
    }
    
    /**
    * Method: toString
    * 
    * @param N/A
    * @return a string that matches the regex /Account .* has \$\d+\.\d+and is (OPEN|CLOSED|OVERDRAWN)/
    * 
    * Description: Describes the account in a human comprehensible way.
    */
    public String toString() {
        return "Account " + name + " has $" + balance + "and is " + getState()
                + "\n";
    }
}
