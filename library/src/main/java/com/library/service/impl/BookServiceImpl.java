package com.library.service.impl;

import com.library.bean.Book;
import com.library.bean.ResData;
import com.library.bean.RespPage;
import com.library.mapper.BookMapper;
import com.library.service.BookService;
import com.library.utils.ISBNUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Integer insert(String isbn, String type, String racknum, Integer total) throws ParseException {

        Book result = bookMapper.findBookByISBN(isbn);

        if(result!=null) return 1;//数据库已经存在该书

        // 添加借助ISBN从第三方API获取图书信息
        ResData resData = ISBNUtils.getBookInfoByISBN(isbn);

        String bname = (String) resData.getData().get("name");
        String author = (String) resData.getData().get("author");

        // 将价格中的元去掉
        String str = (String) resData.getData().get("price");
        int index = str.indexOf("元");   // 获取元的下标，从1开始
        Double price;
        if(index == -1) {
            price = Double.parseDouble(str);
        }
        else {
            price = Double.parseDouble(str.substring(0, index));  // 左闭右开
        }

        String introduction = (String) resData.getData().get("description");
        String publishing = (String) resData.getData().get("publishing");
        String published = (String) resData.getData().get("published");
        String photoUrl = (String) resData.getData().get("photoUrl");

        Book book = new Book(0, bname, author, isbn, price, type, introduction, racknum, total, total, publishing, published, photoUrl);
        Integer rowid =  bookMapper.insert(book);
        return 0;

    }



    @Override
    public Book findBookByid(Integer bId) {

        Book result = bookMapper.findBookByid(bId);

        return result;
    }

    @Override
    public Integer findBookCountByName(String bName) {
        Integer result = bookMapper.findBookCountByName(bName);
        return result;
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
    public Integer changeBook(Book book) {

        Book result = bookMapper.findBookByid(book.getBid());

        if(result==null) return 1;//没有该书

        bookMapper.changeBook(book);

        return 0;
    }

    @Override
    public Integer deleteBook(Integer bId) {
        Book result = bookMapper.findBookByid(bId);

        if(result==null) return 1;//没有该书

        bookMapper.deleteBook(bId);

        return 0;
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
    public RespPage findBooksByPublished(String published, Integer page, Integer size)
    {
        Integer total = bookMapper.findBookCountByPublished(published);

        List<Book> books;
        Integer offset = (page-1)*size;
        books = bookMapper.findBooksByPublished(published,offset,size);


        return new RespPage(total, books);
    }



}
