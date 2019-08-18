package com.xworkz.assignment.services.createassign;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.assignment.dao.createassign.CreateAssignmentDAO;
import com.xworkz.assignment.dto.createAssignment.CreateAssignmentDTO;
import com.xworkz.assignment.email.MailSender;
import com.xworkz.assignment.entities.createAssignment.CreateAssignmentEntity;
import com.xworkz.assignment.exceptions.DAOException;
import com.xworkz.assignment.exceptions.ServiceException;
import com.xworkz.assignment.sms.SendingSMS;
import com.xworkz.assignment.utils.adduser.RandomGenerator;

@Service
public class CreateAssignmentServiceImp implements CreateAssignmentService {

	@Autowired
	private CreateAssignmentDAO dao;
	@Autowired
	private RandomGenerator random;
	@Autowired
	private MailSender mailSender;
	@Autowired
	private SendingSMS sendSMS;
	
	private static Logger logger=LoggerFactory.getLogger(CreateAssignmentServiceImp.class);

	public CreateAssignmentServiceImp() {
		logger.info("Created:" + this.getClass().getSimpleName());
	}

	@Override
	public Long createAssignment(CreateAssignmentDTO dto,HttpServletRequest request) throws ServiceException {

		logger.info("Create AssignmentServiceImp() Calling");

		CreateAssignmentEntity entity1 = new CreateAssignmentEntity();
		entity1.setCourse(dto.getCourse());
		entity1.setTopic(dto.getTopic());
		entity1.setDesc(dto.getDesc());
		entity1.setDate(dto.getDate());
		entity1.setUploadType(dto.getUploadType());

	    //Generate random Pin no. and save in entity
		Long pin = random.genearteAssignmentNumber(entity1);
		entity1.setPin(pin);
		//sending sms on Mobile
		
		sendSMS.sending(pin);
		
		
		logger.info("Entity value:"+entity1);
		
		try {

			dao.createAssignment(entity1);
			
			} catch (DAOException e) {
			logger.info("Exception From Service:" + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
		return pin;
	}

	@Override
	public List<Object[]> courseLoad() throws ServiceException {
		
		logger.info("courseLoad() calling from CreateAssignmentService..");
		try {
			List<Object[]> courses=dao.courseLoad();
			return courses;
		} catch (DAOException e) {
			logger.info("Exception From Service:" + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
		
	}
	@Override
	public List<Object> topicLoad(int id) throws ServiceException {
		
		logger.info("topicLoad() calling from CreateAssignmentService..:"+id);
		try {
			List<Object> topics=dao.topicLoad(id);
			return topics;
		} catch (DAOException e) {
			logger.info("Exception From Service:" + e.getMessage());
			throw new ServiceException(e.getMessage());
		}	
	}

}
