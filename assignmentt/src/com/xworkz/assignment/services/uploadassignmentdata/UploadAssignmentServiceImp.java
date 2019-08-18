package com.xworkz.assignment.services.uploadassignmentdata;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.assignment.dao.UploadAssignment.UploadAssignmentDAO;
import com.xworkz.assignment.entities.assignupload.AssignUploadEntity;
import com.xworkz.assignment.exceptions.DAOException;
import com.xworkz.assignment.exceptions.ServiceException;

@Service
public class UploadAssignmentServiceImp implements UploadAssignmentService {

	@Autowired
	private UploadAssignmentDAO dao;
	private static Logger logger=LoggerFactory.getLogger(UploadAssignmentServiceImp.class);
	
	public UploadAssignmentServiceImp() {
		logger.info("Created:" + this.getClass().getSimpleName());
	}

	@Override
	public  List<AssignUploadEntity> searchuploadedAssignment(String pid) throws ServiceException {

		logger.info("invoked saveuploadAsssignemnt() from service...");
		 List<AssignUploadEntity> entity;
		try {
			entity = dao.searchUploadAssignment(pid);
			logger.info("Entity return From DAO in service is:" + entity);
			return entity;
		} catch (DAOException e) {
			logger.info("From Service Class:" + e.getMessage());
			throw new ServiceException(e.getMessage());
		}

	}

}
