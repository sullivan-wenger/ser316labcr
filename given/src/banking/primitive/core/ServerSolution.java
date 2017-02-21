/*
  File:	ServerSolution.java
  Author: Revised by Matthew Corless and Sullivan Wenger.
  Date:	2/20/2017
  
  Description: Server Solution implements the AccountServer interface. It acts as a server full of accounts
  for the Banking project.
*/
package banking.primitive.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.io.*;

import banking.primitive.core.Account.State;

class ServerSolution implements AccountServer {


	static String fileName = "accounts.ser";

	Map<String,Account> accountMap = null;

	/**
	  Method: Default Constructor
	  Inputs: N/A
	  Returns: N/A

	  Description: SeverSolution() creates a new map of accounts using the contents of accounts.ser
	*/
	public ServerSolution() {
		accountMap = new HashMap<String,Account>();
		File file = new File(fileName);
		ObjectInputStream in = null;
		try {
			if (file.exists()) {
				System.out.println("Reading from file " + fileName + "...");
				in = new ObjectInputStream(new FileInputStream(file));

				Integer sizeI = (Integer) in.readObject();
				int size = sizeI.intValue();
				for (int i=0; i < size; i++) {
					Account acc = (Account) in.readObject();
					if (acc != null) {
						accountMap.put(acc.getName(), acc);
					}
				}
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		finally {
			if (in != null) {
				try {
					in.close();
				}
				catch (Throwable t) {
					t.printStackTrace();
				}
			}
		}
	}
	
	private boolean newAccountFactory(String type, String name, float balance)
		throws IllegalArgumentException {
		
		if (accountMap.get(name) != null) {
			return false;
		}
		
		Account acc;
		if ("Checking".equals(type)) {
			acc = new Checking(name, balance);

		}
		else if ("Savings".equals(type)) {
			acc = new Savings(name, balance);

		}
		else {
			throw new IllegalArgumentException("Bad account type:" + type);
		}
		try {
			accountMap.put(acc.getName(), acc);
		}
		catch (Exception exc) {
			return false;
		}
		return true;
	}

	/**
	  Method: newAccount
	  Inputs: type of account, name of the account, starting balance of account
	  Returns: boolean. Returns true if a new account was made and false if a new account was not made.

	  Description: newAccount creates a new account based on the inputs. If the account is negative then it will
	  return an IllegalArgumentException. Returns true if a new account is made.
	*/
	public boolean newAccount(String type, String name, float balance) 
		throws IllegalArgumentException {
		
		if (name.equals(getAccount(name).getName()))
		{
			throw new IllegalArgumentException("Account name already exists.");
		}
		if (balance < 0.0f) {
			throw new IllegalArgumentException("New account may not be started with a negative balance");
		}
		
		return newAccountFactory(type, name, balance);
	}
	
	/**
	  Method: closeAccount
	  Inputs: name of account
	  Returns: boolean. Returns true if the account was closed and false if the account was not found.

	  Description: Gets the account from accountMap and sets the account to closed. Returns true if account was
	  set to closed. Returns false if account was not found.
	*/
	public boolean closeAccount(String name) {
		Account acc = accountMap.get(name);
		if (acc == null) {
			return false;
		}
		acc.setState(State.CLOSED);
		return true;
	}

	public Account getAccount(String name) {
		return accountMap.get(name);
	}

	public List<Account> getAllAccounts() {
		return new ArrayList<Account>(accountMap.values());
	}
	
	public List<Account> getActiveAccounts() {
		List<Account> result = new ArrayList<Account>();

		for (Account acc : accountMap.values()) {
			if (acc.getState() != State.CLOSED) {
				result.add(acc);
			}
		}
		return result;
	}
	
	/**
	  Method: saveAccounts
	  Inputs: N/A
	  Returns: N/A

	  Description: Saves accounts currently in accountMap to accounts.ser
	*/
	public void saveAccounts() throws IOException {
		ObjectOutputStream out = null; 
		try {
			out = new ObjectOutputStream(new FileOutputStream(fileName));

			out.writeObject(Integer.valueOf(accountMap.size()));
			for (int i=0; i < accountMap.size(); i++) {
				out.writeObject(accountMap.get(i));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new IOException("Could not write file:" + fileName);
		}
		finally {
			if (out != null) {
				try {
					out.close();
				}
				catch (Throwable t) {
					t.printStackTrace();
				}
			}
		}
	}
	
	private static String fileName = "accounts.ser";
	private Map<String,Account> accountMap = null;

}
