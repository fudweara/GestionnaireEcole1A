CREATE TABLE Identification
(
    identifiant  VARCHAR2(20),
    motDePasse VARCHAR2(600) CONSTRAINT NN_motDePasse NOT NULL,
    CONSTRAINT PK_Identification PRIMARY KEY(identifiant)
);

/* INSERT INTO identification VALUES ('admin', 'DPjEXsapKPAGYFCWiigBkHecxc50Xq3G8Bavaj0jhds=$evKgSeo7wPLv/zPN1mahUszkUsYkqP7Xbj8vArv0y2HEH9YJLUREPwQmN5L/Cftco+ElP4+cHRuwSksUsezlFA=='); */