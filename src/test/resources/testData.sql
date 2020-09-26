insert into member(name) values ('Undertaker');
insert into member(name) values ('Hulk Hogan');
insert into member(name) values ('Mark Henry');

insert into activity(name, capacity, price) values ('Wrestling', 1, 15.50);
insert into activity(name, capacity, price) values ('Ballet', 5, 20);

insert into activity_member(activity_id, member_id, start_date) values (2, 1, sysdate);
insert into activity_member(activity_id, member_id, start_date) values (2, 2, sysdate);

insert into fee(month, year, membership_fee, start_date, payment_date, activity_member_id) values (9, 2020, 20, sysdate, null, 1);
insert into fee(month, year, membership_fee, start_date, payment_date, activity_member_id) values (8, 2020, 20, sysdate, sysdate, 2);
insert into fee(month, year, membership_fee, start_date, payment_date, activity_member_id) values (9, 2020, 20, sysdate, null, 2);