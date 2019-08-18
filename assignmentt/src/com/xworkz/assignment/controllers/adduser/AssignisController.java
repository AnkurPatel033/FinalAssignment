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
import com.xworkz.assignment.exceptions.ServiceException;
import com.xworkz.assignment.services.assignis.AssignisService;

@Controller
public class AssignisController {

	@Autowired
	private AssignisService service;

	private static Logger logger = LoggerFactory.getLogger(AssignisController.class);
	
	public AssignisController() {
		logger.info("Created:" + this.getClass().getSimpleName());
	}

	@RequestMapping(value = "/assignis", method = RequestMethod.POST)
	public String assignis(@RequestParam long pid, @RequestParam String email, HttpServletRequest request, Model model)
			throws Exception {

		HttpSession oldSession = request.getSession(false);

		System.out.println("User:" + oldSession.getAttribute("userEntity"));

		if (oldSession.getAttribute("userEntity") != null) {

			System.out.println("invoked assignis() from Controller...");
			System.out.println("Data from assigins:" + pid);
			String[] emails = email.split(",");
			for (int i = 0; i < emails.length; i++) {
				// going to check emailId validation
				logger.info(emails[i]);
			}

			try {

			String msg=	service.assignEmail(pid, emails);
			//	model.addAttribute("success", "Successfully Mail Send..");
				model.addAttribute("success", msg);
				return EnumUtils.Assignis.toString();
			} catch (ServiceException e) {
				System.out.println("from Controller Error:" + e);
				model.addAttribute("success", "Sending Fail...");
				return EnumUtils.Assignis.toString();
			}
		} else {
			logger.info("Session TimeOut:SignIn Again...");
			model.addAttribute("SessionMsg", "SignIn First!!!");
			return EnumUtils.SignIn.toString();
		}

	}
}
