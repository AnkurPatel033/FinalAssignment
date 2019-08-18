package com.xworkz.assignment.dao.UploadAssignment;

import java.util.List;

import com.xworkz.assignment.entities.assignupload.AssignUploadEntity;
import com.xworkz.assignment.exceptions.DAOException;

public interface UploadAssignmentDAO {

	public List<AssignUploadEntity> searchUploadAssignment(String pid) throws DAOException;
}
