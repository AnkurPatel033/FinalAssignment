package com.xworkz.assignment.dao.adduser;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xworkz.assignment.entities.assignupload.AssignUploadEntity;
import com.xworkz.assignment.entities.signup.SignUpEntity;
import com.xworkz.assignment.exceptions.DAOException;

@Repository
public class AddUserDetailsDAOImpl implements AddUserDetailsDAO {

	@Autowired
	private SessionFactory sf;
	private static Logger logger= LoggerFactory.getLogger(AddUserDetailsDAOImpl.class);
	
	public AddUserDetailsDAOImpl() {
		logger.info("Created:"+this.getClass().getSimpleName());
	}
	
	@Override
	public Integer addUserDetailsSave(SignUpEntity entity) throws DAOException {
		logger.info("Calling AddUserDetailsSave() from DAO..."+entity);
		Session ss=null;
		Transaction tx=null;
		try {			
			ss=sf.openSession();
			tx=ss.beginTransaction();
			logger.info("Going to save entity:"+entity);
			Integer i=(Integer) ss.save(entity);
			logger.info("save entity:"+entity);
			tx.commit();
			return i;
		}catch (HibernateException e) {
			tx.rollback();
			logger.info("Exception From DAO:"+e);
			throw new DAOException(e.getMessage());
		}finally {
			ss.close();
		}
	}
	@Override
	public List<SignUpEntity> getUserDetailsSave() throws DAOException {
		logger.info("Calling getUserDetails() from DAO...");
		Session ss=null;
		Transaction tx=null;
		Session ss1 = null;
		try {
			ss1 = sf.openSession();
			String hql = "From SignUpEntity entity";
			Query query = ss1.createQuery(hql);
			List<SignUpEntity> entities = (List<SignUpEntity>) query.list();
			System.out.println("Data:"+entities);
			return entities;
		} catch (HibernateException e) {
			logger.info("Exception From DAO:" + e.getMessage());
			throw new DAOException(e.getMessage());
		} finally {
			ss1.close();
		}
	}

}
