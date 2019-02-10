package project0.services;

import project0.daos.AccountsDao;
import project0.daos.UserDao;
import project0.models.User;
import project0.util.InputUtil;

public class AccountsService {
	private static AccountsDao aDao = new AccountsDao();
	private static UserDao uDao = new UserDao();
	User user = UserService.getUser();
	
	public void viewMyAccounts(User user) {
		aDao.viewAccounts(user);
	}
	
	public void openAccount(User user) {
		int accountType;
		String tcurrency = "US Dollar";
		int currencyChoice;
		double intAmt;
		String intType;
		int intTypeChoice;
		
		System.out.println("Would you like to open a checkins or saving account?");
		System.out.println(" 1: Checking");
		System.out.println(" 2: Savings");
		System.out.println(" 0: Return");
		accountType = InputUtil.getIntChoice(2);
		
		System.out.println("We currently support the following currencies: ");
		System.out.println(" 1: US Dollar");
		System.out.println(" 2: European Euro");
		System.out.println(" 3: Chinese Renminbi");
		System.out.println(" 4: Indian Rupee");
		System.out.println(" 0: Return");
		System.out.println("Please enter your preferred currency for this account: ");
		currencyChoice = InputUtil.getIntChoice(4);
		
		switch(currencyChoice) {
			case 1:
				tcurrency = "US Dollar"; break;
			case 2:
				tcurrency = "European Euro"; break;
			case 3:
				tcurrency = "Chinese Renminbi"; break;
			case 4:
				tcurrency = "Indian Rupee"; break;
			case 0:
				return;
			default:
				return;
		}
		
		switch(accountType) {
			case 1:
				aDao.openCheckingAccount(user, tcurrency);
				System.out.println("Congratulations on your new Checking Account " + user.getfName() + "!");
				break;
			case 2:
				System.out.println("What type of interest plan?");
				System.out.println(" 1: Yearly @ 10%");
				System.out.println(" 2: Semi-Annual @ 4.5%");
				System.out.println(" 3: Quarterly @ 2%");
				System.out.println(" 0: Return");
				intTypeChoice = InputUtil.getIntChoice(3);
				switch(intTypeChoice) {
					case 1:
						intType = "Y"; intAmt = .1;
						break;
					case 2:
						intType = "S"; intAmt = .045;
						break;
					case 3:
						intType = "Q"; intAmt = .02;
						break;
					default:
						return;
				}
			
				aDao.openSavingsAccount(user, tcurrency, intAmt, intType);
				System.out.println("Congratulations on your new Savings Account " + user.getfName() + "!");
				break;
			case 0:
				return;
			default:
				return;
		}
	}
	
