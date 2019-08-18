package com.xworkz.assignment.dao.assignupload;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.xworkz.assignment.entities.assignupload.AssignUploadEntity;
import com.xworkz.assignment.exceptions.DAOException;

@Repository
public class AssignUploadDAOImpl implements AssignUploadDAO {

	@Autowired
	private SessionFactory sf;
	private static Logger logger=LoggerFactory.getLogger(AssignUploadDAOImpl.class);
	public AssignUploadDAOImpl() {
		logger.info("Created:"+this.getClass().getSimpleName());
	}
	
	@Override
	public void saveAssignUpload(AssignUploadEntity entity) throws DAOException {
		logger.info("invoked saveAssignmentUpload() from DAO.. ");
		logger.info("Data in DAO:"+entity);
		Session ss=null;
		Transaction tx=null;
		try {
			
			ss=sf.openSession();
			tx=ss.beginTransaction();
			ss.save(entity);
			tx.commit();
			
		} catch (HibernateException e) {
			logger.info(e.getMessage());
			throw new DAOException(e.getMessage());
		}
		
		
	}

}
