insert into subscriber (first_name, last_name, msisdn, balance, status) VALUES ('Philip', 'Fry', '+12025008080', 100, 'Active');
insert into subscriber (first_name, last_name, msisdn, balance, status) VALUES ('Bender', 'Rodríguez', '+12025008081', 200, 'Active');

insert into call (subscriber_id) VALUES ((select id from subscriber where first_name = 'Philip' and last_name = 'Fry'));
insert into call (subscriber_id) VALUES ((select id from subscriber where first_name = 'Bender' and last_name = 'Rodríguez'));
insert into call (subscriber_id) VALUES ((select id from subscriber where first_name = 'Philip' and last_name = 'Fry'));