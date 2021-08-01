CREATE TABLE user (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) UNIQUE,
    password VARCHAR(50)
);

CREATE TABLE jobs (
    job_id INT AUTO_INCREMENT PRIMARY KEY,
    userId INT,
    FOREIGN KEY (userId) REFERENCES user(user_id),
    title VARCHAR(40) NOT NULL,
    location VARCHAR(40) NOT NULL,
    posted DATE NOT NULL,
    salary INT NOT NULL,
    jobType VARCHAR(40) NOT NULL,
    descr TEXT NOT NULL
);
