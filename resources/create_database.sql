DROP DATABASE IF EXISTS hotel;
CREATE DATABASE hotel;

-- UTILISATION DE LA BDD hotel
use hotel;

-- CREATION DE LA TABLE SAISON
CREATE TABLE saison(
    id_saison INT PRIMARY KEY,
    nom VARCHAR(20) NOT NULL,
    pourcentage DOUBLE NOT NULL
);

-- CREATION DE LA TABLE TYPE
CREATE TABLE type(
    id_type INT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prix DOUBLE NOT NULL
);

-- CREATION DE LA TABLE CATEGORIE
CREATE TABLE categorie(
    id_categorie INT PRIMARY KEY,
    nom VARCHAR(20) NOT NULL,
    pourcentage DOUBLE NOT NULL
);

-- CREATION DE LA TABLE CHAMBRE
CREATE TABLE chambre(
    id_chambre INT PRIMARY KEY,
    id_saison INT NOT NULL,
    id_type INT NOT NULL,
    id_categorie INT NOT NULL,
    FOREIGN KEY (id_saison) REFERENCES saison(id_saison),
    FOREIGN KEY (id_type) REFERENCES type(id_type),
    FOREIGN KEY (id_categorie) REFERENCES categorie(id_categorie)
);

-- CREATION DE LA TABLE CLIENT
CREATE TABLE client(
    id_client INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(20) NOT NULL,
    prenom VARCHAR(20) NOT NULL,
    cin INT NOT NULL,
    telephone VARCHAR(10) NOT NULL
);

-- CREATION DE LA TABLE RESERVATION
CREATE TABLE reservation(
    id_reservation INT PRIMARY KEY AUTO_INCREMENT,
    nb_nuits INT NOT NULL,
    date_reservation DATE NOT NULL,
    nb_personne INT NOT NULL,
    pension VARCHAR(20) NOT NULL
);

-- CREATION DE LA TABLE RESERVATION_CHAMBRE
CREATE TABLE reservation_chambre(
    id_reservation INT NOT NULL,
    id_chambre INT NOT NULL,
    date_debut DATE NOT NULL,
    date_fin DATE NOT NULL,
    FOREIGN KEY (id_reservation) REFERENCES reservation(id_reservation),
    FOREIGN KEY(id_chambre) REFERENCES chambre(id_chambre)
);

-- INSERTION DES TYPES
INSERT INTO type VALUES(1, 'chambre simple', 50);
INSERT INTO type VALUES(2, 'chambre double avec lit double', 70);
INSERT INTO type VALUES(3, 'chambre double avec lits simples', 70);
INSERT INTO type VALUES(4, 'chambre triple avec lits simples', 120);
INSERT INTO type VALUES(5, 'chambre quadruple avec lits simples', 160);
INSERT INTO type VALUES(6, 'suite', 200);

-- INSERTION DES CATEGORIES
INSERT INTO categorie VALUES(1, 'basique', 1.0);
INSERT INTO categorie VALUES(2, 'affaire', 1.2);
INSERT INTO categorie VALUES(3, 'familiale', 1.1);
INSERT INTO categorie VALUES(4, 'luxe', 1.5);
INSERT INTO categorie VALUES(5, 'royale', 2.0);

-- INSERTION DES SAISONS
INSERT INTO saison VALUES(1, 'hiver', 0.7);
INSERT INTO saison VALUES(2, 'printemps', 0.9);
INSERT INTO saison VALUES(3, 'été', 1.5);
INSERT INTO saison VALUES(4, 'automne', 0.6);

-- INSERTION DES CHAMBRES
INSERT INTO chambre VALUES(1, 1, 1, 1);
INSERT INTO chambre VALUES(2, 1, 1, 2);
INSERT INTO chambre VALUES(3, 1, 2, 1);
INSERT INTO chambre VALUES(4, 1, 2, 1);
INSERT INTO chambre VALUES(5, 1, 2, 2);
INSERT INTO chambre VALUES(6, 1, 3, 3);
INSERT INTO chambre VALUES(7, 1, 3, 3);
INSERT INTO chambre VALUES(8, 1, 3, 1);
INSERT INTO chambre VALUES(9, 1, 4, 1);
INSERT INTO chambre VALUES(10, 1, 4, 1);
INSERT INTO chambre VALUES(11, 1, 4, 2);
INSERT INTO chambre VALUES(12, 1, 4, 4);
INSERT INTO chambre VALUES(13, 1, 5, 4);
INSERT INTO chambre VALUES(14, 1, 5, 3);
INSERT INTO chambre VALUES(15, 1, 6, 5);

-- INSERTION DE CLIENT
INSERT INTO client VALUES(1, 'castaner', 'antony', 123, '0781208709');

-- INSERTION DE RESERVATION
INSERT INTO reservation VALUES(1, 5, '2000-07-03 05:37:00', 5, 'complete');
INSERT INTO reservation_chambre VALUES(1, 1, '2000-07-03 05:37:00', '2000-08-03 05:37:00');