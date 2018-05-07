CREATE TABLE Lieu
(
    idLieu  INTEGER,
    emplacement_lieu VARCHAR2(50) CONSTRAINT NN_emplacement NOT NULL,
    horaire_Ouverture     DATE,
    horaire_Fermeture     DATE,
    nb_Acces INTEGER CONSTRAINT NN_nbAcces NOT NULL,
    CONSTRAINT PK_Lieu PRIMARY KEY(idLieu)
);
CREATE SEQUENCE lieu_seq
  START WITH     1
  INCREMENT BY   1
  NOCACHE
  NOCYCLE;



CREATE TABLE TypeAcces
(
    idTypeAcces     INTEGER,
    nom_TypeAcces             VARCHAR2(50) CONSTRAINT NN_nomTypeAcces NOT NULL,
    CONSTRAINT PK_TypeAcces PRIMARY KEY(idTypeAcces)
);
CREATE SEQUENCE typeAcces_seq
  START WITH     1
  INCREMENT BY   1
  NOCACHE
  NOCYCLE;


CREATE TABLE Acces
(
    idAcces INTEGER,
    idLieu     INTEGER,
    idTypeAcces INTEGER,
    CONSTRAINT PK_Acces PRIMARY KEY(idAcces),
    CONSTRAINT FK_idLieu FOREIGN KEY(idLieu) REFERENCES Lieu(idLieu) ON DELETE CASCADE
    CONSTRAINT FK_idTypeAcces FOREIGN KEY(idTypeAcces) REFERENCES TypeAcces(idTypeAcces) ON DELETE CASCADE
);
CREATE SEQUENCE acces_seq
  START WITH     1
  INCREMENT BY   1
  NOCACHE
  NOCYCLE;


CREATE TABLE Personne
(
    idPersonne     INTEGER,
    nom_Personne            VARCHAR2(50) CONSTRAINT NN_nom_Personne NOT NULL,
    prenom_Personne            VARCHAR2(50) CONSTRAINT NN_prenom_Personne NOT NULL,
    dateNaissance   DATE CONSTRAINT NN_dateNaissance NOT NULL,
    fonction            VARCHAR2(50) CONSTRAINT NN_fonction NOT NULL,
    CONSTRAINT PK_Personne PRIMARY KEY(idPersonne)
);
CREATE SEQUENCE personne_seq
  START WITH     1
  INCREMENT BY   1
  NOCACHE
  NOCYCLE;

CREATE TABLE CarteLeo
(
    idCarte_Personne INTEGER,
    numeroBadge   VARCHAR2(50) CONSTRAINT NN_numeroBadge NOT NULL,
    CONSTRAINT PK_CarteLeo PRIMARY KEY(idCarte_Personne),
    CONSTRAINT FK_idCarte_idPersonne FOREIGN KEY(idCarte_Personne) REFERENCES Personne(idPersonne) ON DELETE CASCADE
);
