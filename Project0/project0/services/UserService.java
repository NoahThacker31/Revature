package project0.services;

// Hashing Passwords Import
//import org.mindrot.jbcrypt.BCrypt;

import project0.daos.UserDao;
import project0.models.User;
import project0.util.InputUtil;
import project0.views.MainMenu;
import project0.views.View;

public class UserService {
	public static User user = new User();
	private static UserDao userDao = new UserDao();
	
	public static User getUser() {
		return user;
	}
	
	public static void setUser(User user) {
		UserService.user = user;
	}
	
	public View signUp() {
		User user = new User();
		String temail;
		String tpassword;
		String tfname;
		String tlname;
		
		// Gets an email from the user.
		System.out.print("Please enter an email: ");
		temail = InputUtil.getStringInput(30);
		
		// Checks if given email has "@" in the middle somewhere (not the end and not the beginning AND
		// checks if it ends in ".com".  If they are, it's a valid email, otherwise it isn't.
		// If the email is invalid, they are stuck in a loop of entering new emails.
		while(temail.matches("(?i).*@.*") == false || temail.matches("(?i).*.com") == false) {
			// Get's new email from the user after telling them they entered an invalid email
			System.out.println("That is not a valid email.  Please enter a valid email.");
			temail = InputUtil.getStringInput(30);
		}
		
		// Checks if the email already exists in the system.  If so, user must resubmit email.
		if(userDao.checkIfEmailExists(temail) == true) {
			System.out.println("There is already an account with that email in our system.");
			return new MainMenu();
		}
		
		// Gets user input for password
		System.out.print("Please enter a pasword: ");
		tpassword = InputUtil.getStringInput(30);
		
		//Hashing passwords
		// I didn't use hashed passwords as I have generated insert statements through the database for the presentation
		// In a real-world application, the passwords would need to be hashed before saved to the database
		//tpassword = BCrypt.hashpw(tpassword, BCrypt.gensalt());
		
		// Gets user input for first name
		System.out.print("Please enter a first name: ");
		tfname = InputUtil.getStringInput(25);
		tfname = (tfname.toUpperCase()).charAt(0) + (tfname.toLowerCase()).substring(1);
		
		// Gets user input for last name
		System.out.print("Please enter a last name: ");
		tlname = InputUtil.getStringInput(25);
		tlname = (tlname.toUpperCase()).charAt(0) + (tlname.toLowerCase()).substring(1);
		
		
		// By default the users are created as non-employee / non-admins.
		user.setEmail(temail);
		user.setPassword(tpassword);
		user.setfName(tfname);
		user.setlName(tlname);
		user.setIsEmployee(false);
		user.setIsAdmin(false);
		
		// Signs the user up through the Dao
		userDao.signUp(user);
		System.out.println("Account successfully created.");
		
		return new MainMenu();
	}
	
	public boolean login() {
		
		String temail, tpassword;
		System.out.print("Please enter an email: ");
		temail = InputUtil.getStringInput(30);
		
		// Runs the email confirmation through the data base
		Boolean validEmail = userDao.checkIfEmailExists(temail);
		if(!validEmail) {
			System.out.println("The email doesn't exist in the database.");
			return false;
		}
		
		System.out.print("Please enter a password: ");
		tpassword = InputUtil.getStringInput(30);
		
		//Hashing passwords
		// I didn't use hashed passwords as I have generated insert statements through the database for the presentation
		// In a real-world application, the passwords would need to be hashed before saved to the database
		//tpassword = BCrypt.hashpw(tpassword, BCrypt.gensalt());
		
		// Runs the login confirmation through the database
		user = userDao.logIn(user, temail, tpassword);
		user.getId();
		
		if(user.getId() != 0) {
			return true;
		} else {
			System.out.println("Incorrect password.");
			return false;
		}
	}
	
	public void makeEmployee() {
		// Displays all user ids, names and if they are employees or admins
		userDao.viewAllUsers();
		System.out.println("Please select an account number: ");
		int tempID = InputUtil.getIntChoice(10);
		while(userDao.checkIfUidExists(tempID) == false) {
			System.out.println("That account doesn't exist.");
			userDao.viewAllUsers();
			System.out.println("Please enter an account listed above.");
			tempID = InputUtil.getIntChoice(10);
		}
		
		if (userDao.checkIfEmployee(tempID) == false) {
			System.out.println("The user " + tempID + " is not an employee.  Would you like to make them an employee?");
			Boolean yesNo = InputUtil.getBooleanChoice();
			if(yesNo) {
				userDao.changeEmployeeStatus(tempID, true);
				System.out.println("The user " + tempID + " is now an employee.");
			} else {
				System.out.println("The user " + tempID + " is still not an employee.");
			}
		} else {
			System.out.println("The user " + tempID + " is an employee.  Would you like to revoke their employee status?");
			Boolean yesNo = InputUtil.getBooleanChoice();
			if(yesNo) {
				userDao.changeEmployeeStatus(tempID, false);
				System.out.println("The user " + tempID + " is now no longer an employee.");
			} else {
				System.out.println("The user " + tempID + " is still an employee.");
			}
		}
	}
	
	public void makeAdmin() {
		userDao.viewAllUsers();
		System.out.println("Please select an account number: ");
		int tempID = InputUtil.getIntChoice(10);
		while(userDao.checkIfUidExists(tempID) == false) {
			System.out.println("That account doesn't exist.");
			userDao.viewAllUsers();
			System.out.println("Please enter an account listed above.");
			tempID = InputUtil.getIntChoice(10);
		}
		
		if (userDao.checkIfAdmin(tempID) == false) {
			System.out.println("The user " + tempID + " is not an admin.  Would you like to make them an admin?");
			Boolean yesNo = InputUtil.getBooleanChoice();
			if(yesNo) {
				userDao.changeAdminStatus(tempID, true);
				System.out.println("The user " + tempID + " is now an admin.");
			} else {
				System.out.println("The user " + tempID + " is still not an admin.");
			}
		} else {
			System.out.println("The user " + tempID + " is an admin.  Would you like to revoke their admin status?");
			Boolean yesNo = InputUtil.getBooleanChoice();
			if(yesNo) {
				userDao.changeAdminStatus(tempID, false);
				System.out.println("The user " + tempID + " is now no longer an admin.");
			} else {
				System.out.println("The user " + tempID + " is still an admin.");
			}
		}
	}
}
