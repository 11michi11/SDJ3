package database;

import model.Account;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface DatabaseProxy {

	void saveState(List<Account> accountList);

	List<Account> restoreState();

	void updateAccount(Account account);

	void addAccount(Account account);

}
