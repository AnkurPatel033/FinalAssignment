package com.xworkz.assignment.utils.adduser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ValidData {

	private static Logger logger=LoggerFactory.getLogger(ValidData.class);
	@SuppressWarnings("unused")
	public boolean isValidFname(String fName) {
		if (!fName.isEmpty() || fName != null || (fName.length() >= 4 && fName.length() <= 50)) {
			logger.info("First Name is Valid");
			return true;
		} else
		{
			logger.info("First Name is Invalid");
			return false;
		}

	}

	@SuppressWarnings("unused")
	public boolean isValidLname(String lName) {
		if (!lName.isEmpty() || lName != null || (lName.length() >= 4 && lName.length() <= 50)) {
			logger.info("Last Name is Valid");
			return true;
		} else {
			logger.info("Last Name is Invalid");
			return false;
		}

	}

	@SuppressWarnings("unused")
	public boolean isValidEmail(String email) {
		if (!email.isEmpty() || email != null) {
			logger.info("Email is Valid");
			return true;
		} else {
			logger.info("Email is Invalid");
			return false;
		}
	}

	public boolean isValidCcode(int cCode) {

		if (cCode != 0) {
			logger.info("Country Code is Valid");
			return true;
		} else {
			logger.info("Country Code is Invalid");
			return false;
		}
	}

	public boolean isValidcNo(long cNo) {

		if (cNo != 0 || (cNo >=1  && cNo <= 10)) {
			logger.info("Contact No is Valid");
			return true;
		} else {
			logger.info("Contact No is Invalid");
			return false;
		}
	}
	@SuppressWarnings("unused")
	public boolean isValidPass(String pass) {

		if (!pass.isEmpty() || pass != null) {
			logger.info("Password is Valid");
			return true;
		} else {
			System.out.println("Password is Invalid");
			return false;
		}	}
	
	@SuppressWarnings("unused")
	public boolean isValidNewPass(String NewPass) {
		if (!NewPass.isEmpty() || NewPass != null || (NewPass.length() >= 4 && NewPass.length() <= 8)) {
			logger.info("Valid Password..");
			return true;
		} else
		{
			logger.info("Invalid Pass...try Different..");
			return false;
		}

	}

}
