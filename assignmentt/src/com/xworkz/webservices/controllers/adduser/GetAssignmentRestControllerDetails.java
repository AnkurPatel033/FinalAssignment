package com.xworkz.webservices.controllers.adduser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xworkz.assignment.dao.createassign.CreateAssignmentDAO;
import com.xworkz.assignment.dao.searchassign.SearchAssignmentDAO;
import com.xworkz.assignment.entities.createAssignment.CreateAssignmentEntity;
import com.xworkz.assignment.exceptions.DAOException;
import com.xworkz.assignment.exceptions.ServiceException;
import com.xworkz.assignment.services.searchassign.SearchAssignmentServiceImp;

@RestController
public class GetAssignmentRestControllerDetails {

	@Autowired
	private SearchAssignmentServiceImp service;
	
	public GetAssignmentRestControllerDetails() {
		System.out.println("Created:"+this.getClass().getName());
	}
	
	
	@RequestMapping(value="v1/getAssignment/{pin}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CreateAssignmentEntity> getAssigment(@PathVariable String pin) throws DAOException, ServiceException
	{
		System.out.println("Invoked GetAssignment Rest Controller..."+pin);
	  CreateAssignmentEntity entity=   service.searchAssign(pin);
	System.out.println("Data:"+entity);
		return  new  ResponseEntity<CreateAssignmentEntity>(entity, HttpStatus.OK);
	}
	
	
	
}
