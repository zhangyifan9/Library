package com.library.service;

import com.library.bean.Book;
import com.library.bean.RespPage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: Zilong Lin
 * @Date: 2022/4/5 19:02
 * @Version: v1.0
 * @Description: Test Book Service
 */
@SpringBootTest
public class BookServiceTests {

    @Autowired
    @Qualifier("bookServiceImpl")
    private BookService bookService;

//    @Test
//    void testSearchBooksByTitle(){
//        System.out.println(bookService.searchBooksByTitle("A", 1, 5));
//        System.out.println(bookService.searchBooksByTitle("A", 2, 5));
//    }

    @Test
    void inserttest(){
        try {
            Book book = new Book();
            book.setBid(0);
            book.setBname("cat");
            book.setAuthor("wangzhe");
            book.setIsbn("1");
            book.setPrice(100);
            book.setType("ala");
            book.setIntroduction("first book");
            book.setBarcode("1010111000");
            book.setRacknum("101");
            book.setCopiesnum(10);
            Integer rows = bookService.insert(book);
            System.out.println(rows);
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void changeBooktest(){
        try {
            Book book = new Book();
            book.setBid(1);
            book.setBname("jieshu");
            book.setAuthor("ccmal");
            book.setIsbn("100");
            book.setPrice(100);
            book.setType("ala");
            book.setIntroduction("first book");
            book.setBarcode("1010111000");
            book.setRacknum("101");
            book.setCopiesnum(9);

            Integer rows = bookService.changeBook(book);
            System.out.println(rows);
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void deletetest(){
        try {
            Integer id = 3;

            Integer rows = bookService.deleteBook(id);
            System.out.println(rows);
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }


    @Test
    void testFindBybId(){
        try {
            Book book = bookService.findBookByid(1);
            System.out.println(book.getBname());
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testFindByISBN(){
        try {
            Book book = bookService.findBookByISBN("1sa23");
            System.out.println(book.getBname());
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }


    @Test
    void testFindByName(){
        try {
            RespPage books = bookService.findBookByname("small dog",1,10);
            System.out.println(books.getData());
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testFindByAuthor(){
        try {
            RespPage books = bookService.findBooksByAuthor("me",1,10);
            System.out.println(books.getData());
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testFind(){
        try {
            Integer result = bookService.findBookCountByName("small dog");
            System.out.println(result);
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

}
