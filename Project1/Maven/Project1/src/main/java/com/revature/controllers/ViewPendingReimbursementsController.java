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
import com.revature.models.UserId;
import com.revature.services.ReimbursementService;

public class ViewPendingReimbursementsController implements Controller{
	@Override
	public void handlePost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ObjectMapper om = new ObjectMapper();
		UserId rid = om.readValue(request.getReader(), UserId.class);
		int resolverId = rid.getId();
		
		List<Reimbursement> rList = new ArrayList<>();
		ReimbursementService reimbService = new ReimbursementService();
		try {
			reimbService.viewPendingReimbursements(rList, resolverId);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		om.writeValue(output, rList);
		byte[] reimbByte = output.toByteArray();
		Writer w = response.getWriter();
		w.write(new String(reimbByte));
	}
}
