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

import com.xworkz.assignment.entities.createAssignment.CreateAssignmentEntity;
import com.xworkz.assignment.enumutils.EnumUtils;
import com.xworkz.assignment.exceptions.ControllerException;
import com.xworkz.assignment.exceptions.ServiceException;
import com.xworkz.assignment.services.searchassign.SearchAssigmentService;

@Controller
public class SearchAssignmentController {

	@Autowired
	private SearchAssigmentService service;

	private static Logger logger = LoggerFactory.getLogger(SearchAssignmentController.class);

	public SearchAssignmentController() {
		logger.info("Created:" + this.getClass().getSimpleName());
	}
	

	@RequestMapping(value = "/searchAssign", method = RequestMethod.POST)
	public String serachAssign(@RequestParam String pid, HttpServletRequest request, Model model)
			throws ControllerException, Exception {

		HttpSession oldSession = request.getSession(false);

		logger.info("User:" + oldSession.getAttribute("userEntity"));

		if (oldSession.getAttribute("userEntity") != null) {
			logger.info("invoking seachAssin() from controller....");
			logger.info("Pin is:" + pid);
			try {
				CreateAssignmentEntity entity;
				entity = service.searchAssign(pid);

				if (entity != null) {
					logger.info("Entity From Service in Controller:" + entity);
					model.addAttribute("Data", entity);
					return EnumUtils.Search.toString();
				} else {
					model.addAttribute("message", "Record Not Found...");
					return EnumUtils.Search.toString();
				}
			} catch (ServiceException e) {
				logger.info("from Controller Error:" + e);
				model.addAttribute("message", "Record Not Found...");
				return EnumUtils.Search.toString();
			}
		} else {
			logger.info("Session TimeOut:SignIn Again...");
			model.addAttribute("SessionMsg", "SignIn First!!!");
			return EnumUtils.SignIn.toString();
		}

	}

}
