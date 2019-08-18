package com.xworkz.assignment.dto.addcourse;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddCourseDTO implements Serializable{
		/**
	 * 
	 */
	private static final long serialVersionUID = -2128219455511497263L;
	    private static Logger logger=LoggerFactory.getLogger(AddCourseDTO.class);
		private String courseName;
         
         public AddCourseDTO() {
        	 logger.info("Created:"+this.getClass().getSimpleName());
		}
         
         public String getCourseName() {
			return courseName;
		}
         
         public void setCourseName(String courseName) {
			this.courseName = courseName;
		}

		@Override
		public String toString() {
			return "AddTopicDTO [courseName=" + courseName + "]";
		}
         
        
}
