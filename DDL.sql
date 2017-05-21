DROP TABLE `zuordnung` CASCADE;
DROP TABLE `oberkategorie` CASCADE;
DROP TABLE `unterkategorie` CASCADE;
DROP TABLE `bestellposition` CASCADE;
DROP TABLE `produkt` CASCADE;
DROP TABLE `lager` CASCADE;
DROP TABLE `lieferung` CASCADE;
DROP TABLE `bestellung` CASCADE;
DROP TABLE `kunde` CASCADE;
DROP TABLE `benutzerkonto` CASCADE;


CREATE TABLE `benutzerkonto` (
    `aid` SMALLINT(5) UNSIGNED NOT NULL AUTO_INCREMENT,
    `Passwort` CHAR(8) NOT NULL DEFAULT 'New$0815',
    `email` VARCHAR(40) NOT NULL DEFAULT 'new.customer@eebf.at',
    CONSTRAINT `a_pk` PRIMARY KEY (`aid`)
);

CREATE TABLE `kunde` (
    `cid` SMALLINT(5) UNSIGNED NOT NULL AUTO_INCREMENT,
    `Nachname` VARCHAR(40) NOT NULL DEFAULT '',
    `Vorname` VARCHAR(20) NOT NULL DEFAULT '',
    `Land` VARCHAR(20) NOT NULL DEFAULT '',
    `PLZ` SMALLINT(5) UNSIGNED NOT NULL,
    `Ort` VARCHAR(30) NOT NULL DEFAULT '',
    `Strasse` VARCHAR(40) NOT NULL DEFAULT '',
    `HausNr` SMALLINT(3) UNSIGNED NOT NULL,
    `aid` SMALLINT(5) UNSIGNED NOT NULL,
    PRIMARY KEY (`cid`),
    KEY (`aid`),
    CONSTRAINT `c_fk_a` FOREIGN KEY (`aid`)
        REFERENCES `benutzerkonto` (`aid`)
        ON DELETE CASCADE
);

CREATE TABLE `bestellung` (
    `oid` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
    `Datum` DATETIME NOT NULL,
    `Status` SET('offen', 'bezahlt', 'in Zustellung', 'geliefert', 'abgeschlossen') DEFAULT 'offen',
    `PaypalTNr` CHAR(12) NOT NULL DEFAULT '',
    `aid` SMALLINT(5) UNSIGNED NOT NULL,
    PRIMARY KEY (`oid`),
    KEY (`aid`),
    CONSTRAINT `o_fk_a` FOREIGN KEY (`aid`)
        REFERENCES `benutzerkonto` (`aid`)
        ON DELETE CASCADE
);

CREATE TABLE `lieferung` (
    `lid` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
    `lDatum` DATETIME NOT NULL,
    `Kosten` DECIMAL(4 , 2 ) NOT NULL DEFAULT '2.50',
    `UPSLNr` CHAR(12),
    `oid` INT(10) UNSIGNED NOT NULL,
    PRIMARY KEY (`lid`),
    KEY (`oid`),
    CONSTRAINT `l_fk_o` FOREIGN KEY (`oid`)
        REFERENCES `bestellung` (`oid`)
        ON DELETE CASCADE
);

CREATE TABLE `lager` (
    `sid` TINYINT(3) UNSIGNED NOT NULL AUTO_INCREMENT,
    `Regalfach` SMALLINT(5) UNSIGNED NOT NULL,
    `Regalplatz` SMALLINT(5) UNSIGNED NOT NULL,
    CONSTRAINT `s_pk` PRIMARY KEY (`sid`)
);

CREATE TABLE `produkt` (
    `pid` SMALLINT(5) UNSIGNED NOT NULL AUTO_INCREMENT,
    `PBezeichnung` VARCHAR(30) NOT NULL DEFAULT '',
    `PBeschreibung` VARCHAR(50) NOT NULL DEFAULT '',
    `oid` INT(10) UNSIGNED NOT NULL,
    `sid` TINYINT(3) UNSIGNED NOT NULL,
    PRIMARY KEY (`pid`),
    KEY (`oid`),
    KEY (`sid`),
    CONSTRAINT `p_fk_o` FOREIGN KEY (`oid`)
        REFERENCES `bestellung` (`oid`)
        ON DELETE CASCADE,
    CONSTRAINT `p_fk_s` FOREIGN KEY (`sid`)
        REFERENCES `lager` (`sid`)
        ON DELETE CASCADE
);

CREATE TABLE `bestellposition` (
    `posid` TINYINT(3) UNSIGNED NOT NULL,
    `Menge` TINYINT(3) UNSIGNED NOT NULL DEFAULT '0',
    `oid` INT(10) UNSIGNED NOT NULL,
    `pid` SMALLINT(5) UNSIGNED NOT NULL,
    PRIMARY KEY (`oid` , `posid`),
    KEY (`pid`),
    CONSTRAINT `pos_fk_p` FOREIGN KEY (`pid`)
        REFERENCES `produkt` (`pid`)
        ON DELETE CASCADE,
    CONSTRAINT `pos_fk_o` FOREIGN KEY (`oid`)
        REFERENCES `bestellung` (`oid`)
        ON DELETE CASCADE
);

CREATE TABLE `unterkategorie` (
    `kid` TINYINT(3) UNSIGNED NOT NULL AUTO_INCREMENT,
    `Bezeichnung` VARCHAR(20) NOT NULL DEFAULT '',
    `Beschreibung` VARCHAR(50) NOT NULL DEFAULT '',
    CONSTRAINT `k_pk` PRIMARY KEY (`kid`)
);

CREATE TABLE `oberkategorie` (
    `kid1` TINYINT(3) UNSIGNED NOT NULL,
    `kid2` TINYINT(3) UNSIGNED NOT NULL,
    KEY (`kid1`),
    KEY (`kid2`),
    CONSTRAINT `uk_fk_k_1` FOREIGN KEY (`kid1`)
        REFERENCES `unterkategorie` (`kid`)
        ON DELETE CASCADE,
    CONSTRAINT `uk_fk_k_2` FOREIGN KEY (`kid2`)
        REFERENCES `unterkategorie` (`kid`)
        ON DELETE CASCADE
);

CREATE TABLE `zuordnung` (
    `pid` SMALLINT(5) UNSIGNED NOT NULL,
    `kid` TINYINT(3) UNSIGNED NOT NULL,
    PRIMARY KEY (`pid` , `kid`),
    CONSTRAINT `z_fk_p` FOREIGN KEY (`pid`)
        REFERENCES `produkt` (`pid`)
        ON DELETE CASCADE,
    CONSTRAINT `z_fk_k` FOREIGN KEY (`kid`)
        REFERENCES `unterkategorie` (`kid`)
        ON DELETE CASCADE
);
