--persons
insert into person (birth_date, email, first_name, last_name, legal_identificator, other_names, phone) values ('2001-11-11', 'test1@test.com', 'Miranda', 'Connor', 'YU12wexcvr34A9', 'Jessica', '+420 777 999 999');
insert into person (birth_date, email, first_name, last_name, legal_identificator, other_names, phone) values ('2002-11-11', 'test22@test.com', 'Jack', 'Nickolson', 'YUsrg1234s5A6789', '', '+420 777 666 999');
insert into person (birth_date, email, first_name, last_name, legal_identificator, other_names, phone) values ('2003-11-11', 'test3@test.com', 'Arnold', 'Sch', 'YU123456789sf', '', '+420 777 888 666');
insert into person (birth_date, email, first_name, last_name, legal_identificator, other_names, phone) values ('2004-11-11', 'test4@test.com', 'Patrick', 'Sw', 'YU123S456sd789', '', '+420 777 888 777');
insert into person (birth_date, email, first_name, last_name, legal_identificator, other_names, phone) values ('2005-11-11', 'test5@test.com', 'Ted', 'Parker', 'YU1F2345678sdfg9', 'Leonard', '+420 777 654 999');
insert into person (birth_date, email, first_name, last_name, legal_identificator, other_names, phone) values ('2006-11-11', 'test6@test.com', 'Robin', 'Barker', 'YU123sd4567H89', '', '+420 777 888 665');
insert into person (birth_date, email, first_name, last_name, legal_identificator, other_names, phone) values ('2007-11-11', 'test7@test.com', 'Jasey', 'Larker', 'YU1234J56sdf789', '', '+420 654 888 999');
insert into person (birth_date, email, first_name, last_name, legal_identificator, other_names, phone) values ('2008-11-11', 'test8@test.com', 'Buzz', 'Petrov', 'YU1234sw5U6789', 'Fedor', '+420 732 888 999');
insert into person (birth_date, email, first_name, last_name, legal_identificator, other_names, phone) values ('2009-11-11', 'test9@test.com', 'Luis', 'Armstrong', 'YU1wer2Y3456789', '', '+420 777 998 999');
insert into person (birth_date, email, first_name, last_name, legal_identificator, other_names, phone) values ('2000-11-11', 'test0@test.com', 'Sam', 'Sam', 'YU123q45678werII9', '', '+420 777 888 000');
insert into person (birth_date, email, first_name, last_name, legal_identificator, other_names, phone) values ('2000-01-11', 'testA@test.com', 'Jam', 'Bram', 'YU12qtr3sd45678II9', '', '+420 345 888 000');
insert into person (birth_date, email, first_name, last_name, legal_identificator, other_names, phone) values ('2000-09-01', 'testB@test.com', 'Spam', 'Tram', 'YU12345ss678IttI9', '', '+420 777 345 000');
insert into person (birth_date, email, first_name, last_name, legal_identificator, other_names, phone) values ('2008-11-11', 'test@test.com', 'Sarah', 'Connor', 'YU123456789', 'Jessica', '+420 777 888 999'), ('01.01.1960', 'test2@test.com', 'Parker', 'Evans', 'YU123456788', '-', '+420 669 888 999');


--accommodation unit related seeds
--addresses with coordinates:
insert into address (city_name, street_name, street_num, country_id, latitude, longitude) values ('Brno', 'Hybesova', '33', 59, '49.189064', '16.603823');
insert into address (city_name, street_name, street_num, country_id, latitude, longitude) values ('Brno', 'Rostislavovo namesti', '15', 59, '49.222875', '16.598324');
insert into address (city_name, street_name, street_num, country_id, latitude, longitude) values ('Brno', 'Chladkova', '14', 59, '49.211706', '16.585643');
insert into address (city_name, street_name, street_num, country_id, latitude, longitude) values ('Brno', 'Zavodskeho', '19', 59, '49.192416', '16.657939');
insert into address (city_name, street_name, street_num, country_id, latitude, longitude) values ('Brno', 'Lipova', '13', 59, '49.194291', '16.582606');
insert into address (city_name, street_name, street_num, country_id, latitude, longitude) values ('Brno', 'trida Kpt. Jarose', '2', 59, '49.203007', '16.611038');


insert into price (base_price, services_price, currency, valid_from, valid_to) values (2500, 1400, 'CZK', '10.2.2016', '9.2.2017');
insert into price (base_price, services_price, currency, valid_from, valid_to) values (3200, 1700, 'CZK', '10.2.2017', '9.2.2018');
insert into price (base_price, services_price, currency, valid_from, valid_to) values (1500, 2000, 'CZK', '11.3.2015', '9.8.2018');
insert into price (base_price, services_price, currency, valid_from, valid_to) values (1800, 3000, 'CZK', '12.4.2015', '10.8.2018');
insert into price (base_price, services_price, currency, valid_from, valid_to) values (1456, 2200, 'CZK', '22.8.2015', '15.12.2018');
insert into price (base_price, services_price, currency, valid_from, valid_to) values (3800, 5200, 'CZK', '22.8.2015', '15.12.2018');
insert into price (base_price, services_price, currency, valid_from, valid_to) values (5000, 300, 'CZK', '22.8.2015', '15.12.2018');

