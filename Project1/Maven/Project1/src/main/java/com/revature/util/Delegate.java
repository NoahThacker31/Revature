package com.revature.util;

import com.revature.controllers.Controller;
import com.revature.controllers.CreateReimbController;
import com.revature.controllers.LoginController;
import com.revature.controllers.ProcessReimbursementsController;
import com.revature.controllers.ViewAllReimbursementsController;
import com.revature.controllers.ViewMyReimbursementsController;
import com.revature.controllers.ViewPendingReimbursementsController;

public enum Delegate {
	LOGIN(new LoginController()),
	VIEWALLREIMB(new ViewAllReimbursementsController()),
	VIEWPENDINGREIMB(new ViewPendingReimbursementsController()),
	VIEWMYREIMB(new ViewMyReimbursementsController()),
	CREATEREIMB(new CreateReimbController()),
	PROCESSREIMB(new ProcessReimbursementsController()),
	NOT_FOUND(new Controller() {});
	
	public Controller controller;
	
	private Delegate(Controller controller) {
		this.controller = controller;
	}
	
	public static Delegate getDelegate(String str) {
		if(str == null) {
			return NOT_FOUND;
		}
		String upper = str.toUpperCase();
		for(Delegate d: Delegate.values()) {
			if(d.name().equals(upper)) {
				return d;
			}
		}
		return NOT_FOUND;
	}
}
