package com.xworkz.assignment.controllers.adduser;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.xworkz.assignment.dto.assignupload.AssignUploadDTO;
import com.xworkz.assignment.enumutils.EnumUtils;
import com.xworkz.assignment.exceptions.ServiceException;
import com.xworkz.assignment.services.assignupload.AssignUploadService;

@Controller
@RequestMapping("/")
public class AssignUploadController {

	@Autowired
	private AssignUploadService service;

	private static Logger logger=LoggerFactory.getLogger(AssignisController.class);
	
	public AssignUploadController() {

		logger.info("Created:" + this.getClass().getSimpleName());
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "/assignmentUpload", method = RequestMethod.POST)
	public String saveAssignment(AssignUploadDTO dto, Model model, HttpServletRequest request) {
		logger.info("Calling save() from AssignUploadController class..");

		String ip = request.getRemoteAddr();
		String hostName = "";
		if (ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
			InetAddress inetAddress = null;
			try {
				inetAddress = InetAddress.getLocalHost();
				hostName = inetAddress.getHostName();
				logger.info("IP Address:- " + inetAddress.getHostAddress());
				logger.info("Host Name:- " + hostName);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			String ipAddress = inetAddress.getHostAddress();
			ip = ipAddress;
		}


		// create newSession here...

		HttpSession session = request.getSession(true);// Creating a session
		session.setAttribute("StudentEntity", ip);
		logger.info("Session Entity is:" + ip);

		session.setMaxInactiveInterval(60 * 10);
		if (session != null) {
			MultipartFile file = dto.getFile();
			long fsize=file.getSize();
			logger.info("File size:" +fsize );
			
			String fileName="";
			try {
				   if (fsize!= 0) {
					   byte[] fileadd = file.getBytes();
					   String fileUrl = "D:/" + file.getOriginalFilename();
					   BufferedOutputStream bufferedInputStream = new BufferedOutputStream(
							new FileOutputStream(new File(fileUrl)));
					   bufferedInputStream.write(fileadd);
					    fileName = new SimpleDateFormat("yyyy_MM_dd_HH_mm'.zip'").format(new Date());
					    logger.info("File Name:" + fileName);
					    logger.info("File Address:" + fileUrl);
				       }

				try {

					service.saveAssignUpload(dto, fileName, ip, hostName);
				} catch (ServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				logger.info("DTO is:" + dto);
				
			} catch (IOException e) {

				e.printStackTrace();
			}
			

			model.addAttribute("msg", "File uploaded successfully.");
		} else {
			session.invalidate();
			logger.info("After Session TimeOut:" + request.getAttribute("StudentEntity"));
		}
		/* ${files} uploaded successfully. */

		return EnumUtils.StudentHomeFile.toString();
	}
}
