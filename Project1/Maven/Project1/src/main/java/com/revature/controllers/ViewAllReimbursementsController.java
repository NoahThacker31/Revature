package com.revature.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;

public class ViewAllReimbursementsController implements Controller{
	@Override
	public void handlePost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Reimbursement> rList = new ArrayList<>();
		ReimbursementService reimbService = new ReimbursementService();
		try {
			reimbService.viewAllReimbursements(rList);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ObjectMapper om = new ObjectMapper();
		om.writeValue(output, rList);
		byte[] reimbByte = output.toByteArray();
		Writer w = response.getWriter();
		w.write(new String(reimbByte));
	}
}
