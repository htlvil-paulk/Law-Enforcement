DROP SEQUENCE sqPerson;
DROP SEQUENCE sqCrime;

DROP TABLE HasRemark CASCADE CONSTRAINTS;
DROP TABLE Crime CASCADE CONSTRAINTS;
DROP TABLE Remark CASCADE CONSTRAINTS;
DROP TABLE Person CASCADE CONSTRAINTS;
DROP TABLE OfficerCredential CASCADE CONSTRAINTS;

CREATE TABLE OfficerCredential (
    id INTEGER,
    username VARCHAR2(25),
    --Length of password depends on the hash algorithm
    password VARCHAR2(30),
    rank VARCHAR2(9),

    CONSTRAINT pkOfficerCredential PRIMARY KEY(id),
    CONSTRAINT uqUsername UNIQUE(username),

    CONSTRAINT rank CHECK (UPPER(rank) LIKE 'ASPIRANT'
                       OR UPPER(rank) LIKE 'OFFICER'
                       OR UPPER(rank) LIKE 'INSPECTOR'
                       OR UPPER(rank) LIKE 'GENERAL')
);

CREATE TABLE Person (
    id INTEGER,
    idCardNumber INTEGER NOT NULL,
    citizenship VARCHAR2(30) NOT NULL,

    picture BLOB,
    name VARCHAR2(50),
    lastName VARCHAR2(50),
    dateOfBirth DATE,
    birthPlace VARCHAR2(100),
    gender VARCHAR2(6),
    address VARCHAR2(100),
    
    flagPerson VARCHAR2(7) NOT NULL,

    -- Suspect-only attribute
    description CLOB,

    -- Officer-only attribute
    idOfficerCredential INTEGER,

    CONSTRAINT pkPerson PRIMARY KEY(id),
    CONSTRAINT fkOfficerCredential FOREIGN KEY(idOfficerCredential) REFERENCES OfficerCredential,

    CONSTRAINT uqPersonId UNIQUE(idCardNumber, citizenship),
    
    CONSTRAINT ckGenderPerson CHECK (UPPER(gender) LIKE 'MALE' OR UPPER(gender) LIKE 'FEMALE'),

    -- Flag-based constraints
    CONSTRAINT ckFlagPerson CHECK (UPPER(flagPerson) LIKE 'OFFICER' OR UPPER(flagPerson) LIKE 'SUSPECT'),
    CONSTRAINT ckDescription CHECK (UPPER(flagPerson) LIKE 'OFFICER' AND description IS NULL
                                     OR UPPER(flagPerson) LIKE 'SUSPECT'),
    CONSTRAINT ckIdOfficerCredential CHECK (UPPER(flagPerson) LIKE 'SUSPECT' AND idOfficerCredential IS NULL
                                            OR UPPER(flagPerson) LIKE 'OFFICER')
);

CREATE TABLE Crime (
    fileNumber VARCHAR2(15),
    idMainSuspect INTEGER,

    shortText VARCHAR2(100),
    dateTime DATE,
    crimeScene VARCHAR2(100),
    longText CLOB,

    CONSTRAINT pkCrime PRIMARY KEY (fileNumber),
    CONSTRAINT fkMainSuspect FOREIGN KEY (idMainSuspect) REFERENCES Person
    
    --Check, if the Person isn't an Officer --> trigger necessary
);

CREATE TABLE Remark (
    id INTEGER,
    description VARCHAR2(100),

    CONSTRAINT pkRemark PRIMARY KEY (id),
    CONSTRAINT uqRemark UNIQUE (description)
);

CREATE TABLE HasRemark (
    idPerson INTEGER,
    idRemark INTEGER,

    CONSTRAINT pkHasRemark PRIMARY KEY (idPerson, idRemark),
    CONSTRAINT fkPerson FOREIGN KEY (idPerson) REFERENCES Person,
    CONSTRAINT fkRemark FOREIGN KEY (idRemark) REFERENCES Remark

    --Check, if the Person isn't an Officer --> trigger necessary
);

CREATE SEQUENCE sqPerson START WITH 0 INCREMENT BY 11 MINVALUE 0;
CREATE SEQUENCE sqCrime START WITH 0 INCREMENT BY 111 MINVALUE 0;

INSERT INTO OfficerCredential VALUES(sqPerson.NEXTVAL, 'kreuzf', 'secret', 'GENERAL');
INSERT INTO Person VALUES(sqPerson.CURRVAL, 1121477, 'AUSTRIA', NULL, 'Ferdinand', 'Kreuz', TO_DATE('25.12.1972','DD.MM.YYYY'), 'Villach, AT', 'MALE', 'Ossiacher Zeile 70A, 9500 Villach', 'OFFICER', NULL,  sqPerson.CURRVAL);