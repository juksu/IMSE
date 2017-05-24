INSERT INTO `eebf`.`benutzerkonto` (`aid`, `email`, `Passwort`, `type`) VALUES ('1', 'eeBF_Admin', 'Tombstone', 'admin');
INSERT INTO `eebf`.`benutzerkonto` (`aid`, `email`, `Passwort`, `type`) VALUES ('2', 'roman.schoendorfer@hotmail.de', 'eeBF2017', 'kunde');

INSERT INTO `eebf`.`kunde` 
(`cid`, `Nachname`, `Vorname`, `Land`, `PLZ`, `Ort`, `Strasse`, `HausNr`, `aid`) VALUES 
('1', 'Schöndorfer', 'Roman', 'Österreich', '1090', 'Wien', 'Otto-Wagner-Platz', '5', '1');
INSERT INTO `eebf`.`kunde` 
(`cid`, `Nachname`, `Vorname`, `Land`, `PLZ`, `Ort`, `Strasse`, `HausNr`, `aid`) VALUES  
('2', 'Schöndorfer', 'Roman',	'Österreich', '2353', 'Guntramsdorf', 'Burgundergasse', '16', '2');
