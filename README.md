# MySQL_Pet_Project

This is a pet project that can be used for training:
* creating, editing, deleting tables in the database;
* execution of SQL queries.

---
### How to download / install MySQL: 
Click [here](https://metanit.com/sql/mysql/).

---
### Sample of SQL queries for creation of tables:

* creation of "petProject" database:
```
CREATE DATABASE [IF NOT EXISTS] database_name;
```

* creation of "users" table:
```
CREATE TABLE users (                                      
    userid INT(10) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    address VARCHAR(255)
);
```

* creation of "accounts" table:
```
CREATE TABLE accounts (
	accountid INT(10) PRIMARY KEY AUTO_INCREMENT, 
	userid INT(10) NOT NULL, 
	balance INT(15) NOT NULL, 
	currency VARCHAR(10) NOT NULL,
	FOREIGN KEY (userid) REFERENCES users (userid)
);
```

* creation of "transactions" table:
```
CREATE TABLE transactions (
	transactinId INT(10) PRIMARY KEY AUTO_INCREMENT, 
	accountId INT(10) NOT NULL, 
	amount INT(10) NOT NULL,
	FOREIGN KEY (accountId) REFERENCES accounts (accountid)	
);
```

---
### Database Schema
Click [here](https://drive.google.com/file/d/1wjj9mjF_qgc6dLr7YDHIngZGSVPlxGhL/view?usp=share_link) to see.
