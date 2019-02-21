package com.revature.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
	public default void handleGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendError(404);
		return;
	}
	
	public default void handlePost(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException {
		response.sendError(404);
		return;
	}
}
