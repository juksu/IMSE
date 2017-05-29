-- sample data

INSERT INTO eebf.benutzerkonto (aid, email, Passwort, usertype) VALUES ('1', 'eeBF_Admin', 'Tombstone', 'admin');
INSERT INTO eebf.benutzerkonto (aid, email, Passwort, usertype) VALUES ('2', 'roman.schoendorfer@hotmail.de', 'eeBF2017', 'kunde');

INSERT INTO eebf.kunde (cid, Nachname, Vorname, Land, PLZ, Ort, Strasse, HausNr, aid) VALUES  
('1', 'Schöndorfer', 'Roman', 'Österreich', '1090', 'Wien', 'Otto-Wagner-Platz', '5', '1');
INSERT INTO eebf.kunde (cid, Nachname, Vorname, Land, PLZ, Ort, Strasse, HausNr, aid) VALUES  
('2', 'Schöndorfer', 'Roman', 'Österreich', '2353', 'Guntramsdorf', 'Burgundergasse', '16', '2');

INSERT INTO eebf.bestellung (Bestellstatus, Datum, PaypalTNr, aid) VALUES ('liefernd,geliefert', current_timestamp(), '1', '1');
INSERT INTO eebf.bestellung (Bestellstatus, Datum, PaypalTNr, aid) VALUES ('', current_timestamp(), '2', '2');


INSERT INTO eebf.produkt (pid, PBezeichnung, PBeschreibung, preis, menge, bezeichnung) VALUES (3, 'nokia', 'handys', 25, 3 ,'handy');

INSERT INTO eebf.produkt (pid, PBezeichnung, PBeschreibung, preis, menge, bezeichnung) VALUES (4, 'bmw', 'buecher', 23, 2 ,'auto');

INSERT INTO eebf.produkt (pid, PBezeichnung, PBeschreibung, preis, menge, bezeichnung) VALUES (6, 'hp', 'handys', 123, 3, 'laptop');

INSERT INTO eebf.produkt (pid, PBezeichnung, PBeschreibung, preis, menge, bezeichnung) VALUES (7, 'bla', 'buecher', 57,4 ,'buch');


INSERT INTO eebf.produktkategorie (kid, bezeichnung, beschreibung) VALUES (1, 'auto', 'autos');

INSERT INTO eebf.produktkategorie (kid, bezeichnung, beschreibung) VALUES (2, 'laptop', 'laptops');

INSERT INTO eebf.produktkategorie (kid, bezeichnung, beschreibung) VALUES (3, 'handy', 'handys');

INSERT INTO eebf.produktkategorie (kid, bezeichnung, beschreibung) VALUES (4, 'buch', 'buecher');
