package com.library.service.impl;

import com.library.bean.Book;
import com.library.bean.Copies;
import com.library.bean.ResData;
import com.library.bean.RespPage;
import com.library.mapper.BookMapper;
import com.library.service.BookService;
import com.library.utils.ISBNUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: Zilong Lin
 * @Date: 2022/4/5 18:52
 * @Version: v1.0
 * @Description: Book Service Implement
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public Integer insert(String isbn,String bname, String author, String subject, String introduction, String publishing, String published, String photoUrl, String racknum, Integer total){

        Book result = bookMapper.findBookByISBN(isbn);

        if(result!=null)
        {
            for(int i=0;i<total;i++){
                bookMapper.addCopies(0,isbn,racknum,0,0);//数据库已经存在该书
                bookMapper.addCopiesNum(isbn);
            }
            return 0;
        }
        double price = 10.0;
        Book book = new Book(isbn,bname, author, price, subject, introduction,total,publishing, published, photoUrl);
        Integer rowid =  bookMapper.insert(book);
        for(int i=0;i<total;i++) bookMapper.addCopies(0,isbn,racknum,0,0);

        return 0;

    }


    @Override
    public Integer findBookCountByName(String bName) {
        Integer result = bookMapper.findBookCountByName(bName);
        return result;
    }

    @Override
    public RespPage findAllBooks(Integer page, Integer size) {

        Integer total = bookMapper.findBookCountAll();

        List<Book> books;
        Integer offset = (page-1)*size;
        books = bookMapper.findAllBooks(offset,size);


        return new RespPage(total, books);
    }


    @Override
    public RespPage findBookByname(String bName , Integer page, Integer size)
    {

        Integer total = bookMapper.findBookCountByName(bName);

        List<Book> books;
        Integer offset = (page-1)*size;
        books = bookMapper.findBookByname(bName,offset,size);

        return new RespPage(total, books);
    }

    @Override
    public Book findBookByISBN(String ISBN) {
        Book book = bookMapper.findBookByISBN(ISBN);

        return book;
    }

    @Override
    public RespPage findBooksByAuthor(String author, Integer page, Integer size) {

        Integer total = bookMapper.findBookCountByAuthor(author);

        List<Book> books;
        Integer offset = (page-1)*size;
        books = bookMapper.findBooksByAuthor(author,offset,size);


        return new RespPage(total, books);
    }

    @Override
    public RespPage findBooksByPublished(String published, Integer page, Integer size)
    {
        Integer total = bookMapper.findBookCountByPublished(published);

        List<Book> books;
        Integer offset = (page-1)*size;
        books = bookMapper.findBooksByPublished(published,offset,size);


        return new RespPage(total, books);
    }

    @Override
    public Integer changeBook(Book book) {

        Book result = bookMapper.findBookByISBN(book.getIsbn());

        if(result==null) return 1;//没有该书

        bookMapper.changeBook(book);

        return 0;
    }

    @Override
    public Integer deleteBook(String isbn) {
        Book result = bookMapper.findBookByISBN(isbn);

        if(result==null) return 1;//没有该书

        bookMapper.deleteBook(isbn);

        return 0;
    }


    @Override
    public RespPage findAllCopies(String isbn, Integer page, Integer size) {
        Integer total = bookMapper.findCountAllCopies(isbn);

        List<Copies> copies;
        Integer offset = (page-1)*size;
        copies = bookMapper.findAllCopies(isbn,offset,size);


        return new RespPage(total, copies);
    }

    @Override
    public Integer addCopies(Integer barcode, String isbn, String racknum) {
        bookMapper.addCopies(0,isbn,racknum,0,0);
        return 0;
    }

    @Override
    public Integer deleteCopies(Integer barcode, String isbn) {
        bookMapper.deleteCopies(barcode);
        bookMapper.reduceCopiesNum(isbn);
        return 0;
    }

    @Override
    public Integer reserveCopies(Integer barcode) {
        bookMapper.reserveCopies(barcode);
        return 0;
    }

    @Override
    public Integer cancelReserveCopies(Integer barcode) {
        bookMapper.cancelReserveCopies(barcode);
        return 0;
    }

    @Override
    public Integer borrowCopies(Integer barcode) {
        bookMapper.borrowCopies(barcode);
        return 0;
    }

    @Override
    public Integer cancelBorrowCopies(Integer barcode) {
        bookMapper.cancelBorrowCopies(barcode);
        return 0;
    }





}
