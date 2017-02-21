/**
* File: AccountServerFactory.java
* @author Edited by Matthew Corless and Sullivan Wenger
* @date 2/20/2017
* 
* Description: Singleton class that creates an account server.
*/

package banking.primitive.core;

/**
	Class: AccountServerFactory
	
	Description: Deals with the Account Server. Contains methods to look up current ServerSolution instance.
*/
public class AccountServerFactory {

	protected AccountServerFactory() {

	}

	public static AccountServerFactory getMe() {
		if (singleton == null) {
			singleton = new AccountServerFactory();
		}

		return singleton;
	}
	
	/**
	  Method: lookup
	  Inputs: none
	  Returns: The AccountServer from ServerSolution

	  Description: Creates a new instance of ServerSolution and returns it.
	*/
	public AccountServer lookup() {
		return new ServerSolution();
	}
	
	private static AccountServerFactory singleton = null;
}
