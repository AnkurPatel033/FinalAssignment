package com.xworkz.assignment.services.changepass;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.assignment.dao.changepass.ChangePassDAO;
import com.xworkz.assignment.email.MailSender;
import com.xworkz.assignment.entities.signup.SignUpEntity;
import com.xworkz.assignment.exceptions.DAOException;
import com.xworkz.assignment.exceptions.ServiceException;
import com.xworkz.assignment.utils.adduser.ValidData;

@Service
public class ChangePassServiceImpl implements ChangePassService {

	@Autowired
	private ChangePassDAO dao;
	@Autowired
	private ValidData data;
	@Autowired
	private MailSender mailSender;

	private static Logger logger = LoggerFactory.getLogger(ChangePassServiceImpl.class);

	public ChangePassServiceImpl() {
		logger.info("Created:" + this.getClass().getSimpleName());
	}

	@Override
	public String changePassDetails(String newpass, String email) throws ServiceException {
		logger.info("invkode changePassDetails() From Service...");
		boolean check = false;
		check = valid(newpass);
		if (check)
			try {
				logger.info("Entered New Password is:" + newpass);
				// convert in encryption

				String encpass = Base64.getEncoder().encodeToString(newpass.getBytes("utf-8"));
				logger.info("encrpted pass in DB:" + encpass);
				SignUpEntity entity = new SignUpEntity();
				entity.setStatus(1);
				logger.info("change pass mail send..preparation...start..");
				logger.info("Email for chnage pass:" + entity);
				mailSender.changePassmailSending(email, newpass, null); // send mail for change pass
				logger.info("change pass mail send..complete..");
				dao.changePassDetails(encpass, email);
			} catch (DAOException | UnsupportedEncodingException e) {
				logger.info("From Service Class:" + e.getMessage());
				throw new ServiceException(e.getMessage());
			}
		return "Succesfully from Services..";
	}

	private boolean valid(String newPass) {
		if (newPass != null) {
			@SuppressWarnings("unused")
			boolean vNewPass = data.isValidNewPass(newPass);
			logger.info("Valid Data..going to save in DB..");
			return true;

		} else {
			logger.info("Invalid Password Created......");
			return false;
		}

	}

}
