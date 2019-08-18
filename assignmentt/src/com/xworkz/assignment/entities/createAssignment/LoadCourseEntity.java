package com.xworkz.assignment.entities.createAssignment;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Entity
@Table(name="course_table")
public class LoadCourseEntity implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8649678326408799854L;
	private static Logger logger=LoggerFactory.getLogger(LoadCourseEntity.class);
	@Id
	@GenericGenerator(name="auto",strategy="increment")
	@GeneratedValue(generator="auto")
	@Column(name="COURSE_ID")
	private int courseId;
	@Column(name="COURSE_NAME")
	private String courseName;
	
	public LoadCourseEntity() {
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
		return "LoadCourseEntity [courseId=" + courseId + ", courseName=" + courseName + "]";
	}
	
	
	

}
