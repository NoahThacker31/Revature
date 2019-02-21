package com.revature.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.User;

public class UserDao {
	public Boolean checkIfEmailExists(String email) throws ClassNotFoundException {
		String url = "jdbc:postgresql://localhost:5432/Project1";
		String role = "employee";
		String password = "employee";
		
		Boolean alreadyExists = false;
		
		Class.forName("org.postgresql.Driver");
		try(Connection conn = DriverManager.getConnection(url, role, password)) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM ERS_USERS WHERE USER_EMAIL = ?;");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				alreadyExists = true;
			}
			rs.close();
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return alreadyExists;
	}
	
	public User getUserByEmail(String email) throws ClassNotFoundException {
		String url = "jdbc:postgresql://localhost:5432/Project1";
		String role = "employee";
		String password = "employee";

		User user = new User();
		
		Class.forName("org.postgresql.Driver");
		try(Connection conn = DriverManager.getConnection(url, role, password)) {
			PreparedStatement ps = conn.prepareStatement("SELECT ERS_USERS.ERS_USERS_ID, ERS_USERS.ERS_USERNAME,"
					+ " ERS_USERS.USER_FIRST_NAME, ERS_USERS.USER_LAST_NAME, ERS_USER_ROLES.USER_ROLE, ERS_USERS.ERS_PASSWORD"
					+ " FROM ERS_USERS INNER JOIN ERS_USER_ROLES ON ERS_USERS.USER_ROLE_ID = ERS_USER_ROLES.ERS_USERS_ROLE_ID"
					+ " WHERE USER_EMAIL = ?;");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				user.setId(rs.getInt("ERS_USERS_ID"));
				user.setUsername(rs.getString("ERS_USERNAME"));
				user.setFirstName(rs.getString("USER_FIRST_NAME"));
				user.setLastName(rs.getString("USER_LAST_NAME"));
				user.setRole(rs.getString("USER_ROLE"));
				user.setPassword(rs.getString("ERS_PASSWORD"));
			}
			
			rs.close();
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
}