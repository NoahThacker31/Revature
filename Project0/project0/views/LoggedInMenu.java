package project0.views;

import project0.models.User;
import project0.services.AccountsService;
import project0.services.UserService;
import project0.util.InputUtil;

public class LoggedInMenu implements View{
	public int inputValue;
	User user = UserService.getUser();
	UserService userServ = new UserService();
	AccountsService actServ = new AccountsService();
	
	@Override
	public void showMenu() {
		//System.out.println("Hello " + user.getfName() + ".  What would you like to do today?");
		System.out.println("1. View My Accounts");
		System.out.println("2. Open Account");
		System.out.println("3. Withdrawal / Deposit");
		System.out.println("4. View Transaction History");
		System.out.println("5. Transfer Funds");
		System.out.println("6. Add User to Account");
		if(user.getIsEmployee()) {
			System.out.println("7. Freeze / Unfreeze Account");
			System.out.println("8. Close Account");
		}
		if(user.getIsAdmin()) {
			System.out.println("9. Make user an Employee.");
			System.out.println("10. Make user an Admin.");
		}
		System.out.println("0. Log Out");
	}
	
	@Override
	public View process() {
		switch (inputValue) {
			case 1:
				// View Accounts
				actServ.viewMyAccounts(user);
				return new LoggedInMenu();
			case 2:
				// Open Accounts
				actServ.openAccount(user);
				return new LoggedInMenu();
			case 3:
				// Withdrawal / Deposit;
				actServ.withDepo();
				return new LoggedInMenu();
			case 4:
				// Add User to Account;
				actServ.viewTransactions();
				return new LoggedInMenu();
			case 5:
				// Transfer Funds;
				actServ.transferFunds();
				return new LoggedInMenu();
			case 6:
				// Add User to Account;
				actServ.addUserToAccount();
				return new LoggedInMenu();
			case 7:
				// Freeze / Unfreeze Account;
				actServ.freezeUnfreeze();
				return new LoggedInMenu();
			case 8:
				// Close Account;
				actServ.closeAccount();
				return new LoggedInMenu();
			case 9:
				// Make user an Employee;
				userServ.makeEmployee();
				return new LoggedInMenu();
			case 10:
				// Make user an Admin;
				userServ.makeAdmin();
				return new LoggedInMenu();
			case 0:
				// Log Out
				user.setId(0);
				return new MainMenu();
		}
		return null;

	}
	
	@Override
	public void getUserInput() {
		if(user.getIsAdmin()) {
			this.inputValue = InputUtil.getIntChoice(10);
		} else if(user.getIsEmployee()) {
			this.inputValue = InputUtil.getIntChoice(8);
		} else
		this.inputValue = InputUtil.getIntChoice(6);
	}
}
