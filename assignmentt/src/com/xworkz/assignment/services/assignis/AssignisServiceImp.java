package com.xworkz.assignment.services.assignis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.assignment.dao.assignis.AssignisDAO;
import com.xworkz.assignment.email.MailSender;
import com.xworkz.assignment.entities.createAssignment.CreateAssignmentEntity;
import com.xworkz.assignment.exceptions.DAOException;
import com.xworkz.assignment.exceptions.ServiceException;

@Service
public class AssignisServiceImp implements AssignisService {
	
	@Autowired
	private AssignisDAO dao;
	@Autowired
	private MailSender mainSender;
	
	private static Logger logger=LoggerFactory.getLogger(AssignisServiceImp.class);
	
	public AssignisServiceImp() {
		logger.info("Created:"+this.getClass().getSimpleName());
	}

	@Override
	public String  assignEmail(Long pidId, String[] email) throws ServiceException, Exception {
		logger.info("invoked assignEMail from Service..");
		try {			
			logger.info("Service:"+pidId);
			CreateAssignmentEntity entity=dao.assigniEmail(pidId,email);
			logger.info("Assigment pin:"+entity);
			if(entity!=null)
			{
				mainSender.mailAssigins(entity, email);	
				return "Successfullly Mail Sent...";
			}
			else
			{
				logger.info("Pin is Invalid...");
				return "Invalid Data entered...";
			}
			
		} catch (DAOException e) {
			logger.info("Exception From Service:" + e.getMessage());
			throw new ServiceException(e.getMessage());
		}	
		
	}
	
	

}
