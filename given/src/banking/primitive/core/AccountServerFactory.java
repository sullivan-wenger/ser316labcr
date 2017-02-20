package banking.primitive.core;

/**
	Class: AccountServerFactory
	
	Description: Deals with the Account Server. Contains methods to look up current ServerSolution instance.
*/
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