	public void withDepo() {
		int checkSavingChoice;
		int withDeposChoice;
		int act;
		double amt;
		Boolean userOwnsAccount;
		Boolean withdrawalIsLegal;
		Boolean continueOrNot;
		
		// User decides if they want to use a checking or savings account
		System.out.println("Checking or Savings?");
		System.out.println(" 1: Checking");
		System.out.println(" 2: Savings");
		System.out.println(" 0: Return");
		checkSavingChoice = InputUtil.getIntChoice(2);
		
		// User decides if they want to withdrawal or deposit from the account
		System.out.println("Withdrawal or Deposit?");
		System.out.println(" 1: Withdrawal");
		System.out.println(" 2: Deposit");
		System.out.println(" 0: Return");
		withDeposChoice = InputUtil.getIntChoice(2);
		
		// Checks if they chose checking or savings town
		switch (checkSavingChoice) {
			// Checking Account Option
			case 1:
				// Checks is they chose withdrawal or deposit
				switch (withDeposChoice) {
					// Withdrawal Option
					case 1:
						// Displays all of the user's Checking Accounts
						aDao.viewCAccounts(user);
						
						// User decides which account to Withdrawal from
						System.out.println("Which account would you like to withdrawal from?");
						act = InputUtil.getIntChoice(2_147_483_647);
						
						// Checks if the user owns the account
						userOwnsAccount = aDao.checkCheckBelongsUser(user, act);
						
						// If they don't own the account, they resubmit the account number until it's one they own
						while(userOwnsAccount == false) {
							System.out.println("You don't own this account.");
							aDao.viewCAccounts(user);
							System.out.println("Which account would you like to withdrawal from?");
							act = InputUtil.getIntChoice(2_147_483_647);
							userOwnsAccount = aDao.checkCheckBelongsUser(user, act);
						}
						
						// User decides how much money they want to Withdrawal
						System.out.println("How much money would you like to withdrawal?");
						amt = InputUtil.getDouble();
						
						// Checks if this amount will leave them with a positive remaining balance (or negative)
						withdrawalIsLegal = aDao.checkCheckActWithdrawalValue(act, amt);
						// If it does, proceed with the Withdrawal
						if(withdrawalIsLegal == true) {
							aDao.withdrawalChecking(act, amt);
							System.out.println("You have withdrawn " + amt + " from account " + act + ".");
						
						} else {
							// Otherwise, ask them if they want to go into the negative
							System.out.println("Withdrawaling this amount will put your account in the negative.");
							System.out.println("You will recieve fines.  Continue?");
							continueOrNot = InputUtil.getBooleanChoice();
							// If they want to go into the negative, proceed with Withdrawal
							if(continueOrNot == true) {
								aDao.withdrawalChecking(act, amt);
								System.out.println("You have withdrawn " + amt + " from account " + act + ".");
							}
						}
						break;
						
					// Deposit Option
					case 2:
						// Displays all of the user's checking accounts
						aDao.viewCAccounts(user);
						
						// User decides which account to Deposit into
						System.out.println("Which account would you like to deposit into?");
						act = InputUtil.getIntChoice(2_147_483_647);
						
						// Checks if the user owns the account
						userOwnsAccount = aDao.checkCheckBelongsUser(user, act);
						// If they don't, throws them into a loop until they chose an account they own
						while(userOwnsAccount == false) {
							System.out.println("You don't own this account.");
							aDao.viewCAccounts(user);
							System.out.println("Which account would you like to deposit into?");
							act = InputUtil.getIntChoice(2_147_483_647);
							userOwnsAccount = aDao.checkCheckBelongsUser(user, act);
						}
						// User decides how much money they want to deposit
						System.out.println("How much money would you like to deposit?");
						amt = InputUtil.getDouble();
						// Proceed to deposit
						aDao.depositChecking(act, amt);
						System.out.println("You have deposited " + amt + " into account " + act + ".");
						break;
					case 0:
						return;
					default:
						return;
				}
				break;
			
			// Savings Account Option
			case 2:
				// Checks is they chose withdrawal or deposit
				switch (withDeposChoice) {
					// Withdrawal Option
					case 1:
						// Displays all of the user's Savings Accounts
						aDao.viewSAccounts(user);
						
						// User decides which account they want to Withdrawal from
						System.out.println("Which account would you like to withdrawal from?");
						act = InputUtil.getIntChoice(2_147_483_647);
						
						// Checks if the user owns the account
						userOwnsAccount = aDao.checkSavingsBelongsUser(user, act);
						// If they don't own the account, throws them through a loop until they choose an account they own
						while(userOwnsAccount == false) {
							System.out.println("You don't own this account.");
							aDao.viewSAccounts(user);
							System.out.println("Which account would you like to withdrawal from?");
							act = InputUtil.getIntChoice(2_147_483_647);
							userOwnsAccount = aDao.checkSavingsBelongsUser(user, act);
						}
						// User decides how much they want to withdrawal
						System.out.println("How much money would you like to withdrawal?");
						amt = InputUtil.getDouble();
						// Checks if this amount will leave them with a positive amount
						withdrawalIsLegal = aDao.checkSavingsActWithdrawalValue(act, amt);
						if(withdrawalIsLegal == true) {
							// If so, they proceed to withdrawal that amount
							aDao.withdrawalSavings(act, amt);
							System.out.println("You have withdrawn " + amt + " from account " + act + ".");
						} else {
							// If not, they will get a warning about SEVERE fines
							System.out.println("Withdrawaling this amount will put your account in the negative.");
							System.out.println("You will recieve SEVERE fines.  Continue?");
							// User decides if they want to continue anyways
							continueOrNot = InputUtil.getBooleanChoice();
							// If so, they withdrawal the amount
							if(continueOrNot == true) {
								aDao.withdrawalSavings(act, amt);
								System.out.println("You have withdrawn " + amt + " from account " + act + ".");
							}
						}
						break;
						
					// Deposit Option
					case 2:
						// Displays all of the user's Savings Accounts
						aDao.viewSAccounts(user);
						// User decides which account to deposit into
						System.out.println("Which account would you like to deposit into?");
						act = InputUtil.getIntChoice(2_147_483_647);
						
						// Checks if the user owns the account
						userOwnsAccount = aDao.checkSavingsBelongsUser(user, act);
						// If they don't, throws them into a loop until they choose an account they own
						while(userOwnsAccount == false) {
							System.out.println("You don't own this account.");
							aDao.viewSAccounts(user);
							System.out.println("Which account would you like to deposit into?");
							act = InputUtil.getIntChoice(2_147_483_647);
							userOwnsAccount = aDao.checkSavingsBelongsUser(user, act);
						}
						
						// User decides how much they want to deposit
						System.out.println("How much money would you like to deposit?");
						amt = InputUtil.getDouble();
						// Proceeds to deposit the amount
						aDao.depositSavings(act, amt);
						System.out.println("You have deposited " + amt + " into account " + act + ".");
						break;
					case 0:
						return;
					default:
						return;
				}
				break;
		case 0:
			return;
		default:
			return;
		}
	}
	
