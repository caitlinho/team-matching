-- *****************************************************************************
-- This script contains INSERT statements for populating tables with seed data
-- *****************************************************************************

BEGIN;

-- INSERT statements go here
-- the password for this account is !CodingRules1
INSERT INTO app_user (id, user_name, password, role, salt) VALUES (DEFAULT, 'INSTRUCTORA', '26uvymhdPWOX5iz/Du5Z8A==', 'instructor','8SgDncWoiH4SNtD6yJ7BtaPTho5pUEGSfRcY+cH4LtF8cCOj9n8H80emmRXyLz1FaxOL5vi4RbQ8xT92SNn29/IHA8ziq1IQ6eLd0wl0LL3ibxhxcTLSBRCRQ1LHVcO+temOe3NhNvPzlah9vEk35o3Dq7oqwiO2iIKtJcM36d0=');

INSERT INTO app_user(id, user_name, password, role, salt) VALUES (DEFAULT, 'afrank', 'H70FZSgB+SZKZF9cKqsPDA==', 'instructor', 'FHiL7k+2CU/pZkQh6KZBEUhnU3eHjpDogGNWweX71cwqaOls7SFp2vp1AbJsaGDXnxhoGTgmEd70QMi1jOFVdh87NTV1yxqyLgohjmmvKczgNLIJduzFU34zk8AwOq3hHqt0euk/kVbJlFqMmhAamUYf+fljUqRqRynDznqsLTI=');

INSERT INTO app_user(id, user_name, password, role, salt) VALUES (DEFAULT, 'klindsey', 'JSTzPJEFsf4b6Ligv9ATww==', 'administrator', 'GIFJZx5XpZpmOSEgpjr0LJyezgjkKNJmbEnxrvwBkS5i4dt0WWYwU49trk99EefWtZOylU33GyOZrRGg022kItgQO+tbHwW32W21WZr3O6yeEKLJ6ciw/jjnKzZIs039g6vZgd6RPLG9UEI9n+2JZuc6hlJTXXUwSBK+NBdmhIY=');

INSERT INTO class (class_id,name) VALUES (default, 'Java Blue');

INSERT INTO instructor_class (instructor_id, class_id) VALUES (1, 1);

INSERT INTO app_user_instructor (id, instructor_id) VALUES (1, 1);

INSERT INTO class (class_id,name) VALUES (default, 'Java Green');

INSERT INTO instructor_class (instructor_id, class_id) VALUES (1, 2);

INSERT INTO app_user_instructor (id, instructor_id) VALUES (1, 1);

COMMIT;