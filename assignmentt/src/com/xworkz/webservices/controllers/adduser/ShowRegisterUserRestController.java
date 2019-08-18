package com.xworkz.webservices.controllers.adduser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xworkz.assignment.dao.adduser.AddUserDetailsDAO;
import com.xworkz.assignment.entities.signup.SignUpEntity;
import com.xworkz.assignment.exceptions.ControllerException;

@RestController
public class ShowRegisterUserRestController {

    @Autowired
    private AddUserDetailsDAO dao;
	
	public ShowRegisterUserRestController() {
		System.out.println("Created:"+this.getClass().getSimpleName());
	}
	
	
	@RequestMapping(value = "/getusers", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addUser() throws ControllerException, Exception {
		System.out.println("Calling getusersRestController() from RestController...:");
		
		List<SignUpEntity> entitie=dao.getUserDetailsSave();
		
		System.out.println("Data send:"+entitie );

      	return new  ResponseEntity<>("Data Sucessfully comming",HttpStatus.OK);

		
	}
	
	
}
