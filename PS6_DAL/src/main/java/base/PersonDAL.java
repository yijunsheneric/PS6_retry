package base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.PersonDomainModel;
import util.HibernateUtil;

public class PersonDAL {

	/**
	 * addPerson - Method adds a person to the database
	 * @param per
	 * @return
	 */
	public static PersonDomainModel addPerson(PersonDomainModel per) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		int employeeID = 0;
		try {
			tx = session.beginTransaction();
			session.save(per);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return per;
	}
	
	
	public static ArrayList<PersonDomainModel> getPersons() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		PersonDomainModel perGet = null;		
		ArrayList<PersonDomainModel> pers = new ArrayList<PersonDomainModel>();
		
		try {
			tx = session.beginTransaction();	
			
			List persons = session.createQuery("FROM PersonDomainModel").list();
			for (Iterator iterator = persons.iterator(); iterator.hasNext();) {
				PersonDomainModel per = (PersonDomainModel) iterator.next();
				pers.add(per);

			}
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return pers;
	}		
	
	
	public static PersonDomainModel getPerson(UUID perID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		PersonDomainModel perGet = null;		
		
		try {
			tx = session.beginTransaction();	
									
			Query query = session.createQuery("from PersonDomainModel where personId = :id ");
			query.setParameter("id", perID.toString());
			
			List<?> list = query.list();
			perGet = (PersonDomainModel)list.get(0);
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return perGet;
	}		
	
	public static void deletePerson(UUID perID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		PersonDomainModel perGet = null;		
		
		try {
			tx = session.beginTransaction();	
									
			PersonDomainModel per = (PersonDomainModel) session.get(PersonDomainModel.class, perID);
			session.delete(per);
		
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}	
	

	public static PersonDomainModel updatePerson(PersonDomainModel per) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		PersonDomainModel perGet = null;		
		
		try {
			tx = session.beginTransaction();	
									
			session.update(per);
	
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return per;
	}		

}