	public void transferFunds() {
		String e;
		String p;
		int checkSavingChoice;
		int act1;
		int act2;
		double amt;
		Boolean empOwnsCheckingAccount;
		Boolean empOwnsSavingsAccount;
		Boolean user1OwnsCheckingAccount;
		Boolean user1OwnsSavingsAccount;
		Boolean user2OwnsCheckingAccount;
		Boolean user2OwnsSavingsAccount;
		Boolean legalWithdrawal;
		Boolean continueYesNo;
		Boolean validEmail;
		User user1 = new User();
		User user2 = new User();
		
		if(user.getIsEmployee() == false && user.getIsAdmin() == false) {
			System.out.println("Withdrawal from Checking or Savings?");
			System.out.println(" 1: Checking");
			System.out.println(" 2: Savings");
			System.out.println(" 0: Return");
			checkSavingChoice = InputUtil.getIntChoice(2);
			
			switch(checkSavingChoice) {
				case 1:
					aDao.viewCAccounts(user);
					System.out.println("Select an account to withdrawal from.");
					act1 = InputUtil.getIntChoice(2_147_483_647);
					user1OwnsCheckingAccount = aDao.checkCheckBelongsUser(user, act1);
					while(user1OwnsCheckingAccount == false) {
						System.out.println("You do not own this account.");
						aDao.viewCAccounts(user);
						System.out.println("Select a different account to withdrawal from.");
						act1 = InputUtil.getIntChoice(2_147_483_647);
						user1OwnsCheckingAccount = aDao.checkCheckBelongsUser(user, act1);
					}
					
					System.out.println("Deposit into Checking or Savings?");
					System.out.println(" 1: Checking");
					System.out.println(" 2: Savings");
					System.out.println(" 0: Return");
					checkSavingChoice = InputUtil.getIntChoice(2);
					switch(checkSavingChoice) {
						case 1:
							System.out.println("Select an account to deposit into.");
							aDao.viewCAccounts(user);
							act2 = InputUtil.getIntChoice(2_147_483_647);
							user2OwnsCheckingAccount = aDao.checkCheckBelongsUser(user, act2);
							while(user2OwnsCheckingAccount == false) {
								System.out.println("You do not own this account.");
								aDao.viewCAccounts(user);
								System.out.println("Select a different account to deposit into.");
								act2 = InputUtil.getIntChoice(2_147_483_647);
								user2OwnsCheckingAccount = aDao.checkCheckBelongsUser(user, act2);
							}
							
							System.out.println("How much do you want to transfer?");
							amt = InputUtil.getDouble();
							legalWithdrawal = aDao.checkCheckActWithdrawalValue(act1, amt);
							if(legalWithdrawal == true) {
								aDao.withdrawalChecking(act1, amt);
								aDao.depositChecking(act2, amt);
							} else {
								System.out.println("This transfer will make your account go negative.  Continue?");
								continueYesNo = InputUtil.getBooleanChoice();
								if(continueYesNo == true) {
									aDao.withdrawalChecking(act1, amt);
									aDao.depositChecking(act2, amt);
								}
							}
							break;
						case 2:
							System.out.println("Select an account to deposit into.");
							aDao.viewSAccounts(user);
							act2 = InputUtil.getIntChoice(2_147_483_647);
							user2OwnsSavingsAccount = aDao.checkSavingsBelongsUser(user, act2);
							while(user2OwnsSavingsAccount == false) {
								System.out.println("You do not own this account.");
								aDao.viewSAccounts(user);
								System.out.println("Select a different account to deposit into.");
								act2 = InputUtil.getIntChoice(2_147_483_647);
								user2OwnsSavingsAccount = aDao.checkSavingsBelongsUser(user, act2);
							}
							
							System.out.println("How much do you want to transfer?");
							amt = InputUtil.getDouble();
							legalWithdrawal = aDao.checkCheckActWithdrawalValue(act1, amt);
							if(legalWithdrawal == true) {
								aDao.withdrawalChecking(act1, amt);
								aDao.depositSavings(act2, amt);
							} else {
								System.out.println("This transfer will make your account go negative.  Continue?");
								continueYesNo = InputUtil.getBooleanChoice();
								if(continueYesNo == true) {
									aDao.withdrawalChecking(act1, amt);
									aDao.depositSavings(act2, amt);
								}
							}
							break;
						case 0:
							return;
						default:
							return;
					}
					break;
				case 2:
					aDao.viewSAccounts(user);
					System.out.println("Select an account to withdrawal from.");
					act1 = InputUtil.getIntChoice(2_147_483_647);
					user1OwnsSavingsAccount = aDao.checkSavingsBelongsUser(user, act1);
					while(user1OwnsSavingsAccount == false) {
						System.out.println("You do not own this account.");
						aDao.viewSAccounts(user);
						System.out.println("Select a different account to withdrawal from.");
						act1 = InputUtil.getIntChoice(2_147_483_647);
						user1OwnsSavingsAccount = aDao.checkSavingsBelongsUser(user, act1);
					}
					
					System.out.println("Deposit into Checking or Savings?");
					System.out.println(" 1: Checking");
					System.out.println(" 2: Savings");
					System.out.println(" 0: Return");
					checkSavingChoice = InputUtil.getIntChoice(2);
					switch(checkSavingChoice) {
						case 1:
							System.out.println("Select an account to deposit into.");
							aDao.viewCAccounts(user);
							act2 = InputUtil.getIntChoice(2_147_483_647);
							user2OwnsCheckingAccount = aDao.checkCheckBelongsUser(user, act2);
							while(user2OwnsCheckingAccount == false) {
								System.out.println("You do not own this account.");
								aDao.viewCAccounts(user);
								System.out.println("Select a different account to deposit into.");
								act2 = InputUtil.getIntChoice(2_147_483_647);
								user2OwnsCheckingAccount = aDao.checkCheckBelongsUser(user, act2);
							}
							
							System.out.println("How much do you want to transfer?");
							amt = InputUtil.getDouble();
							legalWithdrawal = aDao.checkSavingsActWithdrawalValue(act1, amt);
							if(legalWithdrawal == true) {
								aDao.withdrawalSavings(act1, amt);
								aDao.depositChecking(act2, amt);
							} else {
								System.out.println("This transfer will make your account go negative.  Continue?");
								continueYesNo = InputUtil.getBooleanChoice();
								if(continueYesNo == true) {
									aDao.withdrawalSavings(act1, amt);
									aDao.depositChecking(act2, amt);
								}
							}
							break;
						case 2:
							System.out.println("Select an account to deposit into.");
							aDao.viewSAccounts(user);
							act2 = InputUtil.getIntChoice(2_147_483_647);
							user2OwnsSavingsAccount = aDao.checkSavingsBelongsUser(user, act2);
							while(user2OwnsSavingsAccount == false) {
								System.out.println("You do not own this account.");
								aDao.viewSAccounts(user);
								System.out.println("Select a different account to deposit into.");
								act2 = InputUtil.getIntChoice(2_147_483_647);
								user2OwnsSavingsAccount = aDao.checkSavingsBelongsUser(user, act2);
							}
							
							System.out.println("How much do you want to transfer?");
							amt = InputUtil.getDouble();
							legalWithdrawal = aDao.checkSavingsActWithdrawalValue(act1, amt);
							if(legalWithdrawal == true) {
								aDao.withdrawalSavings(act1, amt);
								aDao.depositSavings(act2, amt);
							} else {
								System.out.println("This transfer will make your account go negative.  Continue?");
								continueYesNo = InputUtil.getBooleanChoice();
								if(continueYesNo == true) {
									aDao.withdrawalSavings(act1, amt);
									aDao.depositSavings(act2, amt);
								}
							}
							break;
						case 0: return;
						default: return;
					}
					break;
				case 0: return;
				default: return;
			}
			
			
		} else {
			System.out.println("The user that is sending money must login first.");
			System.out.print("Email: ");
			e = InputUtil.getStringInput(30);
			
			validEmail = uDao.checkIfEmailExists(e);
			while(!validEmail) {
				System.out.println("The email doesn't exist in the database.");
				System.out.print("Please enter a valid email: ");
				e = InputUtil.getStringInput(30);
				validEmail = uDao.checkIfEmailExists(e);
			}
			
			System.out.print("Password: ");
			p = InputUtil.getStringInput(30);
			
			user1 = uDao.logIn(user1, e, p);
			
			while(user1.getId() == 0) {
				System.out.print("Incorrect Password.  Please enter a new password: ");
				p = InputUtil.getStringInput(30);
				user1 = uDao.logIn(user1, e, p);
			}
			
			System.out.println("Checking or Savings?");
			System.out.println(" 1: Checking");
			System.out.println(" 2: Savings");
			System.out.println(" 0: Return");
			checkSavingChoice = InputUtil.getIntChoice(2);
			
			switch(checkSavingChoice) {
				case 1:
					aDao.viewCAccounts(user1);
					System.out.println("Select an account to withdrawal from.");
					act1 = InputUtil.getIntChoice(2_147_483_647);
					empOwnsCheckingAccount = aDao.checkCheckBelongsUser(user, act1);
					user1OwnsCheckingAccount = aDao.checkCheckBelongsUser(user1, act1);
					
					while(empOwnsCheckingAccount == true || user1OwnsCheckingAccount == false) {
						while(empOwnsCheckingAccount == true) {
							System.out.println("Employees aren't allowed to transfer with their own account.");
							aDao.viewCAccounts(user1);
							System.out.println("Select a different account to withdrawal from.");
							act1 = InputUtil.getIntChoice(2_147_483_647);
							empOwnsCheckingAccount = aDao.checkCheckBelongsUser(user, act1);
							user1OwnsCheckingAccount = aDao.checkCheckBelongsUser(user1, act1);
						}
						
						while(user1OwnsCheckingAccount == false) {
							System.out.println("You don't own this account.");
							aDao.viewCAccounts(user1);
							System.out.println("Select a different account to withdrawal from.");
							act1 = InputUtil.getIntChoice(2_147_483_647);
							empOwnsCheckingAccount = aDao.checkCheckBelongsUser(user, act1);
							user1OwnsCheckingAccount = aDao.checkCheckBelongsUser(user1, act1);
						}
					}
					
					System.out.println("The user that is recieving money must login now.");
					System.out.print("Email: ");
					e = InputUtil.getStringInput(30);
					
					validEmail = uDao.checkIfEmailExists(e);
					while(!validEmail) {
						System.out.println("The email doesn't exist in the database.");
						System.out.print("Please enter a valid email: ");
						e = InputUtil.getStringInput(30);
						validEmail = uDao.checkIfEmailExists(e);
					}
					
					System.out.print("Password: ");
					p = InputUtil.getStringInput(30);
					
					user2 = uDao.logIn(user2, e, p);
					
					while(user2.getId() == 0) {
						System.out.print("Incorrect Password.  Please enter a new password: ");
						p = InputUtil.getStringInput(30);
						user2 = uDao.logIn(user2, e, p);
					}
					
					System.out.println("Checking or Savings Account?");
					System.out.println(" 1: Checking");
					System.out.println(" 2: Savings Account");
					System.out.println(" 0: Return");
					checkSavingChoice = InputUtil.getIntChoice(2);
					
					switch(checkSavingChoice) {
						case 1:
							aDao.viewCAccounts(user2);
							System.out.println("Select an account to deposit into.");
							act2 = InputUtil.getIntChoice(2_147_483_647);
							
							empOwnsCheckingAccount = aDao.checkCheckBelongsUser(user, act2);
							user2OwnsCheckingAccount = aDao.checkCheckBelongsUser(user2, act2);
							while(empOwnsCheckingAccount == false || user2OwnsCheckingAccount == false) {
								while(empOwnsCheckingAccount == false) {
									System.out.println("Employees aren't allowed to transfer with their own account.");
									aDao.viewCAccounts(user2);
									System.out.println("Select a different account to withdrawal from.");
									act2 = InputUtil.getIntChoice(2_147_483_647);
									empOwnsCheckingAccount = aDao.checkCheckBelongsUser(user, act2);
									user2OwnsCheckingAccount = aDao.checkCheckBelongsUser(user2, act2);
								}
								
								while(user2OwnsCheckingAccount == false) {
									System.out.println("You don't own this account.");
									aDao.viewCAccounts(user2);
									System.out.println("Select a different account to withdrawal from.");
									act2 = InputUtil.getIntChoice(2_147_483_647);
									empOwnsCheckingAccount = aDao.checkCheckBelongsUser(user, act2);
									user2OwnsCheckingAccount = aDao.checkCheckBelongsUser(user2, act2);
								}
							}
							
							System.out.println("How much do you want to transfer?");
							amt = InputUtil.getDouble();
							

							legalWithdrawal = aDao.checkCheckActWithdrawalValue(act1, amt);
							if(legalWithdrawal == true) {
								aDao.withdrawalChecking(act1, amt);
								aDao.depositChecking(act2, amt);
							} else {
								System.out.println("This transfer will make your account go negative.  Continue?");
								continueYesNo = InputUtil.getBooleanChoice();
								if(continueYesNo == true) {
									aDao.withdrawalChecking(act1, amt);
									aDao.depositChecking(act2, amt);
								}
							}
							break;
						case 2:
							aDao.viewSAccounts(user2);
							System.out.println("Select an account to deposit into.");
							act2 = InputUtil.getIntChoice(2_147_483_647);
							
							empOwnsSavingsAccount = aDao.checkCheckBelongsUser(user, act2);
							user2OwnsSavingsAccount = aDao.checkCheckBelongsUser(user2, act2);
							while(empOwnsSavingsAccount == false || user2OwnsSavingsAccount == false) {
								while(empOwnsSavingsAccount == false) {
									System.out.println("Employees aren't allowed to transfer with their own account.");
									aDao.viewSAccounts(user2);
									System.out.println("Select a different account to withdrawal from.");
									act2 = InputUtil.getIntChoice(2_147_483_647);
									empOwnsSavingsAccount = aDao.checkSavingsBelongsUser(user, act2);
									user2OwnsSavingsAccount = aDao.checkSavingsBelongsUser(user2, act2);
								}
								
								while(user2OwnsSavingsAccount == false) {
									System.out.println("You don't own this account.");
									aDao.viewSAccounts(user2);
									System.out.println("Select a different account to withdrawal from.");
									act2 = InputUtil.getIntChoice(2_147_483_647);
									empOwnsSavingsAccount = aDao.checkSavingsBelongsUser(user, act2);
									user2OwnsSavingsAccount = aDao.checkSavingsBelongsUser(user2, act2);
								}
							}
							
							System.out.println("How much do you want to transfer?");
							amt = InputUtil.getDouble();

							legalWithdrawal = aDao.checkCheckActWithdrawalValue(act1, amt);
							if(legalWithdrawal == true) {
								aDao.withdrawalChecking(act1, amt);
								aDao.depositSavings(act2, amt);
							} else {
								System.out.println("This transfer will make your account go negative.  Continue?");
								continueYesNo = InputUtil.getBooleanChoice();
								if(continueYesNo == true) {
									aDao.withdrawalChecking(act1, amt);
									aDao.depositSavings(act2, amt);
								}
							}
							break;
						case 0:
							return;
						default:
							return;
					}
					break;
				case 2:
					aDao.viewSAccounts(user1);
					System.out.println("Select an account to withdrawal from.");
					act1 = InputUtil.getIntChoice(2_147_483_647);
					empOwnsSavingsAccount = aDao.checkSavingsBelongsUser(user, act1);
					user1OwnsSavingsAccount = aDao.checkSavingsBelongsUser(user1, act1);
					
					while(empOwnsSavingsAccount == true || user1OwnsSavingsAccount == false) {
						while(empOwnsSavingsAccount == true) {
							System.out.println("Employees aren't allowed to transfer with their own account.");
							aDao.viewSAccounts(user1);
							System.out.println("Select a different account to withdrawal from.");
							act1 = InputUtil.getIntChoice(2_147_483_647);
							empOwnsSavingsAccount = aDao.checkSavingsBelongsUser(user, act1);
							user1OwnsSavingsAccount = aDao.checkSavingsBelongsUser(user1, act1);
						}
						
						while(user1OwnsSavingsAccount == false) {
							System.out.println("You don't own this account.");
							aDao.viewSAccounts(user1);
							System.out.println("Select a different account to withdrawal from.");
							act1 = InputUtil.getIntChoice(2_147_483_647);
							empOwnsSavingsAccount = aDao.checkSavingsBelongsUser(user, act1);
							user1OwnsSavingsAccount = aDao.checkSavingsBelongsUser(user1, act1);
						}
					}
					
					System.out.println("The user that is recieving money must login now.");
					System.out.print("Email: ");
					e = InputUtil.getStringInput(30);
					
					validEmail = uDao.checkIfEmailExists(e);
					while(!validEmail) {
						System.out.println("The email doesn't exist in the database.");
						System.out.print("Please enter a valid email: ");
						e = InputUtil.getStringInput(30);
						validEmail = uDao.checkIfEmailExists(e);
					}
					
					System.out.print("Password: ");
					p = InputUtil.getStringInput(30);
					
					user2 = uDao.logIn(user2, e, p);
					
					while(user2.getId() == 0) {
						System.out.print("Incorrect Password.  Please enter a new password: ");
						p = InputUtil.getStringInput(30);
						user2 = uDao.logIn(user2, e, p);
					}
					
					System.out.println("Checking or Savings Account?");
					System.out.println(" 1: Checking");
					System.out.println(" 2: Savings Account");
					System.out.println(" 0: Return");
					checkSavingChoice = InputUtil.getIntChoice(2);
					
					switch(checkSavingChoice) {
						case 1:
							aDao.viewCAccounts(user2);
							System.out.println("Select an account to deposit into.");
							act2 = InputUtil.getIntChoice(2_147_483_647);
							
							empOwnsCheckingAccount = aDao.checkCheckBelongsUser(user, act2);
							user2OwnsCheckingAccount = aDao.checkCheckBelongsUser(user2, act2);
							while(empOwnsCheckingAccount == false || user2OwnsCheckingAccount == false) {
								while(empOwnsCheckingAccount == false) {
									System.out.println("Employees aren't allowed to transfer with their own account.");
									aDao.viewCAccounts(user2);
									System.out.println("Select a different account to withdrawal from.");
									act2 = InputUtil.getIntChoice(2_147_483_647);
									empOwnsCheckingAccount = aDao.checkCheckBelongsUser(user, act2);
									user2OwnsCheckingAccount = aDao.checkCheckBelongsUser(user2, act2);
								}
								
								while(user2OwnsCheckingAccount == false) {
									System.out.println("You don't own this account.");
									aDao.viewCAccounts(user2);
									System.out.println("Select a different account to withdrawal from.");
									act2 = InputUtil.getIntChoice(2_147_483_647);
									empOwnsCheckingAccount = aDao.checkCheckBelongsUser(user, act2);
									user2OwnsCheckingAccount = aDao.checkCheckBelongsUser(user2, act2);
								}
							}
							
							System.out.println("How much do you want to transfer?");
							amt = InputUtil.getDouble();
							

							legalWithdrawal = aDao.checkSavingsActWithdrawalValue(act1, amt);
							if(legalWithdrawal == true) {
								aDao.withdrawalSavings(act1, amt);
								aDao.depositChecking(act2, amt);
							} else {
								System.out.println("This transfer will make your account go negative.  Continue?");
								continueYesNo = InputUtil.getBooleanChoice();
								if(continueYesNo == true) {
									aDao.withdrawalSavings(act1, amt);
									aDao.depositChecking(act2, amt);
								}
							}
							break;
						case 2:
							aDao.viewSAccounts(user2);
							System.out.println("Select an account to deposit into.");
							act2 = InputUtil.getIntChoice(2_147_483_647);
							
							empOwnsSavingsAccount = aDao.checkCheckBelongsUser(user, act2);
							user2OwnsSavingsAccount = aDao.checkCheckBelongsUser(user2, act2);
							while(empOwnsSavingsAccount == false || user2OwnsSavingsAccount == false) {
								while(empOwnsSavingsAccount == false) {
									System.out.println("Employees aren't allowed to transfer with their own account.");
									aDao.viewSAccounts(user2);
									System.out.println("Select a different account to withdrawal from.");
									act2 = InputUtil.getIntChoice(2_147_483_647);
									empOwnsSavingsAccount = aDao.checkSavingsBelongsUser(user, act2);
									user2OwnsSavingsAccount = aDao.checkSavingsBelongsUser(user2, act2);
								}
								
								while(user2OwnsSavingsAccount == false) {
									System.out.println("You don't own this account.");
									aDao.viewSAccounts(user2);
									System.out.println("Select a different account to withdrawal from.");
									act2 = InputUtil.getIntChoice(2_147_483_647);
									empOwnsSavingsAccount = aDao.checkSavingsBelongsUser(user, act2);
									user2OwnsSavingsAccount = aDao.checkSavingsBelongsUser(user2, act2);
								}
							}
							
							System.out.println("How much do you want to transfer?");
							amt = InputUtil.getDouble();

							legalWithdrawal = aDao.checkSavingsActWithdrawalValue(act1, amt);
							if(legalWithdrawal == true) {
								aDao.withdrawalSavings(act1, amt);
								aDao.depositSavings(act2, amt);
							} else {
								System.out.println("This transfer will make your account go negative.  Continue?");
								continueYesNo = InputUtil.getBooleanChoice();
								if(continueYesNo == true) {
									aDao.withdrawalSavings(act1, amt);
									aDao.depositSavings(act2, amt);
								}
							}
							break;
						case 0:
							return;
						default:
							return;
					}
					break;
				case 0:
					return;
				default:
					return;
			}
		}
	}
	
