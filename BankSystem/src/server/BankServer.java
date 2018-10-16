package server;

import clients.ClientObserver;
import database.Database;
import database.DatabaseInterface;
import database.DatabaseProxy;
import model.Account;
import model.AccountList;

import javax.jws.WebService;
import javax.naming.InsufficientResourcesException;
import javax.xml.ws.Endpoint;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

@WebService
public class BankServer implements BankInterface {

    private AccountList accounts;
    private DatabaseInterface database;
    private HashMap<Integer, List<ClientObserver>> observers;

    public BankServer() {
        accounts = new AccountList();
        observers = new HashMap<>();
        // get databse proxy
        database.registerAsObserver(this);
        accounts.addAll(database.restoreState());
        System.out.println(this.accounts);
    }

    @Override
    public void createAccount(String owner) {
        Account account = new Account(owner);
        accounts.addAcount(account);
        database.addAccount(account);
        System.out.println(owner + " account was added to database");
    }

    @Override
    public void insert(double amount, int accountNumber) throws Exception {
        accounts.insert(amount, accountNumber);
        database.updateAccount(accounts.exists(accountNumber));
    }

    @Override
    public void withdraw(int amount, int accountNumber) throws NoSuchElementException, InsufficientResourcesException {
        accounts.withdraw(amount, accountNumber);
        database.updateAccount(accounts.exists(accountNumber));

    }

    @Override
    public double getBalance(int accountNumber) {
        return accounts.getBalance(accountNumber);
    }

    @Override
    public void update(Account account) {
        Account accountTemp = accounts.exists(account.getAccountNo());
        if (accountTemp != null)
            accountTemp.setBalance(account.getBalance());
        else
            accounts.addAcount(account);

        notifyObservers(account);
    }

    private void notifyObservers(Account account) {
        if (observers.get(account.getAccountNo()) != null) {
            observers.get(account.getAccountNo()).forEach(o -> {
                try {
                    o.update(account.getBalance());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            });
        }
    }


    @Override
    public void registerObserver(ClientObserver client, int accountNo) {
        System.out.println("starting registering observers");
        List<ClientObserver> list = observers.get(accountNo);
        if (list == null) {
            list = new LinkedList<>();
            list.add(client);
            observers.put(accountNo, list);
        } else {
            observers.get(accountNo).add(client);
        }
    }

    @Override
    public void deregisterObserver(ClientObserver client, int accountNo) {
        System.out.println("starting deregistering observers");
        List<ClientObserver> list = observers.get(accountNo);
        if (list.size() > 0) {
            observers.get(accountNo).remove(client);
        } else {
            observers.remove(accountNo);
        }
    }

    public static void main(String[] args) {
        Object impl = new BankServer();
        String address = "http://localhost:9001/Bank";
        Endpoint.publish(address, impl);
    }
}
