package com.xworkz.assignment.dao.assignis;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xworkz.assignment.entities.assignis.AssignisEntity;
import com.xworkz.assignment.entities.createAssignment.CreateAssignmentEntity;
import com.xworkz.assignment.exceptions.DAOException;

@Repository
public class AssignisDAOImp implements AssignisDAO {

	@Autowired
	private SessionFactory sf;
    private static Logger logger=LoggerFactory.getLogger(AssignisDAOImp.class);
	public AssignisDAOImp() {
		logger.info("Created:" + this.getClass().getSimpleName());
	}

	@Override
	public CreateAssignmentEntity assigniEmail(Long pid, String[] emails) throws DAOException {
		logger.info("Calling assignEmail From DAO:" + pid);
		Session ss = null;
		Transaction tx = null;
		try {
			ss = sf.openSession();
			String hql = "From CreateAssignmentEntity entity where entity.pin='" + pid + "'";
			Query query = ss.createQuery(hql);
			CreateAssignmentEntity pinEntity = (CreateAssignmentEntity) query.uniqueResult();
			if (pinEntity != null) {
				logger.info("Entity Availabe is:" + pinEntity);
				// going to save data of Assignee
				// AssignisEntity assignisEntity=new AssignisEntity();
				CreateAssignmentEntity createAssignmentEntity = new CreateAssignmentEntity();
				createAssignmentEntity.setPin(pinEntity.getPin());
				tx = ss.beginTransaction();
				for (int i = 0; i < emails.length; i++) {
					AssignisEntity assignisEntity = new AssignisEntity();
					assignisEntity.setAssignmentEntity(createAssignmentEntity);
					assignisEntity.setEmail(emails[i]);

					logger.info("AssigmentEntity Email Data:" + assignisEntity.getEmail());
					logger.info("AssigmentEntity PIN Data:" + assignisEntity.getAssignmentEntity().getPin());

					ss.save(assignisEntity);
					logger.info("Data Save in AssigneeDAO:" + emails[i] + " " + "And pin:" + pid);

				}
				tx.commit();

				return pinEntity;
			} else {
				logger.info("Entity Not available is:" + pinEntity);
				return pinEntity;
			}
		} catch (HibernateException e) {
			tx.rollback();
			logger.info("Exception From DAO:" + e);
			throw new DAOException(e.getMessage());
		} finally {
			ss.close();
		}

	}

}
