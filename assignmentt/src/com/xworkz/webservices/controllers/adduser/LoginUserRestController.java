package com.xworkz.webservices.controllers.adduser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xworkz.assignment.entities.signup.SignUpEntity;
import com.xworkz.assignment.exceptions.ServiceException;
import com.xworkz.assignment.services.signInuser.SignInUserService;

@RestController
public class LoginUserRestController {

	@Autowired
	private SignInUserService service;

	private static Logger logger = LoggerFactory.getLogger(LoginUserRestController.class);

	public LoginUserRestController() {

		logger.info("Created:" + this.getClass().getSimpleName());
	}
	
	
	
	@RequestMapping(value="v1/signUpUser",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> signUpUser(@RequestBody SignUpEntity dto) throws ServiceException
	{
	   System.out.println("invoked signUpRestController..."+dto);
	  
	   String email=dto.getEmail();
	   String pass=dto.getPass();
	   
	   SignUpEntity entity=service.signInDetails(email, pass);
	   
	   System.out.println("Return Entity is:"+entity);
	   
	   
	   return new  ResponseEntity<>("Login Successfully by: "+entity.getFname(),HttpStatus.OK);
		
	}
	
}
