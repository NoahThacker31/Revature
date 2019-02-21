package com.revature.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.PReimbursement;
import com.revature.services.ReimbursementService;

public class ProcessReimbursementsController implements Controller{
	@Override
	public void handlePost(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException {
		ReimbursementService reimbService = new ReimbursementService();
		ObjectMapper om = new ObjectMapper();
		PReimbursement pm = om.readValue(request.getReader(), PReimbursement.class);
		
		reimbService.processReimbursement(pm);
	}
}
