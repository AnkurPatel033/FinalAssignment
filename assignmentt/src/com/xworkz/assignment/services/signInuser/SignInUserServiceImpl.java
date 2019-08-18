package com.xworkz.assignment.services.signInuser;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.assignment.dao.signInuser.SignInUserDetailsDAO;
import com.xworkz.assignment.entities.signup.SignUpEntity;
import com.xworkz.assignment.exceptions.DAOException;
import com.xworkz.assignment.exceptions.ServiceException;
import com.xworkz.assignment.utils.adduser.ValidData;

@Service
public class SignInUserServiceImpl implements SignInUserService {

	@Autowired
	private ValidData data;
	@Autowired
	private SignInUserDetailsDAO dao;
	
	private static Logger logger=LoggerFactory.getLogger(SignInUserServiceImpl.class);
	
	public SignUpEntity signInDetails(String email, String pass) throws ServiceException  {
		logger.info("Calling signInUserDetails from Service()...");
		boolean check = false;
		check = valid(email, pass);
		// String encryptpass
		SignUpEntity entity;
		/* try { */
		if (check) {
			try {
				logger.info("Eneterd Pass is:" + pass);

				// decrept pass
				String encpass = Base64.getEncoder().encodeToString(pass.getBytes("utf-8"));
				logger.info("encrpted pass in DB:" + encpass);
				
				entity = dao.signInUserDetailsCheck(email, encpass);
				if (entity!=null)
				{
					logger.info("Going to DAO:and return entity is:" + entity);
				return entity;
				}
			} catch (DAOException | UnsupportedEncodingException e) {
				logger.info(e.getMessage());
				throw new ServiceException(e.getMessage());
			}
		}
     return null;
	}

	private boolean valid(String email, String pass) {
		logger.info("Cheking for login validation...");
		if (email != null && pass != null) {

			boolean vEmail = data.isValidEmail(email);
			boolean vPass = data.isValidPass(pass);

			if (vEmail && vPass) {
				logger.info("Valid Credential..going to SignIn Applications..");
				return true;
			}

		} else {
			logger.info("Invalid Credential Found...");
			return false;
		}
		return false;
	}

}
