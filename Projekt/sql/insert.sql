-- sample data

INSERT INTO eebf.benutzerkonto (aid, email, Passwort, usertype) VALUES ('1', 'eeBF_Admin', 'Tombstone', 'admin');
INSERT INTO eebf.benutzerkonto (aid, email, Passwort, usertype) VALUES ('2', 'eeBF_Kunde', 'Tombstone', 'kunde');

INSERT INTO eebf.kunde (cid, Nachname, Vorname, Land, PLZ, Ort, Strasse, HausNr, aid) VALUES  
('1', 'Schöndorfer', 'Roman', 'Österreich', '1090', 'Wien', 'Otto-Wagner-Platz', '5', '1');
INSERT INTO eebf.kunde (cid, Nachname, Vorname, Land, PLZ, Ort, Strasse, HausNr, aid) VALUES  
('2', 'Schöndorfer', 'Roman', 'Österreich', '2353', 'Guntramsdorf', 'Burgundergasse', '16', '2');

INSERT INTO eebf.bestellung (Bestellstatus, Datum, PaypalTNr, aid) VALUES ('liefernd,geliefert', current_timestamp(), '1', '1');
INSERT INTO eebf.bestellung (Bestellstatus, Datum, PaypalTNr, aid) VALUES ('', current_timestamp(), '2', '2');
