INSERT INTO address (id, country, city, street, `number`) VALUES (1, "Danmark", "Birkerød", "Stationsvej", "1b");
INSERT INTO address (id, country, city, street, `number`) VALUES (2, "Danmark", "Birkerød", "Hovedvej", "1a");

INSERT INTO person VALUES (1, "Thomas", "Vestergaard", "26508830");
INSERT INTO person VALUES (2, "Kasper", "Vestergaard", "26424887");
INSERT INTO person VALUES (3, "Thorbjørn", "Vestergaard", "24609197");
INSERT INTO person VALUES (4, "Sanne", "Vestergaard", "21746833");

INSERT INTO address_person VALUES (1, 1);
INSERT INTO address_person VALUES (2, 1);
INSERT INTO address_person VALUES (1, 2);
INSERT INTO address_person VALUES (1, 3);
INSERT INTO address_person VALUES (2, 3);
INSERT INTO address_person VALUES (2, 4);