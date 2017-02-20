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
	
	/**
	  Method: lookup
	  Inputs: none
	  Returns: The AccountServer from ServerSolution

	  Description: Creates a new instance of ServerSolution and returns it.
	*/
	public AccountServer lookup() {
		return new ServerSolution();
	}
}
