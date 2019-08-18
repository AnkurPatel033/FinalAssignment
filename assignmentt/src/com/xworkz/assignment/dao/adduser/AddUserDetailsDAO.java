package com.xworkz.assignment.dao.adduser;

import java.util.List;

import com.xworkz.assignment.entities.signup.SignUpEntity;
import com.xworkz.assignment.exceptions.DAOException;

public interface AddUserDetailsDAO {

	
	public Integer addUserDetailsSave(SignUpEntity entity) throws DAOException;

	public List<SignUpEntity> getUserDetailsSave() throws DAOException;
	
}
