-- Active: 1675764864838@@127.0.0.1@3306@reverso
CREATE TABLE clients (
    id INT AUTO_INCREMENT PRIMARY KEY,
    raison_sociale VARCHAR(255) NOT NULL,
    num_rue VARCHAR(10) NOT NULL,
    rue VARCHAR(255) NOT NULL,
    code_postal VARCHAR(10) NOT NULL,
    ville VARCHAR(255) NOT NULL,
    tel VARCHAR(20) NOT NULL,
    email VARCHAR(255) NOT NULL,
    commentaire TEXT,
    chiffre_affaires DECIMAL(15, 2) NOT NULL,
    nb_employes INT NOT NULL
);

CREATE TABLE prospects (
    id INT AUTO_INCREMENT PRIMARY KEY,
    raison_sociale VARCHAR(255) NOT NULL,
    num_rue VARCHAR(10) NOT NULL,
    rue VARCHAR(255) NOT NULL,
    code_postal VARCHAR(10) NOT NULL,
    ville VARCHAR(255) NOT NULL,
    tel VARCHAR(20) NOT NULL,
    email VARCHAR(255) NOT NULL,
    commentaire TEXT,
    date_prospection DATE NOT NULL,
    interet VARCHAR(5) NOT NULL
);

CREATE TABLE contrats (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_client INT NOT NULL,
    libelle VARCHAR(50) NOT NULL,
    montant DECIMAL (15, 2) NOT NULL
);