package com.xworkz.assignment.dto.createAssignment;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoadCourseDTO implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8649678326408799854L;
	private static Logger logger=LoggerFactory.getLogger(LoadCourseDTO.class);
	private int courseId;
	private String courseName;
	
	public LoadCourseDTO() {
		logger.info("Created:"+this.getClass().getSimpleName());
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	@Override
	public String toString() {
		return "LoadCourseDTO [courseId=" + courseId + ", courseName=" + courseName + "]";
	}
	
	
	

}
