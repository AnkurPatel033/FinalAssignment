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
@Table(name="topic_table")
public class LoadTopicEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 895111982312484830L;
	private static Logger logger=LoggerFactory.getLogger(LoadTopicEntity.class);
	
	@Id
	@GenericGenerator(name="auto",strategy="increment")
	@GeneratedValue(generator="auto")
	@Column(name="TOPIC_ID")
	private int id;
	@Column(name="TOPIC_NAME")
	private String topicName;
	@Column(name="COURSE_ID")
	private Integer cId;
	
	
	public LoadTopicEntity() {
		logger.info("Created:"+this.getClass().getSimpleName());
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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
		return "LoadTopicEntity [id=" + id + ", topicName=" + topicName + ", cId=" + cId + "]";
	}
	
	
	
	
}
