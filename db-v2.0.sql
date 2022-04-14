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
	isbn VARCHAR(30) PRIMARY KEY,#book ISBN
	bname VARCHAR(30) NOT NULL,#book name
	author VARCHAR(30) NOT NULL,#one auther for each book
	price DECIMAL(10, 2) NOT NULL,#book price
	type VARCHAR(1000),  # book type
	introduction VARCHAR(2000), # book introduction
	copiesnum INT, #number of copies
	publishing VARCHAR(50),
	published VARCHAR(30),
	photoUrl VARCHAR(100)
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



CREATE TABLE IF NOT EXISTS copies(
	barcode INT AUTO_INCREMENT PRIMARY KEY,
	isbn VARCHAR(30) ,#book ISBN
	racknum  VARCHAR(30), #book racknumber
	reserved INT DEFAULT 0 NOT NULL, # 是否被预约
	borrowed INT DEFAULT 0 NOT NULL# 是否被借出
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



/*
create table borrowmanage
Borrow: This class contains the date when the reader borrowed the book and the date when the book is returned, 
so that the reader can borrow the book, renew the book, return the book, pay the default fee and other operations.
*/
CREATE TABLE IF NOT EXISTS borrowmanage(
	borrow_id INT PRIMARY KEY AUTO_INCREMENT,#Borrowing serial number
	reader_id VARCHAR(30) ,#borrower id
	barcode INT NOT NULL,#book id
	lend_date DATE NOT NULL,#book lend date
	back_date DATE NOT NULL,#book back date
	returned INT DEFAULT 0 NOT NULL, # 是否已经归还，没有归还的放到在借图书中， 已经归还的放到借阅历史中。
	ispay INT DEFAULT 0, # 0代表未付款，1代表已付款
	CONSTRAINT fk_members_borrow FOREIGN KEY(reader_id) REFERENCES members(id),
	CONSTRAINT fk_copies_borrow FOREIGN KEY(barcode) REFERENCES copies(barcode)
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
	barcode INT NOT NULL, 
	`time` TIMESTAMP NOT NULL COMMENT 'time of reservation', 
	PRIMARY KEY (`resv_id`),
	# 级联删除，确保完整性不会被破坏
	CONSTRAINT fk_members_reservation FOREIGN KEY(reader_id) REFERENCES members(id)
		ON DELETE CASCADE,
	CONSTRAINT fk_copies_reservation FOREIGN KEY(barcode) REFERENCES copies(barcode)
		ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE message( 
	id INT NOT NULL AUTO_INCREMENT, 
	to_id VARCHAR(30) NOT NULL, 
	content VARCHAR(1000), 
	status INT DEFAULT 0 NOT NULL, # 0未读，1已读
	create_time TIMESTAMP,
	PRIMARY KEY (id),
	CONSTRAINT fk_members_message FOREIGN KEY(to_id) REFERENCES members(id)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


insert into book values(
	"100001010","小王子","达芬奇","10.5","novel","这是一本我很喜欢的小说",10,"清华出版社","2020-02","www.hao123.com"
);
insert into book values(
	"100001011","语文","达芬奇","10.5","课本","这是一本我很喜欢的小说",10,"北京出版社","2020-03","www.hao124.com"
);

insert into book values(
	"100001012","小王子","达芬as","10.5","novel","这是一本我很喜欢的小说",10,"清华出版社11","2020-06","www.hao123.com"
);
