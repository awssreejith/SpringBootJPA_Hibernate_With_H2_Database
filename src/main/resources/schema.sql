CREATE TABLE Country (
   CountryID   VARCHAR     NOT NULL ,
    PRIMARY KEY (countryID),
    COUNTRYNAME VARCHAR(128) NOT NULL,
    Capital VARCHAR(128) NOT NULL,
    LANGUAGESPOKEN VARCHAR(128) NOT NULL
);

CREATE TABLE Language (
    LanguageID   VARCHAR     NOT NULL ,
    PRIMARY KEY (LanguageID),
    LANGUAGENAME VARCHAR(128) NOT NULL
);