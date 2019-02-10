package project0;

import project0.views.MainMenu;
import project0.views.View;

public class Launcher {
	public static void main(String[] args) {
		View view = new MainMenu();
		do {
			view.showMenu();
			view.getUserInput();
			view = view.process();
		} while(view != null);
	}
}
