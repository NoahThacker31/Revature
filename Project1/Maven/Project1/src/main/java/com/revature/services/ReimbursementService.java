package com.revature.services;

import java.util.List;

import com.revature.daos.ReimbursementDao;
import com.revature.models.InsertReimbursement;
import com.revature.models.PReimbursement;
import com.revature.models.Reimbursement;
import com.revature.models.User;

public class ReimbursementService {
	ReimbursementDao reimbDao = new ReimbursementDao();
	
	public void viewAllReimbursements(List<Reimbursement> rList) throws ClassNotFoundException {
		reimbDao.viewAllReimb(rList);
	}
	
	public void viewPendingReimbursements(List<Reimbursement> rList, int resolverId) throws ClassNotFoundException {
		reimbDao.viewPendingReimb(rList, resolverId);
	}
	
	public void viewMyReimbursements(List<Reimbursement> rList, int uid) throws ClassNotFoundException {
		reimbDao.viewMyReimb(rList, uid);
	}
	
	public void createReimbursement(InsertReimbursement insReimb) throws ClassNotFoundException {
		reimbDao.createReimb(insReimb);
	}
	
	public void processReimbursement(PReimbursement newReimb) throws ClassNotFoundException {
		reimbDao.processReimb(newReimb);
	}
}
