insert into tip_placanja(id, naziv, url, img_path) values (1, 'PayPal', 'https://localhost:9091/api/paypal/create', 'assets/images/paypal-brands.svg');
insert into tip_placanja(id, naziv, url, img_path) values (2, 'Kartica', 'https://localhost:9091/api/creditcard/create', 'assets/images/credit-card-regular.svg');
insert into tip_placanja(id, naziv, url, img_path) values (3, 'Bitcoin', 'https://localhost:9091/api/bitcoin/create', 'assets/images/btc-brands.svg');

insert into casopis(merchant_id, merchant_password, success_url, failed_url, error_url) values ('11', 'aaaa', 'https://localhost:4200/paypalSuccess', 'https://localhost:4200/unsuccessful', 'https://localhost:4200/unsuccessful');
insert into casopis(merchant_id, merchant_password, success_url, failed_url, error_url) values ('22', 'bbbb', 'https://localhost:4200/paypalSuccess', 'https://localhost:4200/unsuccessful', 'https://localhost:4200/unsuccessful');
insert into casopis(merchant_id, merchant_password, success_url, failed_url, error_url) values ('33', 'cccc', 'https://localhost:4200/paypalSuccess', 'https://localhost:4200/unsuccessful', 'https://localhost:4200/unsuccessful');
insert into casopis(merchant_id, merchant_password, success_url, failed_url, error_url) values ('44', 'dddd', 'https://localhost:4200/paypalSuccess', 'https://localhost:4200/unsuccessful', 'https://localhost:4200/unsuccessful');
insert into casopis(merchant_id, merchant_password, success_url, failed_url, error_url) values ('55', 'eeee', 'https://localhost:4200/paypalSuccess', 'https://localhost:4200/unsuccessful', 'https://localhost:4200/unsuccessful');
insert into casopis(merchant_id, merchant_password, success_url, failed_url, error_url) values ('66', 'ffff', 'https://localhost:4200/paypalSuccess', 'https://localhost:4200/unsuccessful', 'https://localhost:4200/unsuccessful');

insert into Banka(swift_kod, naziv, port) values ('BANKRS22', 'Banka_1', '9092');
insert into Banka(swift_kod, naziv, port) values ('BKKKRS22', 'Banka_2', '9093');
insert into Banka(swift_kod, naziv, port) values ('BAKKRS22', 'Banka_3', '9094');

insert into Racun(broj_racuna, casopis_id, banka_id) values ('909211111111111111', 1, 1);
insert into Racun(broj_racuna, casopis_id, banka_id) values ('909222222222222222', 2, 1);
insert into Racun(broj_racuna, casopis_id, banka_id) values ('909211011111111111', 3, 1);
insert into Racun(broj_racuna, casopis_id, banka_id) values ('909222220222222222', 4, 1);
insert into Racun(broj_racuna, casopis_id, banka_id) values ('909310111111111111', 5, 2);
insert into Racun(broj_racuna, casopis_id, banka_id) values ('909222222222220222', 6, 1);

insert into casopis_tip_placanja(casopis_id, tip_placanja_id) values (1, 1);
insert into casopis_tip_placanja(casopis_id, tip_placanja_id) values (1, 2);
insert into casopis_tip_placanja(casopis_id, tip_placanja_id) values (1, 3);

insert into casopis_tip_placanja(casopis_id, tip_placanja_id) values (2, 1);
insert into casopis_tip_placanja(casopis_id, tip_placanja_id) values (2, 2);

