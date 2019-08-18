package com.xworkz.assignment.entities.assignis;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xworkz.assignment.entities.createAssignment.CreateAssignmentEntity;

@Entity
@Table(name = "assignees_table")
public class AssignisEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -281779145386324052L;
	private static Logger logger=LoggerFactory.getLogger(AssignisEntity.class);
	
	@Id
    @GenericGenerator(name = "auto", strategy = "increment")
	@GeneratedValue(generator = "auto")
	@Column(name = "ASSIGNIS_ID")
	private int assgnisId;
	
	
	@ManyToOne
	@JoinColumn(name="PIN")
	private CreateAssignmentEntity assignmentEntity;
	
	@Column(name = "EMAIL")
	private String email;

	public AssignisEntity() {
		logger.info("Created:" + this.getClass().getSimpleName());
	}

	public int getAssgnisId() {
		return assgnisId;
	}

	public void setAssgnisId(int assgnisId) {
		this.assgnisId = assgnisId;
	}

	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public CreateAssignmentEntity getAssignmentEntity() {
		return assignmentEntity;
	}
	public void setAssignmentEntity(CreateAssignmentEntity assignmentEntity) {
		this.assignmentEntity = assignmentEntity;
	}

	@Override
	public String toString() {
		return "AssignisEntity [assgnisId=" + assgnisId + ", email=" + email
				+ "]";
	}
	
	
	
	

}
