insert into tip_placanja(id, naziv, url) values (1, 'paypal', 'http://localhost:9091/paypal/make/payment');
insert into tip_placanja(id, naziv, url) values (2, 'kartica', 'http://localhost:9091/placanje/generisiZahtevZaUplatu');
insert into tip_placanja(id, naziv, url) values (3, 'bitcoin', 'http://localhost:9091/bitcoin/napraviPorudzbinuBTC');

insert into casopis(merchant_id, merchant_password) values ('11', 'aaaa');
insert into casopis(merchant_id, merchant_password) values ('22', 'bbbb');
insert into casopis(merchant_id, merchant_password) values ('33', 'cccc');
insert into casopis(merchant_id, merchant_password) values ('44', 'dddd');
insert into casopis(merchant_id, merchant_password) values ('55', 'eeee');
insert into casopis(merchant_id, merchant_password) values ('66', 'ffff');

insert into Banka(swift_kod, naziv, port) values ('BANKRS22', 'Banka_1', '9092');
insert into Banka(swift_kod, naziv, port) values ('BKKKRS22', 'Banka_2', '9093');
insert into Banka(swift_kod, naziv, port) values ('BAKKRS22', 'Banka_3', '9094');

insert into Racun(broj_racuna, casopis_id, banka_id) values ('909211111111111111', 1, 1);
insert into Racun(broj_racuna, casopis_id, banka_id) values ('909222222222222222', 2, 1);
insert into Racun(broj_racuna, casopis_id, banka_id) values ('909211011111111111', 3, 1);
insert into Racun(broj_racuna, casopis_id, banka_id) values ('909222220222222222', 4, 1);
insert into Racun(broj_racuna, casopis_id, banka_id) values ('909210111111111111', 5, 1);
insert into Racun(broj_racuna, casopis_id, banka_id) values ('909222222222220222', 6, 1);

insert into casopis_tip_placanja(casopis_id, tip_placanja_id) values (1, 1);
insert into casopis_tip_placanja(casopis_id, tip_placanja_id) values (1, 2);
insert into casopis_tip_placanja(casopis_id, tip_placanja_id) values (1, 3);

insert into casopis_tip_placanja(casopis_id, tip_placanja_id) values (2, 1);
insert into casopis_tip_placanja(casopis_id, tip_placanja_id) values (2, 2);

