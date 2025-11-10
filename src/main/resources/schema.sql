DROP TABLE IF EXISTS Dog;
DROP TABLE IF EXISTS Breed;
DROP TABLE IF EXISTS AppUser;

CREATE TABLE Breed (
id BIGSERIAL PRIMARY KEY,
breedname VARCHAR(50) NOT NULL UNIQUE
);

INSERT INTO Breed (breedname) 
VALUES 
('Bordercollie'),
('Kultainennoutaja'),
('Isovillakoira'),
('Saksanpaimenkoira');

CREATE TABLE Dog (
id BIGSERIAL PRIMARY KEY,
dogname VARCHAR(50) NOT NULL,
breedid BIGINT REFERENCES Breed(id),
gender VARCHAR NOT NULL,
birthyear INT NOT NULL CHECK (birthyear BETWEEN 2000 AND 2030),
dogowner VARCHAR(50) NOT NULL CHECK (char_length(dogowner) >= 2),
activities VARCHAR(300) NOT NULL,
description VARCHAR (500) NOT NULL
);

INSERT INTO Dog (dogname, breedid, gender, birthyear, dogowner, activities, description)
VALUES 
('Hilla', 1, 'Narttu', 2021, 'Pikku Kalle', 'TOKO, Agility, Rallytoko','VOI, LTK: 198'),
('Mörkö', 2, 'Uros', 2019, 'Iines Ankka', 'Jälki, rauniot', 'Virta-tarkastettu pelastuskoira'),
('Nalle', 3, 'Ei tietoa', 2018, 'Risto Reipas', 'Jäljestys', 'BH suoritettu, JK1'),
('Kaamos', 4, 'Narttu', 2023, 'Riina Reipas', 'Esine-etsintä, IGP', 'BH');

CREATE TABLE Appuser (
id BIGSERIAL PRIMARY KEY,
username VARCHAR NOT NULL UNIQUE,
app_password VARCHAR NOT NULL,
app_role VARCHAR NOT NULL
);

INSERT INTO Appuser (username, app_password, app_role)
VALUES
('admin', '$2a$10$R.ntvNy3FSQEgXLsNJh5xumIPlabSrtdBRMIzZAv8A0K.P.966VWW', 'ADMIN'),
('user', '$2a$10$cKAXCXi3GdELDkJXkVbyZejKsnVP54lto0.MLzbvHYjGIoErpkGWO', 'USER');