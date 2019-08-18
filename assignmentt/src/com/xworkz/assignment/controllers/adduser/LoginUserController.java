package com.xworkz.assignment.controllers.adduser;

import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xworkz.assignment.entities.signup.SignUpEntity;
import com.xworkz.assignment.enumutils.EnumUtils;
import com.xworkz.assignment.exceptions.ControllerException;
import com.xworkz.assignment.exceptions.ServiceException;
import com.xworkz.assignment.services.signInuser.SignInUserService;

@Controller
@RequestMapping("/")
public class LoginUserController {

	@Autowired
	private SignInUserService service;

	private static Logger logger = LoggerFactory.getLogger(LoginUserController.class);

	public LoginUserController() {

		logger.info("Created:" + this.getClass().getSimpleName());
	}

	@RequestMapping(value = "/signIn", method = RequestMethod.POST)
	public String signInDetails(@RequestParam String email, @RequestParam String pwd, Model model,
			HttpServletRequest request, HttpServletResponse response) throws ControllerException {

		// System.out.println("Calling SignIn()..from LoginUSerController()...");
		logger.info("Calling SignIn()..from LoginUSerController()...");
		System.out.println("Email:" + email + "" + "and Password:" + pwd);
		logger.info("Email:" + email + "" + "and Password:" + pwd);
		SignUpEntity success=new SignUpEntity();
		try {
			System.out.println("Comming..........................");

			success = service.signInDetails(email, pwd);

			if (success != null) {

				// create newSession here...

				HttpSession session = request.getSession(true);// Creating a session
				session.setAttribute("userEntity", success);
				System.out.println("Return Session Entity is:" + success);

				logger.info("Set Session Attribute is:" + session.getId());
				// request.setAttribute("userName", success.getFname());// setting session
				// attribute
				// setting session to expire in 5 mins
				session.setMaxInactiveInterval(60 * 5);// 300seconde
				

				Cookie useremail = new Cookie("Email", success.getEmail());
				Cookie password = new Cookie("message", success.getPass());
				response.addCookie(useremail);
				response.addCookie(password);

				if (success.getStatus() == 1) {
					logger.info("SignIn Sucessfully.." + session.getAttribute(success.getFname()));
					model.addAttribute("email", email);
					return EnumUtils.Home.toString();
				} else if (success.getStatus() == 0) {// first time user
					logger.info("Change Your Password " + session.getAttribute(success.getFname()));
					model.addAttribute("email", email);
					return EnumUtils.ChangeSetting.toString();
				}
			} else {
				logger.info("Invalid Credential..try again...");
				model.addAttribute("message", "Invalid Credential..try again......");
				return EnumUtils.SignIn.toString();
			}
		} catch (ServiceException e) {
			logger.info(e.getMessage());
			throw new ControllerException(e.getMessage());
		}
		return EnumUtils.SignIn.toString();

	}

	@RequestMapping("/signOut")
	public String logOut(HttpServletRequest request) {
		logger.info("invoked SignOut method.....");

		HttpSession session = request.getSession(false);

		if (session != null) {
	         
		    
			session.invalidate();

			logger.info("UserName after Session TimeOut:" + request.getAttribute("userName"));
		}
		return EnumUtils.SignIn.toString();
	}

}
