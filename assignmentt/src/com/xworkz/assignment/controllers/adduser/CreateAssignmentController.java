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

import com.xworkz.assignment.dto.createAssignment.CreateAssignmentDTO;
import com.xworkz.assignment.enumutils.EnumUtils;
import com.xworkz.assignment.exceptions.ControllerException;
import com.xworkz.assignment.exceptions.ServiceException;
import com.xworkz.assignment.services.createassign.CreateAssignmentService;
import com.xworkz.assignment.sms.SendingSMS;

@Controller
public class CreateAssignmentController {

	@Autowired
	private CreateAssignmentService service;
	@Autowired
	private SendingSMS sendingSMS;

	private static Logger logger = LoggerFactory.getLogger(CreateAssignmentController.class);

	public CreateAssignmentController() {

		logger.info("Created:" + this.getClass().getSimpleName());

	}

	@RequestMapping(value = "/createAssignment", method = RequestMethod.POST)
	public String createAssignment(CreateAssignmentDTO dto, Model model, HttpServletRequest request)
			throws ControllerException {
		HttpSession oldSession = request.getSession(false);

		logger.info("User:" + oldSession.getAttribute("userEntity"));

		if (oldSession.getAttribute("userEntity") != null) {

			logger.info("Create Assignment Method Calling:" + dto);
			try {
				Long pin = service.createAssignment(dto, null);

				sendingSMS.sending(pin);

				model.addAttribute("success", "Sucessfully Assignment Created:" + pin);

			} catch (ServiceException e) {
				logger.info("Exception From Controller:" + e.getMessage());
				throw new ControllerException(e.getMessage());
			}

		} else {
			logger.info("Session TimeOut:SignIn Again...");
			model.addAttribute("SessionMsg", "SignIn First!!!");
			return EnumUtils.SignIn.toString();
		}
		return EnumUtils.CreateAssignment.toString();
	}

}
