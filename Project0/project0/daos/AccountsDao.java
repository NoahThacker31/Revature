package project0.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import project0.models.User;

public class AccountsDao {
	public void viewAllCAccounts() {
		String url = "jdbc:postgresql://localhost:5432/Project0";
		String role = "bankingadmin";
		String password = "admin";
		
		try(Connection conn = DriverManager.getConnection(url, role, password)) {
			PreparedStatement ps = 
					conn.prepareStatement("SELECT * FROM CheckingAccount;");
			
			System.out.println("");
			System.out.println("Checking Accounts: ");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println("ID: " + rs.getInt(1) + "   Amount: " + rs.getDouble(2) + "   Currency: " + rs.getString(3) + "   Frozen: " + rs.getBoolean(4));
			}
			
			rs.close();
			conn.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void viewAllSAccounts() {
		String url = "jdbc:postgresql://localhost:5432/Project0";
		String role = "bankingadmin";
		String password = "admin";
		
		try(Connection conn = DriverManager.getConnection(url, role, password)) {
			PreparedStatement ps = 
					conn.prepareStatement("SELECT * FROM SavingsAccount;");
			
			System.out.println("");
			System.out.println("Checking Accounts: ");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println("ID: " + rs.getInt(1) + "   Amount: " + rs.getDouble(2) + "   Currency: " + rs.getString(3) + "   Opened: " + rs.getTimestamp(4) + "   Last Interest Date: " + rs.getTimestamp(5) + "   Interest: " + rs.getDouble(6)*100 + "% at " + rs.getString(7));
			}
			
			rs.close();
			conn.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void viewAccounts(User user) {
		String url = "jdbc:postgresql://localhost:5432/Project0";
		String role = "customer";
		String password = "customer";
		
		try(Connection conn = DriverManager.getConnection(url, role, password)) {
			PreparedStatement ps1 = 
					conn.prepareStatement("SELECT * FROM CheckingAccount LEFT JOIN UserCheckingAccount " + 
					"ON CheckingAccount.cID = UserCheckingAccount.cID WHERE UserCheckingAccount.uID = ?;");
			ps1.setInt(1, user.getId());
			
			System.out.println("");
			System.out.println("Checking Accounts: ");
			ResultSet rs1 = ps1.executeQuery();
			while(rs1.next()) {
				System.out.println("ID: " + rs1.getInt(1) + "   Amount: " + rs1.getDouble(2) + "   Currency: " + rs1.getString(3) + "   Frozen: " + rs1.getBoolean(4));
			}
			
			PreparedStatement ps2 = 
					conn.prepareStatement("SELECT * FROM SavingsAccount LEFT JOIN UserSavingsAccount " + 
					"ON SavingsAccount.sID = UserSavingsAccount.sID WHERE UserSavingsAccount.uID = ?;");
			ps2.setInt(1, user.getId());
			
			System.out.println("");
			System.out.println("Savings Accounts: ");
			ResultSet rs2 = ps2.executeQuery();
			while(rs2.next()) {
				System.out.println("ID: " + rs2.getInt(1) + "   Amount: " + rs2.getDouble(2) + "   Currency: " + rs2.getString(3) + "   Opened: " + rs2.getTimestamp(4) + "   Last Interest Date: " + rs2.getTimestamp(5) + "   Interest: " + rs2.getDouble(6)*100 + "% at " + rs2.getString(7));
			}
			
			System.out.println("");
			
			rs1.close();
			rs2.close();
			conn.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void viewCAccounts(User user) {
		String url = "jdbc:postgresql://localhost:5432/Project0";
		String role = "customer";
		String password = "customer";
		
		try(Connection conn = DriverManager.getConnection(url, role, password)) {
			PreparedStatement ps = 
					conn.prepareStatement("SELECT * FROM CheckingAccount LEFT JOIN UserCheckingAccount " + 
					"ON CheckingAccount.cID = UserCheckingAccount.cID WHERE UserCheckingAccount.uID = ?;");
			ps.setInt(1, user.getId());
			
			System.out.println("Checking Accounts: ");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println("ID: " + rs.getInt(1) + "   Amount: " + rs.getDouble(2) + "   Currency: " + rs.getString(3) + "   Frozen: " + rs.getBoolean(4));
			}
			
			rs.close();
			conn.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void viewSAccounts(User user) {
		String url = "jdbc:postgresql://localhost:5432/Project0";
		String role = "customer";
		String password = "customer";
		
		try(Connection conn = DriverManager.getConnection(url, role, password)) {
			PreparedStatement ps = 
					conn.prepareStatement("SELECT * FROM SavingsAccount LEFT JOIN UserSavingsAccount " + 
					"ON SavingsAccount.sID = UserSavingsAccount.sID WHERE UserSavingsAccount.uID = ?;");
			ps.setInt(1, user.getId());
			
			System.out.println("Savings Accounts: ");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println("ID: " + rs.getInt(1) + "   Amount: " + rs.getDouble(2) + "   Currency: " + rs.getString(3) + "   Opened: " + rs.getTimestamp(4) + "   Last Interest Date: " + rs.getTimestamp(5) + "   Interest: " + rs.getDouble(6)*100 + "% at " + rs.getString(7));
			}
			
			rs.close();
			conn.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void openCheckingAccount(User user, String currency) {
		String url = "jdbc:postgresql://localhost:5432/Project0";
		String role = "customer";
		String password = "customer";
		int cID;
		
		try(Connection conn = DriverManager.getConnection(url, role, password)) {
			PreparedStatement ps1 = conn.prepareStatement("INSERT INTO CheckingAccount(balance, currency, frozen) VALUES (0, ?, false);");
			ps1.setString(1, currency);
			ps1.executeUpdate();
			
			PreparedStatement ps2 = conn.prepareStatement("SELECT MAX(cID) FROM CheckingAccount");
			ResultSet rs = ps2.executeQuery();
			rs.next();
			cID = rs.getInt(1);
			
			PreparedStatement ps3 = conn.prepareStatement("INSERT INTO UserCheckingAccount(uID, cID) VALUES(?, ?);");
			ps3.setInt(1, user.getId());
			ps3.setInt(2, cID);
			ps3.executeUpdate();
			
			rs.close();
			conn.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void openSavingsAccount(User user, String currency, double intAmt, String intType) {
		String url = "jdbc:postgresql://localhost:5432/Project0";
		String role = "customer";
		String password = "customer";
		int sID;
		
		try(Connection conn = DriverManager.getConnection(url, role, password)) {
			PreparedStatement ps1 = conn.prepareStatement("INSERT INTO SavingsAccount(balance, currency, openedDate, lastInterestDate, interestAmount, interestType) VALUES (0, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?, ?);");
			ps1.setString(1, currency);
			ps1.setDouble(2, intAmt);
			ps1.setString(3, intType);
			ps1.executeUpdate();
			
			PreparedStatement ps2 = conn.prepareStatement("SELECT MAX(sID) FROM SavingsAccount;");
			ResultSet rs = ps2.executeQuery();
			rs.next();
			sID = rs.getInt(1);
			
			PreparedStatement ps3 = conn.prepareStatement("INSERT INTO UserSavingsAccount(uID, sID) VALUES(?, ?);");
			ps3.setInt(1, user.getId());
			ps3.setInt(2, sID);
			ps3.executeUpdate();
			
			rs.close();
			conn.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Boolean checkCheckBelongsUser(User user, int act) {
		String url = "jdbc:postgresql://localhost:5432/Project0";
		String role = "customer";
		String password = "customer";
		
		try(Connection conn = DriverManager.getConnection(url, role, password)) {
			PreparedStatement ps = 
					conn.prepareStatement("SELECT * FROM userCheckingAccount WHERE uId = ? AND cID = ?;");
			ps.setInt(1, user.getId());
			ps.setDouble(2, act);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next() == true) {
				rs.close();
				conn.close();
				return true;
			} else {
				rs.close();
				conn.close();
				return false;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public Boolean checkCheckActWithdrawalValue(int act, double amt) {
		String url = "jdbc:postgresql://localhost:5432/Project0";
		String role = "customer";
		String password = "customer";
		
		try(Connection conn = DriverManager.getConnection(url, role, password)) {
			PreparedStatement ps = 
					conn.prepareStatement("SELECT * FROM checkingAccount WHERE cID = ? AND balance >= ?;");
			ps.setInt(1, act);
			ps.setDouble(2, amt);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next() == true) {
				rs.close();
				conn.close();
				return true;
			} else {
				rs.close();
				conn.close();
				return false;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public void withdrawalChecking(int accountID, double amount) {
		String url = "jdbc:postgresql://localhost:5432/Project0";
		String role = "customer";
		String password = "customer";
		
		try(Connection conn = DriverManager.getConnection(url, role, password)) {
			PreparedStatement ps1 = 
					conn.prepareStatement("UPDATE checkingAccount SET balance = balance - ? WHERE cID = ?;");
			ps1.setDouble(1, amount);
			ps1.setInt(2, accountID);
			ps1.executeUpdate();
			
			PreparedStatement ps2 = 
					conn.prepareStatement("INSERT INTO Transactions(cID, dateTime, amount) VALUES (?, CURRENT_TIMESTAMP, ?);");
			ps2.setInt(1, accountID);
			ps2.setDouble(2, -amount);
			ps2.executeUpdate();
			
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void depositChecking(int accountID, double amount) {
		String url = "jdbc:postgresql://localhost:5432/Project0";
		String role = "customer";
		String password = "customer";
		
		try(Connection conn = DriverManager.getConnection(url, role, password)) {
			PreparedStatement ps1 = 
					conn.prepareStatement("UPDATE checkingAccount SET balance = balance + ? WHERE cID = ?;");
			ps1.setDouble(1, amount);
			ps1.setInt(2, accountID);
			ps1.executeUpdate();
			
			PreparedStatement ps2 = 
					conn.prepareStatement("INSERT INTO Transactions(cID, dateTime, amount) VALUES (?, CURRENT_TIMESTAMP, ?);");
			ps2.setInt(1, accountID);
			ps2.setDouble(2, amount);
			ps2.executeUpdate();
			
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void viewTransactions(int act) {
		String url = "jdbc:postgresql://localhost:5432/Project0";
		String role = "customer";
		String password = "customer";
		
		try(Connection conn = DriverManager.getConnection(url, role, password)) {
			PreparedStatement ps = 
					conn.prepareStatement("SELECT * FROM Transactions WHERE cID = ?;");
			ps.setInt(1, act);			
			ResultSet rs = ps.executeQuery();

			System.out.println("");
			System.out.println("Account: "+act);
			while(rs.next()) {
				System.out.println("Date: " + rs.getDate("dateTime") + "   Amount: " + rs.getDouble("amount"));
			}
			System.out.println("");
			
			conn.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void freezeUnfreezeAccount(int accountID, Boolean frozen) {
		String url = "jdbc:postgresql://localhost:5432/Project0";
		String role = "bankingadmin";
		String password = "admin";
		
		try(Connection conn = DriverManager.getConnection(url, role, password)) {
			PreparedStatement ps = 
					conn.prepareStatement("UPDATE checkingAccount SET frozen = ? WHERE cID = ?;");
			ps.setBoolean(1, frozen);
			ps.setInt(2, accountID);
			
			ps.executeUpdate();
			conn.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Boolean checkSavingsBelongsUser(User user, int act) {
		String url = "jdbc:postgresql://localhost:5432/Project0";
		String role = "customer";
		String password = "customer";
		
		try(Connection conn = DriverManager.getConnection(url, role, password)) {
			PreparedStatement ps = 
					conn.prepareStatement("SELECT * FROM userSavingsAccount WHERE uId = ? AND sID = ?;");
			ps.setInt(1, user.getId());
			ps.setDouble(2, act);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next() == true) {
				rs.close();
				conn.close();
				return true;
			} else {
				rs.close();
				conn.close();
				return false;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public Boolean checkSavingsActWithdrawalValue(int act, double amt) {
		String url = "jdbc:postgresql://localhost:5432/Project0";
		String role = "customer";
		String password = "customer";
		
		try(Connection conn = DriverManager.getConnection(url, role, password)) {
			PreparedStatement ps = 
					conn.prepareStatement("SELECT * FROM savingsAccount WHERE sID = ? AND balance >= ?;");
			ps.setInt(1, act);
			ps.setDouble(2, amt);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next() == true) {
				rs.close();
				conn.close();
				return true;
			} else {
				rs.close();
				conn.close();
				return false;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public void withdrawalSavings(int accountID, double amount) {
		String url = "jdbc:postgresql://localhost:5432/Project0";
		String role = "customer";
		String password = "customer";
		
		try(Connection conn = DriverManager.getConnection(url, role, password)) {
			PreparedStatement ps = 
					conn.prepareStatement("UPDATE savingsAccount SET balance = balance - ? WHERE sID = ?;");
			ps.setDouble(1, amount);
			ps.setInt(2, accountID);
			ps.executeUpdate();
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void depositSavings(int accountID, double amount) {
		String url = "jdbc:postgresql://localhost:5432/Project0";
		String role = "customer";
		String password = "customer";
		
		try(Connection conn = DriverManager.getConnection(url, role, password)) {
			PreparedStatement ps = 
					conn.prepareStatement("UPDATE savingsAccount SET balance = balance + ? WHERE sID = ?;");
			ps.setDouble(1, amount);
			ps.setInt(2, accountID);
			ps.executeUpdate();
			conn.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void joinCheckingAccount(User user, int act, Boolean isChild) {
		String url = "jdbc:postgresql://localhost:5432/Project0";
		String role = "customer";
		String password = "customer";
		
		try(Connection conn = DriverManager.getConnection(url, role, password)) {
			PreparedStatement ps = 
					conn.prepareStatement("INSERT INTO UserCheckingAccount (uID, cID, isChild) VALUES (?, ?, ?)");
			ps.setInt(1, user.getId());
			ps.setInt(2, act);
			ps.setBoolean(3, isChild);
			ps.executeUpdate();
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void joinSavingsAccount(User user, int act, Boolean isChild) {
		String url = "jdbc:postgresql://localhost:5432/Project0";
		String role = "customer";
		String password = "customer";
		
		try(Connection conn = DriverManager.getConnection(url, role, password)) {
			PreparedStatement ps = 
					conn.prepareStatement("INSERT INTO UserSavingsAccount (uID, sID, isChild) VALUES (?, ?, ?)");
			ps.setInt(1, user.getId());
			ps.setInt(2, act);
			ps.setBoolean(3, isChild);
			ps.executeUpdate();
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteCheckingAccount(int act) {
		String url = "jdbc:postgresql://localhost:5432/Project0";
		String role = "bankingadmin";
		String password = "admin";
		
		try(Connection conn = DriverManager.getConnection(url, role, password)) {
			PreparedStatement ps1 = 
					conn.prepareStatement("DELETE FROM UserCheckingAccount WHERE cID = ?;");
			ps1.setInt(1, act);
			ps1.executeUpdate();
			
			PreparedStatement ps2 = 
					conn.prepareStatement("DELETE FROM CheckingAccount WHERE cID = ?;");
			ps2.setInt(1, act);
			ps2.executeUpdate();
			conn.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteSavingsAccount(int act) {
		String url = "jdbc:postgresql://localhost:5432/Project0";
		String role = "bankingadmin";
		String password = "admin";
		
		try(Connection conn = DriverManager.getConnection(url, role, password)) {
			PreparedStatement ps1 = 
					conn.prepareStatement("DELETE FROM UserSavingsAccount WHERE sID = ?;");
			ps1.setInt(1, act);
			ps1.executeUpdate();
			
			PreparedStatement ps2 = 
					conn.prepareStatement("DELETE FROM SavingsAccount WHERE sID = ?;");
			ps2.setInt(1, act);
			ps2.executeUpdate();
			conn.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
