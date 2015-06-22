SELECT setval('public.person_code_seq', 1001, true);
ALTER SEQUENCE person_code_seq INCREMENT 11 MINVALUE 1001 CACHE 1001 start 1001;

SELECT setval('public.contract_code_seq', 1001, true);
ALTER SEQUENCE contract_code_seq INCREMENT 11 MINVALUE 1001 CACHE 1001 start 1001;

SELECT setval('public.accommodation_unit_code_seq', 1001, true);
ALTER SEQUENCE accommodation_unit_code_seq INCREMENT 11 MINVALUE 1001 CACHE 1001 start 1001;

INSERT INTO USER_ROLE (ROLE_NAME) VALUES ('persUser'), ('contractUser'), ('accommUser'), ('persAdmin'), ('contractAdmin'), ('accommAdmin'), ('client'), ('usermoduleAdmin'), ('usermoduleUser');

--USERS
INSERT INTO FMS_USER (USERNAME, PASSWORD) VALUES ('admin', '6491cacf01b2e1c6d08a5609d2f570ea57d71ae7f06e0391276d70d935d29aa51888d566751aa36dc5e12e18da693ece36427c167e2a7a67e48aca8928ba3979');
INSERT INTO FMS_USER (USERNAME, PASSWORD) VALUES ('user', '6491cacf01b2e1c6d08a5609d2f570ea57d71ae7f06e0391276d70d935d29aa51888d566751aa36dc5e12e18da693ece36427c167e2a7a67e48aca8928ba3979');

INSERT INTO FMS_USER2USER_ROLE (USER_ID, USER_ROLE_ID) VALUES (1,4), (1,5), (1,6), (1,8);
INSERT INTO FMS_USER2USER_ROLE (USER_ID, USER_ROLE_ID) VALUES (2,1), (2,2), (2,3), (2,9);

--create technical user to be used in MDB
--INSERT INTO FMS_USER (USERNAME, PASSWORD) VALUES ('technical_user', 'DUMMY_PASSWORD');
--INSERT INTO FMS_USER2USER_ROLE (USER_ID, USER_ROLE_ID) VALUES (3,4), (3,5), (3,6), (3,8);
