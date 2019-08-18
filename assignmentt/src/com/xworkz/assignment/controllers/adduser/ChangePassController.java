package com.xworkz.assignment.controllers.adduser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xworkz.assignment.enumutils.EnumUtils;
import com.xworkz.assignment.exceptions.ControllerException;
import com.xworkz.assignment.exceptions.ServiceException;
import com.xworkz.assignment.services.changepass.ChangePassService;

@Controller
public class ChangePassController {

	@Autowired
	private ChangePassService service;

	private static Logger logger=LoggerFactory.getLogger(ChangePassController.class);
	
	public ChangePassController() {
		logger.info("Created:" + this.getClass().getSimpleName());
	}

	@RequestMapping(value = "/ChangeSetting", method = RequestMethod.POST)
	public String changePassDetails(@RequestParam String npass,@RequestParam String email,HttpServletRequest request, Model model)
			throws ControllerException {
		

		HttpSession oldSession = request.getSession(false);

		logger.info("User:" + oldSession.getAttribute("userEntity"));

		if (oldSession.getAttribute("userEntity") != null) {
		
		
			logger.info("invoked changePassDetails()...");
		model.addAttribute("email",email);//do comming from Session
        
        
		try {
			service.changePassDetails(npass,email);
			model.addAttribute("message", "Sucessfully Password Changed..");
			return EnumUtils.ChangeSetting.toString();
		
		} catch (ServiceException e) {
			logger.info("From Controller:" + e.getMessage());
			throw new ControllerException(e.getMessage());
		}
		}
		else
		{
			logger.info("Session TimeOut:SignIn Again...");
			model.addAttribute("SessionMsg", "SignIn First!!!");
			return EnumUtils.SignIn.toString();
		}

		
	}

}
