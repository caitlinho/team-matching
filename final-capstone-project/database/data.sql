-- *****************************************************************************
-- This script contains INSERT statements for populating tables with seed data
-- *****************************************************************************

BEGIN;

-- INSERT statements go here
-- the password for this account is !CodingRules1
INSERT INTO app_user (id, user_name, password, role, salt) VALUES (DEFAULT, 'INSTRUCTORA', '26uvymhdPWOX5iz/Du5Z8A==', 'instructor','8SgDncWoiH4SNtD6yJ7BtaPTho5pUEGSfRcY+cH4LtF8cCOj9n8H80emmRXyLz1FaxOL5vi4RbQ8xT92SNn29/IHA8ziq1IQ6eLd0wl0LL3ibxhxcTLSBRCRQ1LHVcO+temOe3NhNvPzlah9vEk35o3Dq7oqwiO2iIKtJcM36d0=');

INSERT INTO app_user(id, user_name, password, role) VALUES (DEFAULT, 'afrank', '!FrontEndRules1', 'instructor');

INSERT INTO app_user(id, user_name, password, role) VALUES (DEFAULT, 'klindsey', '1CSSandDBmaster!', 'administrator')
COMMIT;