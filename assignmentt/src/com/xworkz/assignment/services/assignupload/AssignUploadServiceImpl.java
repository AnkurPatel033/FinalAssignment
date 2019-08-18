package com.xworkz.assignment.services.assignupload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.assignment.dao.assignupload.AssignUploadDAO;
import com.xworkz.assignment.dto.assignupload.AssignUploadDTO;
import com.xworkz.assignment.entities.assignupload.AssignUploadEntity;
import com.xworkz.assignment.exceptions.DAOException;
import com.xworkz.assignment.exceptions.ServiceException;

@Service
public class AssignUploadServiceImpl implements AssignUploadService {

	@Autowired
	private AssignUploadDAO dao;
	
	private static Logger logger=LoggerFactory.getLogger(AssignUploadServiceImpl.class);

	public AssignUploadServiceImpl() {
		logger.info("Created:" + this.getClass().getSimpleName());
	}

	@Override
	public void saveAssignUpload(AssignUploadDTO dto, String path,String ip,String hostName) throws ServiceException {

		logger.info("invoked saveAssignUpload() from service..");

		logger.info("Data in Service is:" + dto);

		AssignUploadEntity entity = new AssignUploadEntity();
		entity.setPin(dto.getPin());
		entity.setcNo(dto.getcNo());
		entity.setEmail(dto.getEmail());
		entity.setUrl(dto.getUrl());
		entity.setFile(path);
		entity.setIpAddress(ip);
		entity.setHostName(hostName);
		try {
			dao.saveAssignUpload(entity);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		logger.info("Data in Service is:" + entity);

	}

}
