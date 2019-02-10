DROP TABLE UserSavingsAccount;
DROP TABLE UserCheckingAccount;
DROP TABLE Transactions;
DROP TABLE SavingsAccount;
DROP TABLE CheckingAccount;
DROP TABLE UserAccount;

CREATE TABLE UserAccount (
	uID SERIAL PRIMARY KEY,
	email VARCHAR(30) NOT NULL UNIQUE,
	password VARCHAR(30) NOT NULL,
	fName VARCHAR(25) NOT NULL,
	lName VARCHAR(25) NOT NULL,
	isEmployee BOOLEAN NOT NULL,
	isAdmin BOOLEAN NOT NULL
);

CREATE TABLE CheckingAccount (
	cID SERIAL PRIMARY KEY,
	balance FLOAT NOT NULL,
	currency VARCHAR(20) NOT NULL,
	frozen BOOLEAN NOT NULL
);

CREATE TABLE SavingsAccount (
	sID SERIAL PRIMARY KEY,
	balance FLOAT NOT NULL,
	currency VARCHAR(20) NOT NULL,
	openedDate DATE NOT NULL,
	lastInterestDate DATE NOT NULL,
	interestAmount FLOAT NOT NULL,
	interestType VARCHAR NOT NULL
);

CREATE TABLE Transactions (
	transactionID SERIAL PRIMARY KEY,
	cID INTEGER REFERENCES CheckingAccount(cID) NOT NULL,
	dateTime DATE NOT NULL,
	amount FLOAT NOT NULL
);

CREATE TABLE UserCheckingAccount (
	uID INTEGER REFERENCES UserAccount(uID) NOT NULL,
	cID INTEGER REFERENCES CheckingAccount(cID) NOT NULL,
	isChild BOOLEAN
);

CREATE TABLE UserSavingsAccount (
	uID INTEGER REFERENCES UserAccount(uID) NOT NULL,
	sID INTEGER REFERENCES SavingsAccount(sID) NOT NULL,
	isChild BOOLEAN
);

