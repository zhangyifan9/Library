package com.library.service;

import com.library.bean.Book;
import com.library.bean.RespPage;

import java.text.ParseException;

/**
 * @Author: Zilong Lin Ning zhang
 * @Date: 2022/4/5 18:49
 * @Version: v1.0
 * @Description: Book-related operations
 */
//图书馆里模块业务层的实现类
public interface BookService {


    public Integer findBookCountByName(String bName);
    public RespPage findBookByname(String bName, Integer page, Integer size);
    public Book findBookByISBN (String ISBN );
    public RespPage findBooksByAuthor(String bauthor, Integer page, Integer size);
    public Integer insert(String isbn,String bname, String author, String subject, String introduction, String publishing, String published, String photoUrl, String racknum, Integer total);

    public Integer changeBook(Book book);
    public Integer deleteBook(String isbn );

    public RespPage findBooksByPublished(String published, Integer page,Integer size);

    public RespPage findAllBooks(Integer page, Integer size);

    public RespPage findAllCopies(String isbn,Integer page,Integer size );

    public Integer addCopies(Integer barcode, String isbn,String racknum);

    public  Integer deleteCopies(Integer barcode,String isbn);

    public Integer reserveCopies(Integer barcode);

    public Integer cancelReserveCopies(Integer barcode);

    public Integer borrowCopies(Integer barcode);

    public Integer cancelBorrowCopies(Integer barcode);

}
