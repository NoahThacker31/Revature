package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controllers.Controller;
import com.revature.util.Delegate;
import com.revature.util.HttpException;

public class DispatcherServlet extends HttpServlet{
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//***********************************************************
		// fixes an error in Angular
		//response.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		//response.addHeader("Access-Control-Allow-Headers", "content-type");
		//***********************************************************
		
		String[] uriParts = request.getRequestURI().split("/");
		
		Delegate delegate = null;
	
		if (uriParts.length > 1) {
			String part = uriParts[2];
			delegate = Delegate.getDelegate(part);
		} else {
			delegate = Delegate.NOT_FOUND;
		}
	
		if (delegate == Delegate.NOT_FOUND) {
			response.sendError(404);
			return;
		}
	
		request.setAttribute("controller", delegate.controller);
		try {
			super.service(request, response);
		} catch (HttpException e) {
			response.sendError(e.getCode());
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Controller controller = (Controller) request.getAttribute("controller");
		controller.handleGet(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Controller controller = (Controller) request.getAttribute("controller");
		try {
			controller.handlePost(request, response);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
