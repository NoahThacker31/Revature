DROP ROLE employee;
CREATE ROLE employee WITH LOGIN PASSWORD 'employee';
GRANT SELECT, UPDATE, INSERT ON ers_reimbursement_status, ers_reimbursement_type, ers_user_roles, ers_users, ers_reimbursement TO employee;
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO employee;

DROP ROLE manager;
CREATE ROLE manager WITH LOGIN PASSWORD 'manager';
GRANT SELECT, UPDATE, INSERT, DELETE ON ers_reimbursement_status, ers_reimbursement_type, ers_user_roles, ers_users, ers_reimbursement TO manager;
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO manager;