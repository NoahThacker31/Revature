package project0.views;

import project0.models.User;
import project0.services.UserService;
import project0.util.InputUtil;

public class MainMenu implements View {
	public int inputValue;
	public boolean loginPassFail;
	UserService userService = new UserService();
	User user = new User();
	
	@Override
	public void showMenu() {
		System.out.println("Welcome to the Bank");
		System.out.println("1. Log In");
		System.out.println("2. Sign Up");
		System.out.println("0. Quit");
	}
	
	@Override
	public View process() {
		switch (inputValue) {
			case 1:
				Boolean loggedIn = userService.login();
				if (loggedIn == true) {
					return new LoggedInMenu();
				} else {
					return new MainMenu();
				}
			case 2:
				return userService.signUp();
			case 0:
				return null;
		}
		return null;

	}
	
	@Override
	public void getUserInput() {
		this.inputValue = InputUtil.getIntChoice(2);
	}

}
