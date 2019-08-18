package com.xworkz.assignment.services.searchassign;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.assignment.dao.searchassign.SearchAssignmentDAO;
import com.xworkz.assignment.entities.createAssignment.CreateAssignmentEntity;
import com.xworkz.assignment.exceptions.DAOException;
import com.xworkz.assignment.exceptions.ServiceException;

@Service
public class SearchAssignmentServiceImp implements SearchAssigmentService {

	@Autowired
	private SearchAssignmentDAO dao;
	
	private static Logger logger=LoggerFactory.getLogger(SearchAssignmentServiceImp.class);

	public SearchAssignmentServiceImp() {
		logger.info("Created:" + this.getClass().getSimpleName());
	}

	@Override
	public CreateAssignmentEntity searchAssign(String pid) throws ServiceException {

		logger.info("invoked searchAssign() from Service...");
		CreateAssignmentEntity entity;

		try {
			entity = dao.searchAssign(pid);
			logger.info("Entity return From DAO in service is:"+entity);
			return entity;
		} catch (DAOException e) {
			logger.info("From Service Class:" + e.getMessage());
			throw new ServiceException(e.getMessage());
		}

	}

}
