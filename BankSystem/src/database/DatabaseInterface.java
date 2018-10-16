package database;

import model.Account;
import server.BankInterface;
import server.BankServer;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

@WebService
public interface DatabaseInterface {

	@WebMethod
	public void saveState(List<Account> accountList);

	@WebMethod
	public List<Account> restoreState();

	@WebMethod
	public void updateAccount(Account account);

	@WebMethod
	public void addAccount(Account account);

	@WebMethod
	public void registerAsObserver(BankInterface observer);


}
