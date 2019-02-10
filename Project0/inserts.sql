-- Users
INSERT INTO UserAccount(email, password, fname, lname, isEmployee, isAdmin) VALUES('admin@admin.com', 'admin', 'Admin', 'User', true, true);
INSERT INTO UserAccount(email, password, fname, lname, isEmployee, isAdmin) VALUES('emp1@emp.com', 'emp1', 'First', 'Employee', true, false);
INSERT INTO UserAccount(email, password, fname, lname, isEmployee, isAdmin) VALUES('emp2@emp.com', 'emp2', 'Second', 'Employee', true, false);
INSERT INTO UserAccount(email, password, fname, lname, isEmployee, isAdmin) VALUES('user1@user.com', 'user1', 'First', 'User', false, false);
INSERT INTO UserAccount(email, password, fname, lname, isEmployee, isAdmin) VALUES('user2@user.com', 'user2', 'Second', 'User', false, false);
INSERT INTO UserAccount(email, password, fname, lname, isEmployee, isAdmin) VALUES('user3@user.com', 'user3', 'Third', 'User', false, false);
INSERT INTO UserAccount(email, password, fname, lname, isEmployee, isAdmin) VALUES('user4@user.com', 'user4', 'Fourth', 'User', false, false);
INSERT INTO UserAccount(email, password, fname, lname, isEmployee, isAdmin) VALUES('user5@user.com', 'user5', 'Fifth', 'User', false, false);

-- Checking Accounts
INSERT INTO CheckingAccount(balance, currency, frozen) VALUES(100000.00, 'US Dollar', false);
INSERT INTO CheckingAccount(balance, currency, frozen) VALUES(10000.00, 'US Dollar', false);
INSERT INTO CheckingAccount(balance, currency, frozen) VALUES(10000.00, 'US Dollar', false);
INSERT INTO CheckingAccount(balance, currency, frozen) VALUES(1000.00, 'US Dollar', false);
INSERT INTO CheckingAccount(balance, currency, frozen) VALUES(1000.00, 'US Dollar', false);
INSERT INTO CheckingAccount(balance, currency, frozen) VALUES(1000.00, 'US Dollar', false);
INSERT INTO CheckingAccount(balance, currency, frozen) VALUES(1000.00, 'US Dollar', false);
INSERT INTO CheckingAccount(balance, currency, frozen) VALUES(1000.00, 'US Dollar', false);

-- Savings Accounts
INSERT INTO SavingsAccount(balance, currency, openedDate, lastInterestDate, interestAmount, interestType) VALUES(100000, 'US Dollar', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, .1, 'Y');
INSERT INTO SavingsAccount(balance, currency, openedDate, lastInterestDate, interestAmount, interestType) VALUES(10000, 'US Dollar', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, .1, 'Y');
INSERT INTO SavingsAccount(balance, currency, openedDate, lastInterestDate, interestAmount, interestType) VALUES(10000, 'US Dollar', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, .045, 'S');
INSERT INTO SavingsAccount(balance, currency, openedDate, lastInterestDate, interestAmount, interestType) VALUES(1000, 'US Dollar', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, .1, 'Y');
INSERT INTO SavingsAccount(balance, currency, openedDate, lastInterestDate, interestAmount, interestType) VALUES(1000, 'US Dollar', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, .045, 'S');
INSERT INTO SavingsAccount(balance, currency, openedDate, lastInterestDate, interestAmount, interestType) VALUES(1000, 'US Dollar', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, .02, 'Q');
INSERT INTO SavingsAccount(balance, currency, openedDate, lastInterestDate, interestAmount, interestType) VALUES(1000, 'US Dollar', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, .1, 'Y');
INSERT INTO SavingsAccount(balance, currency, openedDate, lastInterestDate, interestAmount, interestType) VALUES(1000, 'US Dollar', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, .045, 'S');

-- Transactions
--INSERT INTO Transactions(cID, dateTime, amount) VALUES();

-- User - Checking Accounts
INSERT INTO UserCheckingAccount(uID, cID, isChild) VALUES(1, 1, false);
INSERT INTO UserCheckingAccount(uID, cID, isChild) VALUES(2, 2, false);
INSERT INTO UserCheckingAccount(uID, cID, isChild) VALUES(3, 3, false);
INSERT INTO UserCheckingAccount(uID, cID, isChild) VALUES(4, 4, false);
INSERT INTO UserCheckingAccount(uID, cID, isChild) VALUES(5, 5, false);
INSERT INTO UserCheckingAccount(uID, cID, isChild) VALUES(6, 6, false);
INSERT INTO UserCheckingAccount(uID, cID, isChild) VALUES(7, 7, false);
INSERT INTO UserCheckingAccount(uID, cID, isChild) VALUES(8, 8, false);

-- User - Savings Accounts
INSERT INTO UserSavingsAccount(uID, sID, isChild) VALUES(1, 1, false);
INSERT INTO UserSavingsAccount(uID, sID, isChild) VALUES(2, 2, false);
INSERT INTO UserSavingsAccount(uID, sID, isChild) VALUES(3, 3, false);
INSERT INTO UserSavingsAccount(uID, sID, isChild) VALUES(4, 4, false);
INSERT INTO UserSavingsAccount(uID, sID, isChild) VALUES(5, 5, false);
INSERT INTO UserSavingsAccount(uID, sID, isChild) VALUES(6, 6, false);
INSERT INTO UserSavingsAccount(uID, sID, isChild) VALUES(7, 7, false);
INSERT INTO UserSavingsAccount(uID, sID, isChild) VALUES(8, 8, false);