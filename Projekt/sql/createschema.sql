-- sql file that helps you setting up your database
-- simply log into your database as root and
-- execute file with source 'file' (without ' ')

CREATE SCHEMA eebf;

CREATE USER 'eeBF_Admin'@'localhost' IDENTIFIED BY 'Tombstone';
GRANT SELECT,INSERT,UPDATE,DELETE ON eebf.* TO 'eeBF_Admin'@'localhost';
-- show grants for 'eeBF_Admin'@'localhost';

-- You can create everything in the schema by executing the following commands
-- Apply the path to your DDL.sql file and remove comments
-- use eebf;
-- source /home/kettu/Development/Uni/IMSE/Projekt/sql/DDL.sql

-- fill database with sample data
-- use eebf;
-- source /home/kettu/Development/Uni/IMSE/Projekt/sql/insert.sql