	public void viewTransactions() {
		int act;
		
		aDao.viewCAccounts(user);
		System.out.println("Please select an account to view.");
		act = InputUtil.getIntChoice(2_147_483_647);
		
		aDao.viewTransactions(act);
	}
	
	public void addUserToAccount() {
		int checkSaveChoice;
		int act;
		int parentChildChoice;
		String u1email;
		String u1password;
		Boolean cBelongsU;
		Boolean sBelongsU;
		Boolean u1emailExists;
		User user1 = new User();
		
		System.out.println("Would you like to add the account to one of your Checking or Savings accounts?");
		System.out.println(" 1: Checking");
		System.out.println(" 2: Savings");
		System.out.println(" 0: Return");
		checkSaveChoice = InputUtil.getIntChoice(2);
		
		switch(checkSaveChoice) {
			case 1:
				aDao.viewCAccounts(user);
				System.out.println("Please select one of your accounts to add a new user to.");
				act = InputUtil.getIntChoice(2_147_483_647);
				cBelongsU = aDao.checkCheckBelongsUser(user, act);
				
				while(cBelongsU == false) {
					System.out.println("You don't own this account.");
					aDao.viewCAccounts(user);
					System.out.println("Please select one of your accounts to add a new user to.");
					act = InputUtil.getIntChoice(2_147_483_647);
					cBelongsU = aDao.checkCheckBelongsUser(user, act);
				}
				
				System.out.println("The user you want to add must login now.");
				System.out.print("Please enter an email: ");
				u1email = InputUtil.getStringInput(30);
				u1emailExists = uDao.checkIfEmailExists(u1email);
				
				while(u1emailExists == false) {
					System.out.println("That email doesn't exist in the database.");
					System.out.print("Please enter a valid email: ");
					u1email = InputUtil.getStringInput(30);
					u1emailExists = uDao.checkIfEmailExists(u1email);
				}
				
				System.out.print("Please enter a password: ");
				u1password = InputUtil.getStringInput(30);
				
				user1 = uDao.logIn(user1, u1email, u1password);
				
				while(user1.getId() == 0) {
					System.out.print("Incorrect password.  Please enter another password: ");
					u1password = InputUtil.getStringInput(30);
					user1 = uDao.logIn(user1, u1email, u1password);
				}
				
				System.out.println("Is the new user a parent or child user?");
				System.out.println(" 1: Parent");
				System.out.println(" 2: Child");
				System.out.println(" 0: Return");
				parentChildChoice = InputUtil.getIntChoice(2);
				switch(parentChildChoice) {
					case 1:
						aDao.joinCheckingAccount(user1, act, false);
						break;
					case 2:
						aDao.joinCheckingAccount(user1, act, true);
						break;
					case 0:
						return;
					default:
						return;
				}
				break;
			case 2:
				aDao.viewSAccounts(user);
				System.out.println("Please select one of your accounts to add a new user to.");
				act = InputUtil.getIntChoice(2_147_483_647);
				sBelongsU = aDao.checkSavingsBelongsUser(user, act);
				
				while(sBelongsU == false) {
					System.out.println("You don't own this account.");
					aDao.viewSAccounts(user);
					System.out.println("Please select one of your accounts to add a new user to.");
					act = InputUtil.getIntChoice(2_147_483_647);
					sBelongsU = aDao.checkSavingsBelongsUser(user, act);
				}
				
				System.out.println("The user you want to add must login now.");
				System.out.print("Please enter an email: ");
				u1email = InputUtil.getStringInput(30);
				u1emailExists = uDao.checkIfEmailExists(u1email);
				
				while(u1emailExists == false) {
					System.out.println("That email doesn't exist in the database.");
					System.out.print("Please enter a valid email: ");
					u1email = InputUtil.getStringInput(30);
					u1emailExists = uDao.checkIfEmailExists(u1email);
				}
				
				System.out.print("Please enter a password: ");
				u1password = InputUtil.getStringInput(30);
				
				user1 = uDao.logIn(user1, u1email, u1password);
				
				while(user1.getId() == 0) {
					System.out.print("Incorrect password.  Please enter another password: ");
					u1password = InputUtil.getStringInput(30);
					user1 = uDao.logIn(user1, u1email, u1password);
				}
				
				System.out.println("Is the new user a parent or child user?");
				System.out.println(" 1: Parent");
				System.out.println(" 2: Child");
				System.out.println(" 0: Return");
				parentChildChoice = InputUtil.getIntChoice(2);
				switch(parentChildChoice) {
					case 1:
						aDao.joinSavingsAccount(user1, act, false);
						break;
					case 2:
						aDao.joinSavingsAccount(user1, act, true);
						break;
					case 0:
						return;
					default:
						return;
				}
				break;
			case 0:
				return;
			default:
				return;
		}
	}
	
