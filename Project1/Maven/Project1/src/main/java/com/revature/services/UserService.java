package com.revature.services;

import com.revature.daos.UserDao;
import com.revature.dto.LoginRequestDTO;
import com.revature.models.User;
import com.revature.util.HttpException;

public class UserService {
	UserDao userDao = new UserDao();

	public User login(LoginRequestDTO dto) throws ClassNotFoundException {
		Boolean emailExists;
		emailExists = userDao.checkIfEmailExists(dto.getEmail());
		if(emailExists) {
			User user = userDao.getUserByEmail(dto.getEmail());
			if(user.getPassword().equals(dto.getPassword())) {
				return user;
			}
		}
		
		throw new HttpException(401, "Invalid login credentials");
	}
}
