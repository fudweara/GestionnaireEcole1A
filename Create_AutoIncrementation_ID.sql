CREATE OR REPLACE TRIGGER Lieu_on_insert
  BEFORE INSERT ON Lieu
  FOR EACH ROW
BEGIN
  SELECT lieu_seq.nextval
  INTO :new.idLieu
  FROM dual;
END;


CREATE OR REPLACE TRIGGER TypeAcces_on_insert
  BEFORE INSERT ON TypeAcces
  FOR EACH ROW
BEGIN
  SELECT typeAcces_seq.nextval
  INTO :new.idTypeAcces
  FROM dual;
END;

CREATE OR REPLACE TRIGGER ACCES_on_insert
  BEFORE INSERT ON Acces
  FOR EACH ROW
BEGIN
  SELECT acces_seq.nextval
  INTO :new.idAcces
  FROM dual;
END;
  
CREATE OR REPLACE TRIGGER Personne_on_insert
  BEFORE INSERT ON Personne
  FOR EACH ROW
BEGIN
  SELECT personne_seq.nextval
  INTO :new.idPersonne
  FROM dual;
END;