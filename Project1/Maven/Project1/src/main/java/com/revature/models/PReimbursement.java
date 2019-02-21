package com.revature.models;

public class PReimbursement {
	int resolverId;
	int reimbId;
	int status;
	
	public int getResolverId() {
		return resolverId;
	}
	public void setResolverId(int resolverId) {
		this.resolverId = resolverId;
	}
	public int getReimbId() {
		return reimbId;
	}
	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + reimbId;
		result = prime * result + resolverId;
		result = prime * result + status;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PReimbursement other = (PReimbursement) obj;
		if (reimbId != other.reimbId)
			return false;
		if (resolverId != other.resolverId)
			return false;
		if (status != other.status)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PReimbursement [resolverId=" + resolverId + ", reimbId=" + reimbId + ", status=" + status + "]";
	}
	public PReimbursement(int resolverId, int reimbId, int status) {
		super();
		this.resolverId = resolverId;
		this.reimbId = reimbId;
		this.status = status;
	}
	public PReimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}
}