	public void freezeUnfreeze() {
		Boolean frozen;
		int aid;
		int freezeUnfreeze;
		
		aDao.viewAllCAccounts();
		System.out.print("Choose an account to freeze / unfreeze: ");
		aid = InputUtil.getIntChoice(2_147_483_647);
		
		System.out.println("Would you like to freeze or unfreeze the account: ");
		System.out.println(" 1: Freeze");
		System.out.println(" 2: Unfreeze");
		System.out.println(" 0: Return");
		freezeUnfreeze = InputUtil.getIntChoice(2);
		
		switch(freezeUnfreeze) {
		case 1:
			frozen = true;
			break;
		case 2:
			frozen = false;
			break;
		case 0:
			return;
		default:
			return;
		}
		
		aDao.freezeUnfreezeAccount(aid, frozen);
		if(frozen) {
			System.out.println("Checking Account " + aid + " is now frozen.");
		} else {
			System.out.println("Checking Account " + aid + " is now unfrozen.");
		}
		
	}
	
	public void closeAccount() {
		int checkSavingChoice;
		int closingAccount;
		
		System.out.println("Checking or Savings Account?");
		System.out.println(" 1: Checking");
		System.out.println(" 2: Savings");
		System.out.println(" 0: Return");
		checkSavingChoice = InputUtil.getIntChoice(2);
		
		switch(checkSavingChoice) {
			case 1:
				aDao.viewAllCAccounts();
				System.out.println("Please select an account to close.");
				closingAccount = InputUtil.getIntChoice(2_147_483_647);
				aDao.deleteCheckingAccount(closingAccount);
				break;
			case 2:
				aDao.viewAllSAccounts();
				System.out.println("Please select an account to close.");
				closingAccount = InputUtil.getIntChoice(2_147_483_647);
				aDao.deleteSavingsAccount(closingAccount);
				break;
			case 0:
				return;
			default:
				return;
		}
	}
	
