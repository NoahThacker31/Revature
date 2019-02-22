package com.revature.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.revature.models.InsertReimbursement;
import com.revature.models.PReimbursement;
import com.revature.models.Reimbursement;

public class ReimbursementDao {
	public void viewMyReimb(List<Reimbursement> rList, int uid) throws ClassNotFoundException {
		String url = "jdbc:postgresql://localhost:5432/Project1";
		String role = "employee";
		String password = "employee";
		
		Class.forName("org.postgresql.Driver");
		try(Connection conn = DriverManager.getConnection(url, role, password)) {
			PreparedStatement ps = conn.prepareStatement("SELECT ERS_REIMBURSEMENT.REIMB_ID, ERS_REIMBURSEMENT.REIMB_AUTHOR,"
					+ " ERS_REIMBURSEMENT.REIMB_AMOUNT, ERS_REIMBURSEMENT.REIMB_DESCRIPTION, ERS_REIMBURSEMENT_TYPE.REIMB_TYPE,"
					+ " ERS_REIMBURSEMENT_STATUS.REIMB_STATUS, ERS_REIMBURSEMENT.REIMB_SUBMITTED, ERS_REIMBURSEMENT.REIMB_RESOLVER,"
					+ " ERS_REIMBURSEMENT.REIMB_RESOLVED FROM ERS_REIMBURSEMENT"
					+ ""
					+ " INNER JOIN ERS_REIMBURSEMENT_STATUS"
					+ " ON ERS_REIMBURSEMENT.REIMB_STATUS_ID = ERS_REIMBURSEMENT_STATUS.REIMB_STATUS_ID"
					+ " INNER JOIN ERS_REIMBURSEMENT_TYPE"
					+ " ON ERS_REIMBURSEMENT.REIMB_TYPE_ID = ERS_REIMBURSEMENT_TYPE.REIMB_TYPE_ID"
					+ ""
					+ " WHERE ERS_REIMBURSEMENT.REIMB_AUTHOR = ?"
					+ " ORDER BY ERS_REIMBURSEMENT.REIMB_ID DESC;");
			ps.setInt(1, uid);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Reimbursement reimb = new Reimbursement();
				
				//thisReimbursement.setId(rs.getInt("REIMB_ID"));
				reimb.setId(rs.getInt("REIMB_ID"));
				reimb.setAuthorid(rs.getInt("REIMB_AUTHOR"));
				reimb.setAmount(rs.getDouble("REIMB_AMOUNT"));
				reimb.setSubmitted(rs.getTimestamp("REIMB_SUBMITTED"));
				reimb.setDescription(rs.getString("REIMB_DESCRIPTION"));
				reimb.setType(rs.getString("REIMB_TYPE"));
				reimb.setStatus(rs.getString("REIMB_STATUS"));
				reimb.setResolver(rs.getInt("REIMB_RESOLVER"));
				reimb.setResolved(rs.getTimestamp("REIMB_RESOLVED"));
				rList.add(reimb);
			}
			rs.close();
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void viewPendingReimb(List<Reimbursement> rList, int resolverId) throws ClassNotFoundException {
		String url = "jdbc:postgresql://localhost:5432/Project1";
		String role = "employee";
		String password = "employee";
		
		Class.forName("org.postgresql.Driver");
		try(Connection conn = DriverManager.getConnection(url, role, password)) {
			PreparedStatement ps = conn.prepareStatement("SELECT ERS_REIMBURSEMENT.REIMB_ID, ERS_REIMBURSEMENT.REIMB_AUTHOR,"
					+ " ERS_REIMBURSEMENT.REIMB_AMOUNT, ERS_REIMBURSEMENT.REIMB_DESCRIPTION, ERS_REIMBURSEMENT_TYPE.REIMB_TYPE,"
					+ " ERS_REIMBURSEMENT_STATUS.REIMB_STATUS, ERS_REIMBURSEMENT.REIMB_SUBMITTED, ERS_REIMBURSEMENT.REIMB_RESOLVER,"
					+ " ERS_REIMBURSEMENT.REIMB_RESOLVED FROM ERS_REIMBURSEMENT"
					+ ""
					+ " INNER JOIN ERS_REIMBURSEMENT_STATUS"
					+ " ON ERS_REIMBURSEMENT.REIMB_STATUS_ID = ERS_REIMBURSEMENT_STATUS.REIMB_STATUS_ID"
					+ " INNER JOIN ERS_REIMBURSEMENT_TYPE"
					+ " ON ERS_REIMBURSEMENT.REIMB_TYPE_ID = ERS_REIMBURSEMENT_TYPE.REIMB_TYPE_ID"
					+ ""
					+ " WHERE ERS_REIMBURSEMENT.REIMB_STATUS_ID = 1 AND ERS_REIMBURSEMENT.REIMB_AUTHOR != ?"
					+ " ORDER BY ERS_REIMBURSEMENT.REIMB_ID DESC;");
			ps.setInt(1, resolverId);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Reimbursement reimb = new Reimbursement();
				
				//thisReimbursement.setId(rs.getInt("REIMB_ID"));
				reimb.setId(rs.getInt("REIMB_ID"));
				reimb.setAuthorid(rs.getInt("REIMB_AUTHOR"));
				reimb.setAmount(rs.getDouble("REIMB_AMOUNT"));
				reimb.setSubmitted(rs.getTimestamp("REIMB_SUBMITTED"));
				reimb.setDescription(rs.getString("REIMB_DESCRIPTION"));
				reimb.setType(rs.getString("REIMB_TYPE"));
				reimb.setStatus(rs.getString("REIMB_STATUS"));
				reimb.setResolver(rs.getInt("REIMB_RESOLVER"));
				reimb.setResolved(rs.getTimestamp("REIMB_RESOLVED"));
				rList.add(reimb);
			}
			rs.close();
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void viewAllReimb(List<Reimbursement> rList) throws ClassNotFoundException {
		String url = "jdbc:postgresql://localhost:5432/Project1";
		String role = "employee";
		String password = "employee";
		
		Class.forName("org.postgresql.Driver");
		try(Connection conn = DriverManager.getConnection(url, role, password)) {
			PreparedStatement ps = conn.prepareStatement("SELECT ERS_REIMBURSEMENT.REIMB_ID, ERS_REIMBURSEMENT.REIMB_AUTHOR,"
					+ " ERS_REIMBURSEMENT.REIMB_AMOUNT, ERS_REIMBURSEMENT.REIMB_DESCRIPTION, ERS_REIMBURSEMENT_TYPE.REIMB_TYPE,"
					+ " ERS_REIMBURSEMENT_STATUS.REIMB_STATUS, ERS_REIMBURSEMENT.REIMB_SUBMITTED, ERS_REIMBURSEMENT.REIMB_RESOLVER,"
					+ " ERS_REIMBURSEMENT.REIMB_RESOLVED FROM ERS_REIMBURSEMENT"
					+ ""
					+ " INNER JOIN ERS_REIMBURSEMENT_STATUS"
					+ " ON ERS_REIMBURSEMENT.REIMB_STATUS_ID = ERS_REIMBURSEMENT_STATUS.REIMB_STATUS_ID"
					+ " INNER JOIN ERS_REIMBURSEMENT_TYPE"
					+ " ON ERS_REIMBURSEMENT.REIMB_TYPE_ID = ERS_REIMBURSEMENT_TYPE.REIMB_TYPE_ID"
					+ " ORDER BY ERS_REIMBURSEMENT.REIMB_ID DESC;");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Reimbursement reimb = new Reimbursement();
				
				//thisReimbursement.setId(rs.getInt("REIMB_ID"));
				reimb.setId(rs.getInt("REIMB_ID"));
				reimb.setAuthorid(rs.getInt("REIMB_AUTHOR"));
				reimb.setAmount(rs.getDouble("REIMB_AMOUNT"));
				reimb.setSubmitted(rs.getTimestamp("REIMB_SUBMITTED"));
				reimb.setDescription(rs.getString("REIMB_DESCRIPTION"));
				reimb.setType(rs.getString("REIMB_TYPE"));
				reimb.setStatus(rs.getString("REIMB_STATUS"));
				reimb.setResolver(rs.getInt("REIMB_RESOLVER"));
				reimb.setResolved(rs.getTimestamp("REIMB_RESOLVED"));
				rList.add(reimb);
			}
			rs.close();
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void createReimb(InsertReimbursement insReimb) throws ClassNotFoundException {
		String url = "jdbc:postgresql://localhost:5432/Project1";
		String role = "employee";
		String password = "employee";
		
		Class.forName("org.postgresql.Driver");
		try(Connection conn = DriverManager.getConnection(url, role, password)) {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO ERS_REIMBURSEMENT (REIMB_AMOUNT, REIMB_SUBMITTED, REIMB_RESOLVED,"
					+ " REIMB_DESCRIPTION, REIMB_AUTHOR, REIMB_RESOLVER, REIMB_STATUS_ID, REIMB_TYPE_ID) VALUES (?, CURRENT_DATE, NULL,"
					+ "?, ?, NULL, 1, ?);");
			ps.setDouble(1, insReimb.getAmount());
			ps.setString(2, insReimb.getDescription());
			ps.setInt(3, insReimb.getAuthor());
			ps.setInt(4, insReimb.getType());
			
			ps.executeUpdate();
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void processReimb(PReimbursement reimb) throws ClassNotFoundException {
		String url = "jdbc:postgresql://localhost:5432/Project1";
		String role = "employee";
		String password = "employee";
		
		Class.forName("org.postgresql.Driver");
		try(Connection conn = DriverManager.getConnection(url, role, password)) {
			PreparedStatement ps = conn.prepareStatement("UPDATE ERS_REIMBURSEMENT SET REIMB_STATUS_ID = ?, REIMB_RESOLVER = ?,"
					+ " REIMB_RESOLVED = CURRENT_TIMESTAMP WHERE REIMB_ID = ?;");
			ps.setInt(1, reimb.getStatus());
			ps.setInt(2, reimb.getResolverId());
			ps.setInt(3, reimb.getReimbId());
			
			ps.executeUpdate();
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
