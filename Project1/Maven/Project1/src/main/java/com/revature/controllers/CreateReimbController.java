package com.revature.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.InsertReimbursement;
import com.revature.services.ReimbursementService;

public class CreateReimbController implements Controller{
	@Override
	public void handlePost(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException {
		ReimbursementService reimbServ = new ReimbursementService();
		ObjectMapper om = new ObjectMapper();
		InsertReimbursement insReimb = om.readValue(request.getInputStream(), InsertReimbursement.class);
		reimbServ.createReimbursement(insReimb);
	}
}
