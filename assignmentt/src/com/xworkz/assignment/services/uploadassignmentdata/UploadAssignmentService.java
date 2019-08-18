package com.xworkz.assignment.services.uploadassignmentdata;

import java.util.List;

import com.xworkz.assignment.entities.assignupload.AssignUploadEntity;
import com.xworkz.assignment.exceptions.ServiceException;

public interface UploadAssignmentService {

	public  List<AssignUploadEntity> searchuploadedAssignment(String pid) throws ServiceException;
}
