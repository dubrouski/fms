SELECT setval('public.person_business_id_seq', 1001, true);
ALTER SEQUENCE person_business_id_seq INCREMENT 11 MINVALUE 1001 CACHE 1001 start 1001;


insert into accommodation_unit (name, type, deposit_amount) values ('by the window', 'room', '56.68'), ('at the door', 'room', '4785.7');
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


--fill countries list
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('AF', 'Afghanistan', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('AL', 'Albania', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('DZ', 'Algeria', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('AS', 'American Samoa', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('AD', 'Andorra', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('AO', 'Angola', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('AI', 'Anguilla', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('AQ', 'Antarctica', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('AG', 'Antigua and Barbuda', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('AR', 'Argentina', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('AM', 'Armenia', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('AW', 'Aruba', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('AU', 'Australia', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('AT', 'Austria', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('AZ', 'Azerbaijan', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('BS', 'Bahamas', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('BH', 'Bahrain', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('BD', 'Bangladesh', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('BB', 'Barbados', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('BY', 'Belarus', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('BE', 'Belgium', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('BZ', 'Belize', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('BJ', 'Benin', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('BM', 'Bermuda', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('BT', 'Bhutan', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('BO', 'Bolivia', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('BA', 'Bosnia and Herzegovina', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('BW', 'Botswana', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('BV', 'Bouvet Island', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('BR', 'Brazil', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('BQ', 'British Antarctic Territory', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('IO', 'British Indian Ocean Territory', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('VG', 'British Virgin Islands', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('BN', 'Brunei', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('BG', 'Bulgaria', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('BF', 'Burkina Faso', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('BI', 'Burundi', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('KH', 'Cambodia', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('CM', 'Cameroon', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('CA', 'Canada', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('CT', 'Canton and Enderbury Islands', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('CV', 'Cape Verde', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('KY', 'Cayman Islands', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('CF', 'Central African Republic', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('TD', 'Chad', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('CL', 'Chile', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('CN', 'China', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('CX', 'Christmas Island', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('CC', 'Cocos [Keeling] Islands', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('CO', 'Colombia', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('KM', 'Comoros', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('CG', 'Congo - Brazzaville', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('CD', 'Congo - Kinshasa', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('CK', 'Cook Islands', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('CR', 'Costa Rica', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('HR', 'Croatia', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('CU', 'Cuba', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('CY', 'Cyprus', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('CZ', 'Czech Republic', 1);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('CI', 'Côte d’Ivoire', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('DK', 'Denmark', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('DJ', 'Djibouti', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('DM', 'Dominica', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('DO', 'Dominican Republic', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('NQ', 'Dronning Maud Land', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('DD', 'East Germany', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('EC', 'Ecuador', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('EG', 'Egypt', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('SV', 'El Salvador', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('GQ', 'Equatorial Guinea', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('ER', 'Eritrea', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('EE', 'Estonia', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('ET', 'Ethiopia', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('FK', 'Falkland Islands', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('FO', 'Faroe Islands', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('FJ', 'Fiji', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('FI', 'Finland', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('FR', 'France', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('GF', 'French Guiana', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('PF', 'French Polynesia', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('TF', 'French Southern Territories', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('FQ', 'French Southern and Antarctic Territories', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('GA', 'Gabon', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('GM', 'Gambia', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('GE', 'Georgia', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('DE', 'Germany', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('GH', 'Ghana', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('GI', 'Gibraltar', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('GR', 'Greece', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('GL', 'Greenland', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('GD', 'Grenada', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('GP', 'Guadeloupe', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('GU', 'Guam', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('GT', 'Guatemala', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('GG', 'Guernsey', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('GN', 'Guinea', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('GW', 'Guinea-Bissau', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('GY', 'Guyana', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('HT', 'Haiti', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('HM', 'Heard Island and McDonald Islands', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('HN', 'Honduras', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('HK', 'Hong Kong SAR China', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('HU', 'Hungary', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('IS', 'Iceland', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('IN', 'India', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('iso_code', 'Indonesia', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('IR', 'Iran', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('IQ', 'Iraq', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('IE', 'Ireland', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('IM', 'Isle of Man', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('IL', 'Israel', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('IT', 'Italy', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('JM', 'Jamaica', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('JP', 'Japan', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('JE', 'Jersey', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('JT', 'Johnston Island', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('JO', 'Jordan', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('KZ', 'Kazakhstan', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('KE', 'Kenya', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('KI', 'Kiribati', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('KW', 'Kuwait', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('KG', 'Kyrgyzstan', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('LA', 'Laos', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('LV', 'Latvia', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('LB', 'Lebanon', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('LS', 'Lesotho', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('LR', 'Liberia', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('LY', 'Libya', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('LI', 'Liechtenstein', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('LT', 'Lithuania', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('LU', 'Luxembourg', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('MO', 'Macau SAR China', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('MK', 'Macedonia', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('MG', 'Madagascar', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('MW', 'Malawi', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('MY', 'Malaysia', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('MV', 'Maldives', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('ML', 'Mali', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('MT', 'Malta', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('MH', 'Marshall Islands', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('MQ', 'Martinique', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('MR', 'Mauritania', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('MU', 'Mauritius', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('YT', 'Mayotte', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('FX', 'Metropolitan France', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('MX', 'Mexico', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('FM', 'Micronesia', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('MI', 'Miso_codeway Islands', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('MD', 'Moldova', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('MC', 'Monaco', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('MN', 'Mongolia', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('ME', 'Montenegro', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('MS', 'Montserrat', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('MA', 'Morocco', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('MZ', 'Mozambique', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('MM', 'Myanmar [Burma]', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('NA', 'Namibia', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('NR', 'Nauru', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('NP', 'Nepal', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('NL', 'Netherlands', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('AN', 'Netherlands Antilles', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('NT', 'Neutral Zone', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('NC', 'New Caledonia', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('NZ', 'New Zealand', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('NI', 'Nicaragua', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('NE', 'Niger', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('NG', 'Nigeria', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('NU', 'Niue', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('NF', 'Norfolk Island', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('KP', 'North Korea', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('VD', 'North Vietnam', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('MP', 'Northern Mariana Islands', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('NO', 'Norway', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('OM', 'Oman', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('PC', 'Pacific Islands Trust Territory', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('PK', 'Pakistan', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('PW', 'Palau', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('PS', 'Palestinian Territories', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('PA', 'Panama', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('PZ', 'Panama Canal Zone', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('PG', 'Papua New Guinea', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('PY', 'Paraguay', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('YD', 'People''s Democratic Republic of Yemen', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('PE', 'Peru', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('PH', 'Philippines', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('PN', 'Pitcairn Islands', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('PL', 'Poland', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('PT', 'Portugal', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('PR', 'Puerto Rico', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('QA', 'Qatar', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('RO', 'Romania', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('RU', 'Russia', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('RW', 'Rwanda', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('RE', 'Réunion', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('BL', 'Saint Barthélemy', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('SH', 'Saint Helena', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('KN', 'Saint Kitts and Nevis', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('LC', 'Saint Lucia', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('MF', 'Saint Martin', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('PM', 'Saint Pierre and Miquelon', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('VC', 'Saint Vincent and the Grenadines', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('WS', 'Samoa', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('SM', 'San Marino', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('SA', 'Saudi Arabia', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('SN', 'Senegal', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('RS', 'Serbia', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('CS', 'Serbia and Montenegro', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('SC', 'Seychelles', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('SL', 'Sierra Leone', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('SG', 'Singapore', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('SK', 'Slovakia', 2);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('SI', 'Slovenia', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('SB', 'Solomon Islands', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('SO', 'Somalia', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('ZA', 'South Africa', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('GS', 'South Georgia and the South Sandwich Islands', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('KR', 'South Korea', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('ES', 'Spain', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('LK', 'Sri Lanka', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('SD', 'Sudan', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('SR', 'Suriname', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('SJ', 'Svalbard and Jan Mayen', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('SZ', 'Swaziland', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('SE', 'Sweden', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('CH', 'Switzerland', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('SY', 'Syria', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('ST', 'São Tomé and Príncipe', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('TW', 'Taiwan', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('TJ', 'Tajikistan', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('TZ', 'Tanzania', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('TH', 'Thailand', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('TL', 'Timor-Leste', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('TG', 'Togo', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('TK', 'Tokelau', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('TO', 'Tonga', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('TT', 'Triniso_codead and Tobago', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('TN', 'Tunisia', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('TR', 'Turkey', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('TM', 'Turkmenistan', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('TC', 'Turks and Caicos Islands', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('TV', 'Tuvalu', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('UM', 'U.S. Minor Outlying Islands', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('PU', 'U.S. Miscellaneous Pacific Islands', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('VI', 'U.S. Virgin Islands', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('UG', 'Uganda', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('UA', 'Ukraine', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('SU', 'Union of Soviet Socialist Republics', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('AE', 'United Arab Emirates', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('GB', 'United Kingdom', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('US', 'United States', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('ZZ', 'Unknown or Invaliso_code Region', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('UY', 'Uruguay', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('UZ', 'Uzbekistan', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('VU', 'Vanuatu', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('VA', 'Vatican City', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('VE', 'Venezuela', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('VN', 'Vietnam', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('WK', 'Wake Island', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('WF', 'Wallis and Futuna', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('EH', 'Western Sahara', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('YE', 'Yemen', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('ZM', 'Zambia', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('ZW', 'Zimbabwe', 10);
INSERT INTO "country" ("iso_code", "readable_name", "ordering") VALUES ('AX', 'Åland Islands', 10);

--accommodation unit related seeds
insert into address (city_name, street_name, street_num, flat_num, country_id) values ('Brno', 'Botanicka', '53', '26a', 59);

insert into price (base_price, services_price, currency, valid_from, valid_to) values (2500, 1400, 'CZK', '10.2.2016', '9.2.2017');
insert into price (base_price, services_price, currency, valid_from, valid_to) values (3200, 1700, 'CZK', '10.2.2017', '9.2.2018');
insert into price (base_price, services_price, currency, valid_from, valid_to) values (1500, 2000, 'CZK', '11.3.2015', '9.8.2018');

insert into accommodation_unit (name, type, deposit_amount, address_id, price_id) values ('big room', 'room', '56.68', 1, 1), ('small room', 'room', '4785.7', 1, 2), ('at the window', 'place', '256', null, 3);

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
