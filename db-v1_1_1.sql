# Version: v1.0
# Date: 2022/4/8
-- Ctrl+Shift+C 注释 SQL 窗口选择内容
-- Ctrl+Shift+R 从选择内容删除注释

/*create database Library*/
DROP DATABASE IF EXISTS library;
CREATE DATABASE IF NOT EXISTS library;
USE library;
/*
create table members
Includes basic information about the reader, 
such as name, sex, address, etc. 
It can be used to check out books, manage readers' personal information, search books, return books and other operations.
*/
CREATE TABLE IF NOT EXISTS members(
	id VARCHAR(30) PRIMARY KEY,#member id
	mtype INT NOT NULL, #1:student，2：teacher，3：else
	#cardid VARCHAR(30),#card of the member
	mname VARCHAR(30) NOT NULL,#member name
	pwd VARCHAR(50) NOT NULL,#passward
	sex INT NOT NULL,#0:female;1:male
	address  VARCHAR(30),#address of the member
	phone  VARCHAR(30),#address of the member
	borrownum INT DEFAULT 0 NOT NULL,#number of borrowings
	card_state  INT  NOT NULL, #0:disabled;1:available
	`resvnum` INT DEFAULT 0 NOT NULL,
	photoUrl VARCHAR(100)
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


/*
create table bookmanage
This class contains basic information about the book, such as the book number, author, ISBN, etc. 
It can be used to check out books, search books, return books, modify books, etc.
*/
CREATE TABLE IF NOT EXISTS book(
	bid INT AUTO_INCREMENT PRIMARY KEY,#book id
	bname VARCHAR(30) NOT NULL,#book name
	author VARCHAR(30) NOT NULL,#one auther for each book
	isbn VARCHAR(30) NOT NULL UNIQUE,#book ISBN
	price DECIMAL(10, 2) NOT NULL,#book price
	TYPE VARCHAR(30),  # book type
	introduction VARCHAR(2000), # book introduction
	racknum  VARCHAR(30), #book racknumber
	copiesnum INT, #number of copies
	total INT DEFAULT 0 NOT NULL,
	publishing VARCHAR(50),
	published VARCHAR(30),
	photoUrl VARCHAR(100),
	`resvnum` INT DEFAULT 0 NOT NULL
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

UPDATE reservation SET notify = 0
WHERE resv_id = 1;




/*
create table borrowmanage
Borrow: This class contains the date when the reader borrowed the book and the date when the book is returned, 
so that the reader can borrow the book, renew the book, return the book, pay the default fee and other operations.
*/
CREATE TABLE IF NOT EXISTS borrowmanage(
	borrow_id INT PRIMARY KEY AUTO_INCREMENT,#Borrowing serial number
	reader_id VARCHAR(30) ,#borrower id
	book_id INT NOT NULL,#book id
	lend_date DATE NOT NULL,#book lend date
	back_date DATE NOT NULL,#book back date
	ispay INT DEFAULT 0, # 0代表未付款，1代表已付款
	CONSTRAINT fk_members_borrow FOREIGN KEY(reader_id) REFERENCES members(id),
	CONSTRAINT fk_book_borrow FOREIGN KEY(book_id) REFERENCES book(bid)
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE IF NOT EXISTS login_ticket(
	id INT PRIMARY KEY AUTO_INCREMENT,
	mem_id VARCHAR(30) NOT NULL,
	ticket VARCHAR(50) NOT NULL,
	`status` INT DEFAULT 1,#0:available,1:disavailable
	expired TIMESTAMP NOT NULL
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE reservation( 
	`resv_id` INT NOT NULL AUTO_INCREMENT, 
	`reader_id` VARCHAR(30) NOT NULL, 
	`book_id` INT NOT NULL, 
	`time` TIMESTAMP NOT NULL COMMENT 'time of reservation', 
	notify INT DEFAULT 0,	# 如果为1，表明通知用户取书
	PRIMARY KEY (`resv_id`),
	# 级联删除，确保完整性不会被破坏
	CONSTRAINT fk_members_reservation FOREIGN KEY(reader_id) REFERENCES members(id)
		ON DELETE CASCADE,
	CONSTRAINT fk_book_reservation FOREIGN KEY(book_id) REFERENCES book(bid)
		ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

