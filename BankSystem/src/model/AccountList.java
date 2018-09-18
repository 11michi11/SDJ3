package model;

import javax.naming.InsufficientResourcesException;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class AccountList {

	private List<Account> accounts;

	public AccountList() {
		accounts = new LinkedList<>();
	}

	public void addAcount(Account account) {
		accounts.add(account);
	}

	public void withdraw(int amount, int accountNumber) throws InsufficientResourcesException,NoSuchElementException {
		for (Account a : accounts) {
			if (a.getAccountNo() == accountNumber) {
				a.withdraw(amount);
				return;
			}
		}
		throw new NoSuchElementException("model.Account doesn't exist");
	}

	public void insert(double amount, int accountNumber) throws Exception {
		for (Account a : accounts) {
			if (a.getAccountNo() == accountNumber) {
				a.insert(amount);
				return;
			}
		}
		throw new Exception("model.Account doesn't exist");
	}

	public Account exists(int accountNumber) {
		for (Account a : accounts) {
			if (a.getAccountNo() == accountNumber)
				return a;
		}
		return null;
	}

	public String toString() {
		StringBuilder returnString = new StringBuilder();
		for (Account a : accounts)
			returnString.append(a.getAccountNo()).append("\n");
		return returnString.toString();
	}

	public void addAll(List<Account> accounts) {
		this.accounts.addAll(accounts);
	}

	public double getBalance(int accountNumber) {
		for (Account a : accounts) {
			if (a.getAccountNo() == accountNumber)
				return a.getBalance();
		}
		return -1;
	}
}
