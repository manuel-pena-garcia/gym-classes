insert into member(name) values ('Jed Wallace');
insert into member(name) values ('Matt Smith');
insert into member(name) values ('Scott Malone');

insert into activity(name, capacity, price) values ('Pilates', 5, 5.50);
insert into activity(name, capacity, price) values ('TRX', 1, 20);

insert into activity_member(activity_id, member_id, start_date) values (2, 1, sysdate);

insert into fee(month, year, membership_fee, start_date, payment_date, activity_member_id) values (5, 2020, 20, sysdate, null, 1);
insert into fee(month, year, membership_fee, start_date, payment_date, activity_member_id) values (6, 2020, 20, sysdate, null, 1);
insert into fee(month, year, membership_fee, start_date, payment_date, activity_member_id) values (7, 2020, 20, sysdate, null, 1);
insert into fee(month, year, membership_fee, start_date, payment_date, activity_member_id) values (8, 2020, 20, sysdate, null, 1);
insert into fee(month, year, membership_fee, start_date, payment_date, activity_member_id) values (9, 2020, 20, sysdate, null, 1);