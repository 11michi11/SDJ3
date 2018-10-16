package database;

import model.Account;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.LinkedList;
import java.util.List;

public class Hibernate implements DatabaseProxy {

	private final SessionFactory ourSessionFactory;

	public Hibernate() {
		try {
			Configuration configuration = new Configuration();
			configuration.configure();
			ourSessionFactory = configuration.buildSessionFactory();
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	public Session getSession() throws HibernateException {
		return ourSessionFactory.openSession();
	}

	@Override
	public void saveState(List<Account> accountList) {
		accountList.forEach(this::updateObject);
	}

	@Override
	public List<Account> restoreState() {
		Transaction tx = null;
		try (Session session = ourSessionFactory.openSession()) {
			tx = session.beginTransaction();
			List<Account> films = session.createQuery("FROM Account ").list();
			tx.commit();
			return films;
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		}
		return new LinkedList<>();
	}

	@Override
	public void updateAccount(Account account)  {
		updateObject(account);
	}

	@Override
	public void addAccount(Account account) {
		addObject(account);
	}

	private void addObject(Object obj){
		Transaction tx = null;
		try (Session session = ourSessionFactory.openSession()) {
			tx = session.beginTransaction();
			session.save(obj);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		}
	}

	private void updateObject(Object obj){
		Transaction tx = null;
		try (Session session = ourSessionFactory.openSession()) {
			tx = session.beginTransaction();
			session.update(obj);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		}
	}
}
