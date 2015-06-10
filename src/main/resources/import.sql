SELECT setval('public.person_code_seq', 1001, true);
ALTER SEQUENCE person_code_seq INCREMENT 11 MINVALUE 1001 CACHE 1001 start 1001;

SELECT setval('public.contract_code_seq', 1001, true);
ALTER SEQUENCE contract_code_seq INCREMENT 11 MINVALUE 1001 CACHE 1001 start 1001;

SELECT setval('public.accommodation_unit_code_seq', 1001, true);
ALTER SEQUENCE accommodation_unit_code_seq INCREMENT 11 MINVALUE 1001 CACHE 1001 start 1001;

-- user and user rights
INSERT INTO "USER" (id, login, password, person_id) VALUES (1, 'admin', 'admin', null);
INSERT INTO "USER" (id, login, password, person_id) VALUES (2, 'user_read', 'user', null);
INSERT INTO "USER" (id, login, password, person_id) VALUES (3, 'user_write', 'user', null);

INSERT INTO user_right (id, right_name) VALUES (1, 'ADMIN_READ');
INSERT INTO user_right (id, right_name) VALUES (2, 'ADMIN_WRITE');
INSERT INTO user_right (id, right_name) VALUES (3, 'PERSON_READ');
INSERT INTO user_right (id, right_name) VALUES (4, 'PERSON_WRITE');
INSERT INTO user_right (id, right_name) VALUES (5, 'ACCOMM_READ');
INSERT INTO user_right (id, right_name) VALUES (6, 'ACCOMM_WRITE');
INSERT INTO user_right (id, right_name) VALUES (7, 'CONTRACT_READ');
INSERT INTO user_right (id, right_name) VALUES (8, 'CONTRACT_WRITE');

INSERT INTO user2user_right (user_id, user_right_id) VALUES (1, 1);
INSERT INTO user2user_right (user_id, user_right_id) VALUES (1, 2);
INSERT INTO user2user_right (user_id, user_right_id) VALUES (2, 3);
INSERT INTO user2user_right (user_id, user_right_id) VALUES (3, 4);
INSERT INTO user2user_right (user_id, user_right_id) VALUES (2, 5);
INSERT INTO user2user_right (user_id, user_right_id) VALUES (3, 6);
INSERT INTO user2user_right (user_id, user_right_id) VALUES (2, 7);
INSERT INTO user2user_right (user_id, user_right_id) VALUES (3, 8);



DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS USER_ROLES;

CREATE TABLE USERS (USERNAME varchar(255) NOT NULL, PASSWORD varchar(255) DEFAULT NULL, PRIMARY KEY (USERNAME));
CREATE TABLE USER_ROLES (ROLE varchar(255) NOT NULL,USERNAME varchar(255) NOT NULL,ID BIGSERIAL NOT NULL, PRIMARY KEY (ID)); 

INSERT INTO USERS (USERNAME, PASSWORD) VALUES ('admin', '6491cacf01b2e1c6d08a5609d2f570ea57d71ae7f06e0391276d70d935d29aa51888d566751aa36dc5e12e18da693ece36427c167e2a7a67e48aca8928ba3979'); --a1234 ->sha-512 hex
INSERT INTO USER_ROLES (ROLE, USERNAME) VALUES ('person-user', 'admin');

INSERT INTO USERS (USERNAME, PASSWORD) VALUES ('superadmin', '6491cacf01b2e1c6d08a5609d2f570ea57d71ae7f06e0391276d70d935d29aa51888d566751aa36dc5e12e18da693ece36427c167e2a7a67e48aca8928ba3979'); --a1234 ->sha-512 hex
INSERT INTO USER_ROLES (ROLE, USERNAME) VALUES ('person-admin', 'superadmin');
INSERT INTO USER_ROLES (ROLE, USERNAME) VALUES ('contract-admin', 'superadmin');
INSERT INTO USER_ROLES (ROLE, USERNAME) VALUES ('accommodation-admin', 'superadmin');