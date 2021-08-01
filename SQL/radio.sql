CREATE TABLE users (
    userId INT(255) AUTO_INCREMENT PRIMARY KEY,
    userName VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    createdOn TIMESTAMP NOT NULL,
    lastLogIn TIMESTAMP NOT NULL
);

CREATE TABLE radio (
    radioId INT(255) AUTO_INCREMENT PRIMARY KEY,
    station VARCHAR(50) NOT NULL,
    frequency VARCHAR(50) NOT NULL,
    location VARCHAR(50) NOT NULL,
    url VARCHAR(255) NOT NULL,
    likes INT
);

CREATE TABLE shows (
    showsId INT NOT NULL PRIMARY KEY,
    shows VARCHAR(255) NOT NULL,
    showTime VARCHAR(50) NOT NULL,
    likes INT,
    radioId INT NOT NULL,
    CONSTRAINT fk_radio
    FOREIGN KEY (radioId)
    REFERENCES radio (radioId)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE favourites (
    favId INT PRIMARY KEY,
    shows  TEXT,
    stations TEXT,
    userId INT NOT NULL,
    CONSTRAINT fk_user
    FOREIGN KEY (userId)
    REFERENCES users (userId)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);