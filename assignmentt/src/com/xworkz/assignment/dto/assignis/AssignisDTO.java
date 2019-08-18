package com.xworkz.assignment.dto.assignis;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xworkz.assignment.dto.addtopic.AddTopicDTO;


public class AssignisDTO implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7852466871212663802L;
	private static Logger logger=LoggerFactory.getLogger(AssignisDTO.class);
	private int pin;
	private String email;
	
	public AssignisDTO() {
		logger.info("Created:"+this.getClass().getSimpleName());
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "AssignisDTO [pin=" + pin + ", email=" + email + "]";
	}
	
	

}
