-- You can use this file to load seed data into the database using SQL statements
insert into Member (id, name, email, phone_number) values (0, 'John Smith', 'john.smith@mailinator.com', '2125551212') 




insert into country (code) values ('CZECH_REPUBLIC'), ('SLOVAKIA'), ('POLAND'), ('AUSTRALIA');

insert into person 	(birth_date, business_id, email, first_name, last_name, legal_identificator, other_names, phone) values
			('2008-11-11', '00001', 'test@test.com', 'Sarah', 'Connor', 'YU123456789', 'Jessica', '777 888 999'),
			('01.01.1960', '00002', 'test2@test.com', 'Parker', 'Evans', 'YU123456788', '-', '669 888 999');