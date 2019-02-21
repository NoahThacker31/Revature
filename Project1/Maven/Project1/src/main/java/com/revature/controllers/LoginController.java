package com.revature.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dto.LoginRequestDTO;
import com.revature.models.User;
import com.revature.services.UserService;

public class LoginController implements Controller{
	@Override
	public void handlePost(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException {
		UserService userService = new UserService();
		ObjectMapper om = new ObjectMapper();
		LoginRequestDTO dto = om.readValue(request.getReader(), LoginRequestDTO.class);
		
		User user = userService.login(dto);
		
		response.setContentType("application/json");
		response.getWriter().write(om.writeValueAsString(user));
	}
}