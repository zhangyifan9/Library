# Version: v1.0
# Date: 2022/4/8
-- Ctrl+Shift+C 注释 SQL 窗口选择内容
-- Ctrl+Shift+R 从选择内容删除注释

/*create database Library*/
DROP DATABASE IF EXISTS Library;
CREATE DATABASE IF NOT EXISTS Library;
USE Library;
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
	card_state  INT  NOT NULL#0:disabled;1:available
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*
create table librarian
Librarian: This table includes basic information for librarians. 
It can implement two functions: book management and reader management.
*/
/*
CREATE TABLE IF NOT EXISTS librarian(
	id VARCHAR(30) PRIMARY KEY,#librarian id
	mname INT NOT NULL,#librarian name
	pwd VARCHAR(30) NOT NULL#librarian passward
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
*/
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
	price INT NOT NULL,#book price
	`type` VARCHAR(30),  # book type
	introduction VARCHAR(100), # book introduction
	barcode  VARCHAR(30),#book barcode
	racknum  VARCHAR(30), #book racknumber
	copiesnum INT, #number of copies
	`total` INT DEFAULT 0 NOT NULL
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*
create table author
There may be more than one author of the book, so create an author table
*/
/*
CREATE TABLE IF NOT EXISTS author(
	id VARCHAR(30) PRIMARY KEY,#book id
	aname VARCHAR(30) NOT NULL#author name
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
*/


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

# test
INSERT INTO borrowmanage (`reader_id`, `book_id`, `lend_date`, `back_date`) 
VALUES ('19030501', '5', CURDATE(), DATE_ADD(CURDATE(), INTERVAL 10 DAY)); 

UPDATE borrowmanage
SET back_date = DATE_ADD(back_date, INTERVAL 10 DAY)
WHERE borrow_id = 3;

DELETE FROM borrowmanage
WHERE borrow_id = 3;


SELECT DATEDIFF(back_date, CURDATE())
FROM borrowmanage
WHERE borrow_id = 5;
SELECT DATEDIFF(back_date, DATE_ADD(back_date, INTERVAL 10 DAY))
FROM borrowmanage
WHERE borrow_id = 5;

UPDATE book
SET copiesnum = copiesnum+1
WHERE bid = 5;

UPDATE book
SET copiesnum = copiesnum-1
WHERE bid = 5;

UPDATE members
SET borrownum = borrownum+1
WHERE id = 19030501;

UPDATE members
SET borrownum = borrownum-1
WHERE id = 19030501;

SELECT copiesnum
FROM book
WHERE bid = 5

SELECT book_id
FROM borrowmanage
WHERE borrow_id = 5;