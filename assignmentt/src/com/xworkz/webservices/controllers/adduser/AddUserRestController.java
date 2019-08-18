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

import com.xworkz.assignment.dto.signup.SignUpDTO;
import com.xworkz.assignment.exceptions.ControllerException;
import com.xworkz.assignment.services.adduser.AddUserDetailsService;

@RestController
public class AddUserRestController {

	@Autowired
	private AddUserDetailsService service;
	
	private static Logger logger = LoggerFactory.getLogger(AddUserRestController.class);
	
	public AddUserRestController() {
		logger.info("Created:" + this.getClass().getSimpleName());
	}
	//posting data
	@RequestMapping(value = "v1/addusers", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addUser(@RequestBody SignUpDTO dto) throws ControllerException, Exception {
		System.out.println("Calling TaddusersRestController() from RestController...:");
		
		System.out.println("Data send:"+dto );
		
        Integer i=service.addUserDetailsSave(dto);
		
      	return new  ResponseEntity<>("Inserted Sucessfully data at Index:"+i,HttpStatus.OK);

		
	}
	
	
	
	
	

}