	public void makeEmp() {
		int userAct = 2;
		int changeEmp;
		
		uDao.viewAllUsers();
		System.out.print("Please select a user: ");
		userAct = InputUtil.getIntChoice(2_147_483_647);
		
		System.out.println("Make employee or dismiss employee?");
		System.out.println(" 1: Make Employee");
		System.out.println(" 2: Dismiss Employee");
		System.out.println(" 0: Return");
		changeEmp = InputUtil.getIntChoice(2);
		
		switch(changeEmp) {
			case 1:
				uDao.changeEmployeeStatus(userAct, true);
				break;
			case 2:
				uDao.changeEmployeeStatus(userAct, false);
				break;
			case 0:
				return;
			default:
				return;
		}
	}
	
	public void makeAdmin() {
		int userAct = 2;
		int changeAdmin;
		
		uDao.viewAllUsers();
		System.out.print("Please select a user: ");
		userAct = InputUtil.getIntChoice(2_147_483_647);
		
		System.out.println("Make employee or dismiss employee?");
		System.out.println(" 1: Make Employee");
		System.out.println(" 2: Dismiss Employee");
		System.out.println(" 0: Return");
		changeAdmin = InputUtil.getIntChoice(2);
		
		switch(changeAdmin) {
			case 1:
				uDao.changeAdminStatus(userAct, true);
				break;
			case 2:
				uDao.changeAdminStatus(userAct, false);
				break;
			case 0:
				return;
			default:
				return;
		}
	}
}