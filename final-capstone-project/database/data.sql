-- *****************************************************************************
-- This script contains INSERT statements for populating tables with seed data
-- *****************************************************************************

BEGIN;

-- INSERT statements go here
INSERT INTO app_user (id, user_name, password, role) VALUES (DEFAULT, 'blauvray', '!CodingRules1', 'instructor');

INSERT INTO app_user(id, user_name, password, role) VALUES (DEFAULT, 'afrank', '!FrontEndRules1', 'instructor');

INSERT INTO app_user(id, user_name, password, role) VALUES (DEFAULT, 'klindsey', '1CSSandDBmaster!', 'administrator')
COMMIT;