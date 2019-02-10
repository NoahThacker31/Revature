package project0.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import project0.models.User;

// ***** TRANSACTIONAL *****
// conn.setAutoCommit(false);
// run commit at the end.
public class UserDao {
	public Boolean checkIfEmailExists(String email) {
		String url = "jdbc:postgresql://localhost:5432/Project0";
		String role = "customer";
		String password = "customer";
		
		Boolean alreadyExists = false;
		
		try(Connection conn = DriverManager.getConnection(url, role, password)) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM UserAccount WHERE email = ? GROUP BY uID;");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				alreadyExists = true;
				rs.close();
				conn.close();
				return alreadyExists;
			} else {
				rs.close();
				conn.close();
				return alreadyExists;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return alreadyExists;
	}
	
	public Boolean checkIfUidExists(int uid) {
		String url = "jdbc:postgresql://localhost:5432/Project0";
		String role = "customer";
		String password = "customer";
		
		Boolean alreadyExists = false;
		
		try(Connection conn = DriverManager.getConnection(url, role, password)) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM UserAccount WHERE uID = ? GROUP BY uID;");
			ps.setInt(1, uid);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				alreadyExists = true;
				rs.close();
				conn.close();
				return alreadyExists;
			} else {
				rs.close();
				conn.close();
				return alreadyExists;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return alreadyExists;
	}
	
	public Boolean checkIfEmployee(int uid) {
		String url = "jdbc:postgresql://localhost:5432/Project0";
		String role = "customer";
		String password = "customer";
		
		Boolean isAnEmployee = false;
		
		try(Connection conn = DriverManager.getConnection(url, role, password)) {
			PreparedStatement ps = conn.prepareStatement("SELECT isEmployee1 FROM UserAccount WHERE uID = ? GROUP BY uID;");
			ps.setInt(1, uid);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			isAnEmployee = rs.getBoolean(1);
			rs.close();
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return isAnEmployee;
	}
	
	public Boolean checkIfAdmin(int uid) {
		String url = "jdbc:postgresql://localhost:5432/Project0";
		String role = "customer";
		String password = "customer";
		
		Boolean isAnAdmin = false;
		
		try(Connection conn = DriverManager.getConnection(url, role, password)) {
			PreparedStatement ps = conn.prepareStatement("SELECT isAdmin FROM UserAccount WHERE uID = ? GROUP BY uID;");
			ps.setInt(1, uid);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			isAnAdmin = rs.getBoolean(1);
			rs.close();
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return isAnAdmin;
	}
	
	public void viewAllUsers() {
		String url = "jdbc:postgresql://localhost:5432/Project0";
		String role = "bankingadmin";
		String password = "admin";
		
		try(Connection conn = DriverManager.getConnection(url, role, password)) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM UserAccount;");
			
			System.out.println("");
			System.out.println("Users: ");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println("ID: " + rs.getInt(1) + "   Name: " + rs.getString(4) + " " + rs.getString(5) + "   Employee: " + rs.getBoolean(6) + "   Admin: " + rs.getBoolean(7));
			}
			
			rs.close();
			conn.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void signUp(User user) {
		String url = "jdbc:postgresql://localhost:5432/Project0";
		String role = "customer";
		String password = "customer";
		
		try(Connection conn = DriverManager.getConnection(url, role, password)) {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO UserAccount " + 
				"(email, password, fName, lName, isEmployee, isAdmin) VALUES (?, ?, ?, ?, ?, ?);");
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getfName());
			ps.setString(4, user.getlName());
			ps.setBoolean(5, user.getIsEmployee());
			ps.setBoolean(6, user.getIsAdmin());
			
			ps.executeUpdate();
			
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public User logIn(User user, String temail, String tpassword) {
		String url = "jdbc:postgresql://localhost:5432/Project0";
		String role = "customer";
		String password = "customer";
		
		try(Connection conn = DriverManager.getConnection(url, role, password)) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM UserAccount WHERE email = ?");
			ps.setString(1, temail);
			
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			if(rs.getString("password").equals(tpassword)) {
				user.setId(rs.getInt("uID"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setfName(rs.getString("fName"));
				user.setlName(rs.getString("lName"));
				user.setIsEmployee(rs.getBoolean("isEmployee"));
				user.setIsAdmin(rs.getBoolean("isAdmin"));
				rs.close();
				conn.close();
				return user;
			} else {
				rs.close();
				conn.close();
				return user;
			}
			
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return user;
	}
	
	public void changeEmployeeStatus(int userID, Boolean empStat) {
		String url = "jdbc:postgresql://localhost:5432/Project0";
		String role = "bankingadmin";
		String password = "admin";
		
		try(Connection conn = DriverManager.getConnection(url, role, password)) {
			PreparedStatement ps = conn.prepareStatement("UPDATE UserAccount SET isEmployee = ? WHERE uID = ?");
			ps.setBoolean(1, empStat);
			ps.setInt(2, userID);
			
			ps.executeUpdate();
			
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void changeAdminStatus(int userID, Boolean adminStat) {
		String url = "jdbc:postgresql://localhost:5432/Project0";
		String role = "bankingadmin";
		String password = "admin";
		
		try(Connection conn = DriverManager.getConnection(url, role, password)) {
			PreparedStatement ps = conn.prepareStatement("UPDATE UserAccount SET isAdmin = ? WHERE uID = ?");
			ps.setBoolean(1, adminStat);
			ps.setInt(2, userID);
			
			ps.executeUpdate();
			
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}