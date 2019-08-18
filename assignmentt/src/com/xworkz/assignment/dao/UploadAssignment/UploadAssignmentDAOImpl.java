package com.xworkz.assignment.dao.UploadAssignment;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xworkz.assignment.entities.assignupload.AssignUploadEntity;
import com.xworkz.assignment.exceptions.DAOException;

@Repository
public class UploadAssignmentDAOImpl implements UploadAssignmentDAO {

	@Autowired
	private SessionFactory sf;
    private static Logger logger=LoggerFactory.getLogger(UploadAssignmentDAOImpl.class);
	public UploadAssignmentDAOImpl() {
		logger.info("Created:" + this.getClass().getSimpleName());
	}

	@Override
	public List<AssignUploadEntity> searchUploadAssignment(String pid) throws DAOException {
		logger.info("Calling seachAssign() from DAO...");
		Session ss = null;
		try {
			ss = sf.openSession();
			String hql = "From AssignUploadEntity entity where entity.pin = '" + pid + "' ";
			Query query = ss.createQuery(hql);
			List<AssignUploadEntity> entity = (List<AssignUploadEntity>) query.list();
			logger.info("Entity return From DB in DAO is:" + entity);
			return entity;
		} catch (HibernateException e) {
			logger.info("Exception From DAO:" + e.getMessage());
			throw new DAOException(e.getMessage());
		} finally {
			ss.close();
		}
	}

}
