package com.library.service.impl;

import com.library.bean.Book;
import com.library.bean.RespPage;
import com.library.mapper.BookMapper;
import com.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Integer insert(Book book) {

        Book result = bookMapper.findBookByISBN(book.getIsbn());

        if(result!=null) return 1;//数据库已经存在该书

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


//    @Override
//    public RespPage searchBooksByTitle(String name, Integer page, Integer size) {
//
//        Integer total = bookMapper.findBookCount(name);
//
//        List<Book> books;
//        Integer offset = (page-1)*size;
//        books = bookMapper.getBooksByName(name, offset, size);
//
//        for(int i=0; i<books.size(); i++){
//            List<String> authors = authorMapper.getAuthorById(books.get(i).getBook_id());
//            String str = authors.size()>0? authors.get(0) : "";
//            // for multiple authors, string concatenation is done
//            for(int j=1; j<authors.size(); j++){
//                str = str+", "+authors.get(j);
//            }
//            books.get(i).setAuthor(str);
//        }
//        return new RespPage(total, books);
//    }



}
