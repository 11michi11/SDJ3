package model;

import javax.naming.InsufficientResourcesException;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "account", schema = "bank")
public class Account implements Serializable{

	private static int nextNo = 0;
	@Column(name = "balance")
	private double balance;

	@Id
	@Column(name = "accountNo")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int accountNo;
	@Column(name = "owner")
	private String owner;

	public Account(String owner) {
		this.owner = owner;
		balance = 0;
		accountNo = ++nextNo;
		//account number UUID
	}

	protected Account() {
	}

	private boolean checkBalance(int amount) {
		return !(amount > balance);
	}

	public void withdraw(int amount) throws InsufficientResourcesException {
		if (checkBalance(amount)) {
			balance -= amount;
		} else {
			throw new InsufficientResourcesException("there is no enough money");
		}
	}

	public void insert(double amount) {
		balance += amount;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
		nextNo = accountNo;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "model.Account{" +
				"balance=" + balance +
				", accountNumber=" + accountNo +
				", owner='" + owner + '\'' +
				'}';
	}

	public int getAccountNo() {
		return accountNo;
	}

	public String getOwner() {
		return owner;
	}

	public double getBalance() {
		return balance;
	}
}
