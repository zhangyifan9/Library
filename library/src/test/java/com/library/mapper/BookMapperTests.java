package com.library.mapper;

import com.library.bean.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author: Zilong Lin, Ning Zhang
 * @Date: 2022/4/6 18:19
 * @Version: v1.0
 * @Description: Test Book Mapper
 */

//@MapperScan("com.library.mapper")
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootTest
//@RunWith: 表示启动这个单元测试类，必须传递SpringRunner.class
@RunWith(SpringRunner.class)
public class BookMapperTests {

    /**
     * 单元测试须知，可以单独运行，不需要启动整个项目
     * 1.必须被@Test注解修饰
     * 2.返回值类型必须是void
     * 3.方法的参数列表不指定任何类型
     * 4.必须时public
     */
    @Autowired
    private BookMapper bookMapper;


    @Test
    public void insert()
    {
        Book book = new Book();
        book.setBid(100);
        book.setBname("small dog");
        book.setAuthor("me");
        book.setIsbn("1sa23eqw");
        book.setPrice(100);
        book.setType("ala");
        book.setIntroduction("first book");
        book.setBarcode("1010111000");
        book.setRacknum("101");
        book.setCopiesnum(10);
        book.setTotal(10);
        Integer rows = bookMapper.insert(book);
        System.out.println(rows);
    }

    @Test
    public void changeBook()
    {
        Book book = new Book();
        book.setBid(1);
        book.setBname("small pig");
        book.setAuthor("me");
        book.setIsbn("1sa23");
        book.setPrice(100);
        book.setType("ala");
        book.setIntroduction("first book");
        book.setBarcode("1010111000");
        book.setRacknum("101");
        book.setCopiesnum(10);
        book.setTotal(10);
        Integer rows = bookMapper.changeBook(book);
        System.out.println(rows);
    }

    @Test
    public void deletebook()
    {
        Integer bid = 2;
        Integer rows = bookMapper.deleteBook(bid);
        System.out.println(rows);
    }

    @Test
    public void findbyISBN()
    {
        String ISBN = "1sa23";
        Book book = bookMapper.findBookByISBN(ISBN);
        System.out.println(book);
    }

    @Test
    public void findbybId()
    {

        Book book = bookMapper.findBookByid(2);
        System.out.println(book);
    }

    @Test
    public void findbyname()
    {
        List <Book> book = bookMapper.findBookByname("small dog",0,1);
        System.out.println(book.size());
    }
    @Test
    public void findbyauthor()
    {
        List <Book> book = bookMapper.findBooksByAuthor("me",0,2);
        System.out.println(book.size());
    }
    @Test
    public void counttest()
    {
        Integer ans = bookMapper.findBookCountByAuthor("me");
        System.out.println(ans);
    }

    @Test
    public void testAddCopiesNum(){
        System.out.println(bookMapper.addCopiesNum(5));
    }

    @Test
    public void testReduceCopiesNum(){
        System.out.println(bookMapper.reduceCopiesNum(5));
    }

    @Test
    public void testGetCopiesNumById() {
        System.out.println(bookMapper.getCopiesNumById(5));
    }


}
