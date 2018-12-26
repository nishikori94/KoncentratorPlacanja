insert into tip_placanja(id, naziv, url, img_path) values (1, 'PayPal', 'http://localhost:9091/paypal/make/payment', 'assets/images/paypal-brands.svg');
insert into tip_placanja(id, naziv, url, img_path) values (2, 'Kartica', 'http://localhost:9091/placanje/generisiZahtevZaUplatu', 'assets/images/credit-card-regular.svg');
insert into tip_placanja(id, naziv, url, img_path) values (3, 'Bitcoin', 'http://localhost:9091/bitcoin/napraviPorudzbinuBTC', 'assets/images/btc-brands.svg');

insert into casopis(merchant_id, merchant_password, success_url, failed_url, error_url) values ('11', 'aaaa', 'http://localhost:4200/paypalSuccess', 'http://localhost:4200/paypalSuccess', 'http://www.google.com');
insert into casopis(merchant_id, merchant_password, success_url, failed_url, error_url) values ('22', 'bbbb', 'http://localhost:4200/paypalSuccess', 'http://localhost:4200/paypalSuccess', 'http://www.google.com');
insert into casopis(merchant_id, merchant_password, success_url, failed_url, error_url) values ('33', 'cccc', 'http://localhost:4200/paypalSuccess', 'http://localhost:4200/paypalSuccess', 'http://www.google.com');
insert into casopis(merchant_id, merchant_password, success_url, failed_url, error_url) values ('44', 'dddd', 'http://localhost:4200/paypalSuccess', 'http://localhost:4200/paypalSuccess', 'http://www.google.com');
insert into casopis(merchant_id, merchant_password, success_url, failed_url, error_url) values ('55', 'eeee', 'http://localhost:4200/paypalSuccess', 'http://localhost:4200/paypalSuccess', 'http://www.google.com');
insert into casopis(merchant_id, merchant_password, success_url, failed_url, error_url) values ('66', 'ffff', 'http://localhost:4200/paypalSuccess', 'http://localhost:4200/paypalSuccess', 'http://www.google.com');

insert into Banka(swift_kod, naziv, port) values ('BANKRS22', 'Banka_1', '9092');
insert into Banka(swift_kod, naziv, port) values ('BKKKRS22', 'Banka_2', '9093');
insert into Banka(swift_kod, naziv, port) values ('BAKKRS22', 'Banka_3', '9094');

insert into Racun(broj_racuna, casopis_id, banka_id) values ('909211111111111111', 1, 1);
insert into Racun(broj_racuna, casopis_id, banka_id) values ('909222222222222222', 2, 1);
insert into Racun(broj_racuna, casopis_id, banka_id) values ('909211011111111111', 3, 1);
insert into Racun(broj_racuna, casopis_id, banka_id) values ('909222220222222222', 4, 1);
insert into Racun(broj_racuna, casopis_id, banka_id) values ('909310111111111111', 5, 1);
insert into Racun(broj_racuna, casopis_id, banka_id) values ('909222222222220222', 6, 1);

insert into casopis_tip_placanja(casopis_id, tip_placanja_id) values (1, 1);
insert into casopis_tip_placanja(casopis_id, tip_placanja_id) values (1, 2);
insert into casopis_tip_placanja(casopis_id, tip_placanja_id) values (1, 3);

insert into casopis_tip_placanja(casopis_id, tip_placanja_id) values (2, 1);
insert into casopis_tip_placanja(casopis_id, tip_placanja_id) values (2, 2);

