CREATE TABLE benutzerkonto (
    aid SMALLINT(5) UNSIGNED NOT NULL AUTO_INCREMENT,
    Passwort VARCHAR(40) NOT NULL DEFAULT 'New$0815',
    email VARCHAR(40) NOT NULL DEFAULT 'new.customer@eebf.at',	-- why not have email as key?
	createdate TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
	usertype char(5) NOT NULL DEFAULT 'kunde',
    CONSTRAINT a_pk PRIMARY KEY (aid)
);

CREATE TABLE kunde (
    cid SMALLINT(5) UNSIGNED NOT NULL AUTO_INCREMENT,
    Nachname VARCHAR(40) NOT NULL DEFAULT '',
    Vorname VARCHAR(20) NOT NULL DEFAULT '',
    Land VARCHAR(20) NOT NULL DEFAULT '',
    PLZ SMALLINT(5) UNSIGNED NOT NULL,
    Ort VARCHAR(30) NOT NULL DEFAULT '',
    Strasse VARCHAR(40) NOT NULL DEFAULT '',
    HausNr SMALLINT(3) UNSIGNED NOT NULL,
    aid SMALLINT(5) UNSIGNED NOT NULL,
    PRIMARY KEY (cid),
    KEY (aid),
    CONSTRAINT c_fk_a FOREIGN KEY (aid)
        REFERENCES benutzerkonto (aid)
        ON DELETE CASCADE
);

CREATE TABLE bestellung (
    oid INT AUTO_INCREMENT,
    Datum TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    Bestellstatus SET('bestellt', 'bezahlt', 'liefernd', 'geliefert', 'abgeschlossen') ,
    PaypalTNr CHAR(12),
    aid SMALLINT(5) UNSIGNED NOT NULL,
    CONSTRAINT o_pk PRIMARY KEY (oid),
--    KEY (aid), aid is not a key, a customer can have more than one order
    CONSTRAINT o_fk_a FOREIGN KEY (aid)
        REFERENCES benutzerkonto (aid)
        ON DELETE CASCADE
);

CREATE TABLE lieferung (
    lid INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
    lDatum TIMESTAMP NOT NULL,
    Kosten DECIMAL(4 , 2 ) NOT NULL DEFAULT '2.50',
    UPSLNr CHAR(12),
    oid INT(10) UNSIGNED NOT NULL,
    CONSTRAINT l_pk PRIMARY KEY (lid),
    KEY (oid), -- TODO only one delivery per order?
    CONSTRAINT l_fk_o FOREIGN KEY (oid)
        REFERENCES bestellung (oid)
        ON DELETE CASCADE
);

CREATE TABLE lager (
    sid TINYINT(3) UNSIGNED NOT NULL AUTO_INCREMENT,
    Regalfach SMALLINT(5) UNSIGNED NOT NULL,
    Regalplatz SMALLINT(5) UNSIGNED NOT NULL,
    CONSTRAINT s_pk PRIMARY KEY (sid)
);

CREATE TABLE produkt (
    pid SMALLINT(5) UNSIGNED NOT NULL AUTO_INCREMENT,
    PBezeichnung VARCHAR(30) NOT NULL DEFAULT '',
    PBeschreibung VARCHAR(50) NOT NULL DEFAULT '',
    oid INT(10) UNSIGNED NOT NULL,
    sid TINYINT(3) UNSIGNED NOT NULL,
    PRIMARY KEY (pid),
    KEY (oid),
    KEY (sid),
    CONSTRAINT p_fk_o FOREIGN KEY (oid)
        REFERENCES bestellung (oid)
        ON DELETE CASCADE,
    CONSTRAINT p_fk_s FOREIGN KEY (sid)
        REFERENCES lager (sid)
        ON DELETE CASCADE
);

CREATE TABLE bestellposition (
    posid TINYINT(3) UNSIGNED NOT NULL,
    Menge TINYINT(3) UNSIGNED NOT NULL DEFAULT '0',
    oid INT(10) UNSIGNED NOT NULL,
    pid SMALLINT(5) UNSIGNED NOT NULL,
    PRIMARY KEY (oid , posid),
    KEY (pid),
    CONSTRAINT pos_fk_p FOREIGN KEY (pid)
        REFERENCES produkt (pid)
        ON DELETE CASCADE,
    CONSTRAINT pos_fk_o FOREIGN KEY (oid)
        REFERENCES bestellung (oid)
        ON DELETE CASCADE
);

CREATE TABLE produktkategorie (
	kid TINYINT(3) UNSIGNED NOT NULL AUTO_INCREMENT,
	bezeichnung varchar(20) NOT NULL,
	beschreibung varchar(50),
	oberkategorie TINYINT(3) UNSIGNED,
	CONSTRAINT k_pk PRIMARY KEY (kid),
	CONSTRAINT k_fk FOREIGN KEY (oberkategorie)
		REFERENCES produktkategorie (kid)
		ON DELETE CASCADE
);

-- ~ CREATE TABLE unterkategorie (
    -- ~ kid TINYINT(3) UNSIGNED NOT NULL AUTO_INCREMENT,
    -- ~ Bezeichnung VARCHAR(20) NOT NULL DEFAULT '',
    -- ~ Beschreibung VARCHAR(50) NOT NULL DEFAULT '',
    -- ~ CONSTRAINT k_pk PRIMARY KEY (kid)
-- ~ );

-- ~ CREATE TABLE oberkategorie (
    -- ~ kid1 TINYINT(3) UNSIGNED NOT NULL,
    -- ~ kid2 TINYINT(3) UNSIGNED NOT NULL,
    -- ~ KEY (kid1),
    -- ~ KEY (kid2),
    -- ~ CONSTRAINT uk_fk_k_1 FOREIGN KEY (kid1)
        -- ~ REFERENCES unterkategorie (kid)
        -- ~ ON DELETE CASCADE,
    -- ~ CONSTRAINT uk_fk_k_2 FOREIGN KEY (kid2)
        -- ~ REFERENCES unterkategorie (kid)
        -- ~ ON DELETE CASCADE
-- ~ );

CREATE TABLE zuordnung (
    pid SMALLINT(5) UNSIGNED NOT NULL,
    kid TINYINT(3) UNSIGNED NOT NULL,
    PRIMARY KEY (pid , kid),
    CONSTRAINT z_fk_p FOREIGN KEY (pid)
        REFERENCES produkt (pid)
        ON DELETE CASCADE,
    CONSTRAINT z_fk_k FOREIGN KEY (kid)
        REFERENCES produktkategorie (kid)
        ON DELETE CASCADE
);