insert into accommodation_unit (name, type, deposit_amount, address_id, price_id) values ('medium room', 'room', '56.68', 2, 6), ('big place', 'place', '4785.7', 1, 4), ('small place', 'place', '145.75', 1, 5);
insert into accommodation_unit (name, type, deposit_amount, address_id, price_id) values ('big room', 'room', '56.68', 1, 1), ('small room', 'room', '4785.7', 2, 2), ('at the window', 'place', '256', 2, 3);
insert into accommodation_unit (name, type, deposit_amount, address_id, price_id) values ('Pokoj na Hybesove', 'room', '5000.00', 3, 7), ('Misto 1', 'place', '3000.00', 3, 7), ('Misto 2', 'place', '3000.00', 3, 7);

insert into unit_unit (parent_id, child_id) values (1, 6), (4, 2), (4, 3);
insert into unit_unit (parent_id, child_id) values (7,8), (7,9);


--dummy contracts
insert into price (base_price, services_price, currency, valid_from, valid_to) values (2500, 1400, 'CZK', '10.2.2016', '9.2.2017');
insert into price (base_price, services_price, currency, valid_from, valid_to) values (3200, 1700, 'CZK', '10.2.2017', '9.2.2018');
insert into price (base_price, services_price, currency, valid_from, valid_to) values (1500, 2000, 'CZK', '11.3.2015', '9.8.2018');
insert into price (base_price, services_price, currency, valid_from, valid_to) values (1800, 3000, 'CZK', '12.4.2015', '10.8.2018');
insert into price (base_price, services_price, currency, valid_from, valid_to) values (1800, 3000, 'CZK', '12.4.2015', '10.8.2018');

insert into price (base_price, services_price, currency, valid_from, valid_to) values (6500, 1400, 'CZK', '10.2.2016', '9.2.2017');
insert into price (base_price, services_price, currency, valid_from, valid_to) values (8500, 1700, 'CZK', '10.2.2017', '9.2.2018');
insert into price (base_price, services_price, currency, valid_from, valid_to) values (7200, 2000, 'CZK', '11.3.2015', '9.8.2018');
insert into price (base_price, services_price, currency, valid_from, valid_to) values (1500, 3000, 'CZK', '12.4.2015', '10.8.2018');
insert into price (base_price, services_price, currency, valid_from, valid_to) values (1800, 3000, 'CZK', '12.4.2015', '10.12.2019');

insert into contract(start_date, end_date, state, accommodation_unit_id, tenant_id, keys_handed_over, price_id) values('2012-01-01', '2012-12-12', 'Closed', 2, 2, false, 7);
insert into contract(start_date, end_date, state, accommodation_unit_id, tenant_id, keys_handed_over, price_id) values('2013-01-01', '2013-12-12', 'Closed', 2, 2, false, 8);
insert into contract(start_date, end_date, state, accommodation_unit_id, tenant_id, keys_handed_over, price_id) values('2014-01-01', '2014-12-12', 'Closed', 2, 2, false, 9);
insert into contract(start_date, end_date, state, accommodation_unit_id, tenant_id, keys_handed_over, price_id) values('2015-01-01', '2015-12-12', 'Signed', 2, 2, false, 10);
insert into contract(start_date, end_date, state, accommodation_unit_id, tenant_id, keys_handed_over, price_id) values('2016-01-01', '2016-12-12', 'New', 2, 2, false, 11);

insert into contract(start_date, end_date, state, accommodation_unit_id, tenant_id, keys_handed_over, price_id) values('2012-01-01', '2012-12-12', 'Closed', 1, 1, false, 12);
insert into contract(start_date, end_date, state, accommodation_unit_id, tenant_id, keys_handed_over, price_id) values('2013-01-01', '2013-12-12', 'Closed', 1, 1, false, 13);
insert into contract(start_date, end_date, state, accommodation_unit_id, tenant_id, keys_handed_over, price_id) values('2014-01-01', '2014-12-12', 'Closed', 1, 1, false, 14);
insert into contract(start_date, end_date, state, accommodation_unit_id, tenant_id, keys_handed_over, price_id) values('2015-01-01', '2015-12-12', 'Signed', 1, 1, false, 15);
insert into contract(start_date, end_date, state, accommodation_unit_id, tenant_id, keys_handed_over, price_id) values('2016-01-01', '2016-12-12', 'New', 1, 1, false, 16);
