package com.xworkz.assignment.controllers.adduser;

import java.util.List;

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

import com.xworkz.assignment.entities.assignupload.AssignUploadEntity;
import com.xworkz.assignment.enumutils.EnumUtils;
import com.xworkz.assignment.exceptions.ServiceException;
import com.xworkz.assignment.services.uploadassignmentdata.UploadAssignmentService;

@Controller
public class UploadedAssignmentController {

	@Autowired
	private UploadAssignmentService service;
	
	private static Logger logger=LoggerFactory.getLogger(UploadedAssignmentController.class);
	
	public UploadedAssignmentController() {
		logger.info("Created:"+this.getClass().getSimpleName());
	}
	
	@RequestMapping(value="/uploadedAssign",method=RequestMethod.POST)
	public String saveUploadedAssigment(@RequestParam String pid, HttpServletRequest request, Model model)
	{
		HttpSession oldSession = request.getSession(false);

		logger.info("User:" + oldSession.getAttribute("userEntity"));

		if (oldSession.getAttribute("userEntity") != null) {
			logger.info("invoking seachAssin() from controller....");
			logger.info("Pin is:" + pid);
			try {
				 List<AssignUploadEntity> entity;
				entity = service.searchuploadedAssignment(pid);

				if (entity.size()>0) {
					logger.info("Entity From Service in Controller:" + entity);
					logger.info("Data is There..");
					model.addAttribute("Data", entity);
				
					return EnumUtils.UploadAssignment.toString();
				} else {
					logger.info("Data is Not There..");
					model.addAttribute("message","Still Not uploaded data...");
					return EnumUtils.UploadAssignment.toString();
				}
			} catch (ServiceException e) {
				logger.info("from Controller Error:" + e);
				model.addAttribute("message","Data not available...");
				return EnumUtils.UploadAssignment.toString();
			}
		} else {
			logger.info("Session TimeOut:SignIn Again...");
			model.addAttribute("SessionMsg", "SignIn First!!!");
			return EnumUtils.SignIn.toString();
		}
		
		
		
	}
	
	
}
