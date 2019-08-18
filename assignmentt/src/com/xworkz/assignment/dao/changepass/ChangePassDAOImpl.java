package com.xworkz.assignment.dao.changepass;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xworkz.assignment.entities.signup.SignUpEntity;
import com.xworkz.assignment.exceptions.DAOException;

@Repository
public class ChangePassDAOImpl implements ChangePassDAO {

	@Autowired
	SessionFactory sf;
	private static Logger logger=LoggerFactory.getLogger(ChangePassDAOImpl.class);

	public ChangePassDAOImpl() {
		logger.info("Created:" + this.getClass().getSimpleName());
	}

	@Override
	public String changePassDetails(String newPass, String email) throws DAOException {
		logger.info("invoked ChangePassDetails() from DAO...");
		Session ss = null;
		Transaction tx = null;
		int status = 1;
		try {
			ss = sf.openSession();
			tx = ss.beginTransaction();
//			String hql = "update SignUpEntity entity set  entity.pass='" + newPass + "'  where entity.email='" + email + "' ";
			String hql = "update SignUpEntity entity set entity.pass = :password , entity.status = :st where entity.email = :email";
//		    Query query = ss.createQuery(hql);
			Query query = ss.createQuery(hql);
			query.setParameter("password", newPass);
			query.setParameter("email", email);
			query.setParameter("st", status);
			
			SignUpEntity entity = new SignUpEntity();
			entity.setStatus(1);
			logger.info("updated entity" + entity);
			int i = (int) query.executeUpdate();
			tx.commit();
			logger.info("Updated pass row:" + i);
			logger.info("Sucessfull return entity from services()...");
			return "System.out.println(\"Sucessfull return entity from services()...\");";

		} catch (HibernateException e) {
			logger.info("Form DAO:" + e.getMessage());
			throw new DAOException(e.getMessage());
		} finally {

		}
	}

}
