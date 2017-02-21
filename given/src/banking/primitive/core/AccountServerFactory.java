/**
* File: AccountServerFactory.java
* @author Edited by Matthew Corless and Sullivan Wenger
* @date 2/20/2017
* 
* Description: Singleton class that creates an account server.
*/

package banking.primitive.core;


public class AccountServerFactory {

	protected static AccountServerFactory singleton = null;

	protected AccountServerFactory() {

	}

	public static AccountServerFactory getMe() {
		if (singleton == null) {
			singleton = new AccountServerFactory();
		}

		return singleton;
	}

	public AccountServer lookup() {
		return new ServerSolution();
	}
}
