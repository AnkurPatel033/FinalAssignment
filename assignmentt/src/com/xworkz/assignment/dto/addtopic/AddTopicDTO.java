package com.xworkz.assignment.dto.addtopic;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xworkz.assignment.dto.addcourse.AddCourseDTO;

public class AddTopicDTO implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = -7775661729599642754L;
	  private static Logger logger=LoggerFactory.getLogger(AddTopicDTO.class);
	private String topicName;
	private Integer cId;

	public AddTopicDTO() {
		logger.info("Created:" + this.getClass().getSimpleName());
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public Integer getcId() {
		return cId;
	}

	public void setcId(Integer cId) {
		this.cId = cId;
	}

	@Override
	public String toString() {
		return "AddTopicDTO [topicName=" + topicName + ", cId=" + cId + "]";
	}

	
}
