DROP ROLE customer;
CREATE ROLE customer WITH LOGIN PASSWORD 'customer';
GRANT SELECT, UPDATE, INSERT ON UserAccount, CheckingAccount, SavingsAccount, Transactions, UserCheckingAccount, UserSavingsAccount TO customer;
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO customer;

DROP ROLE bankingAdmin;
CREATE ROLE bankingAdmin WITH LOGIN PASSWORD 'admin';
GRANT SELECT, UPDATE, INSERT, DELETE ON UserAccount, CheckingAccount, SavingsAccount, Transactions, UserCheckingAccount, UserSavingsAccount TO bankingAdmin;
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO customer;