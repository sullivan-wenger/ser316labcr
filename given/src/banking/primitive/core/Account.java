/**
* File: Account.java
* @author Edited by Matthew Corless and Sullivan Wenger
* @date 2/20/2017
* 
* Description: 
*   The class of all bank accounts.
*   Can be checking or savings.
*/

package banking.primitive.core;

public abstract class Account implements java.io.Serializable {

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
    * Method: toString
    * 
    * @param N/A
    * @return string form of the account, matches the regex /Account .* has \$\d+\.\d+and is (OPEN|CLOSED|OVERDRAWN)/
    * 
    * Description: Describes the account in a human comprehensible way.
    */
    public String toString() {
        return "Account " + name + " has $" + balance + "and is " + getState()
                + "\n";
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

    private static final long serialVersionUID = 1L;

    private enum State {
        OPEN, CLOSED, OVERDRAWN
    };

    private float balance = 0.0F;
    private String name;
    private State state;

}
