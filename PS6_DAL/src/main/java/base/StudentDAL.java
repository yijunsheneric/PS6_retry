package base;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import domain.StudentDomainModel;
import util.HibernateUtil;

public class StudentDAL  {


	/**
	 * addStudent - Method adds a student to the database
	 * @param stu
	 * @return
	 */
	public static StudentDomainModel addStudent(StudentDomainModel stu) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		int employeeID = 0;
		try {
			tx = session.beginTransaction();
			session.save(stu);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return stu;
	}
	
	
	public static ArrayList<StudentDomainModel> getStudents() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		StudentDomainModel stuGet = null;		
		ArrayList<StudentDomainModel> stus = new ArrayList<StudentDomainModel>();
		
		try {
			tx = session.beginTransaction();	
			
			List students = session.createQuery("FROM StudentDomainModel").list();
			for (Iterator iterator = students.iterator(); iterator.hasNext();) {
				StudentDomainModel stu = (StudentDomainModel) iterator.next();
				stus.add(stu);

			}
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return stus;
	}		
	
	
	public static StudentDomainModel getStudent(UUID stuID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		StudentDomainModel stuGet = null;		
		
		try {
			tx = session.beginTransaction();	
									
			Query query = session.createQuery("from StudentDomainModel where studentId = :id ");
			query.setParameter("id", stuID.toString());
			
			List<?> list = query.list();
			stuGet = (StudentDomainModel)list.get(0);
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return stuGet;
	}		
	
	public static void deleteStudent(UUID stuID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		StudentDomainModel stuGet = null;		
		
		try {
			tx = session.beginTransaction();	
									
			StudentDomainModel stu = (StudentDomainModel) session.get(StudentDomainModel.class, stuID);
			session.delete(stu);
		
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}	
	

	public static StudentDomainModel updateStudent(StudentDomainModel stu) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		StudentDomainModel stuGet = null;		
		
		try {
			tx = session.beginTransaction();	
									
			session.update(stu);
	
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return stu;
	}		
	
	
	
	
	
	
}
