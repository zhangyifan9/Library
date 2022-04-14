package com.library.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


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


//    @Test
//    public void insert()
//    {
//        Book book = new Book();
//        book.setBname("small dog");
//        book.setAuthor("me");
//        book.setIsbn("1sa24");
//        book.setPrice(100.10);
//        book.setType("ala");
//        book.setIntroduction("first book");
//        book.setCopiesnum(10);
//        book.setPublished("2022-04-12");
//        book.setPublishing("太阳出版上");
//        book.setPhotoUrl("www.asdasd.cm");
//        Integer rows = bookMapper.insert(book);
//        System.out.println(rows);
//    }
//
//    @Test
//    public void changeBook()
//    {
//        Book book = new Book();
//        book.setBname("small pig");
//        book.setAuthor("me");
//        book.setIsbn("1sa23");
//        book.setPrice(100.10);
//        book.setType("ala");
//        book.setIntroduction("first book");
//        book.setCopiesnum(10);
//        book.setPublished("2022-04-11");
//        book.setPublishing("太阳出版上");
//        book.setPhotoUrl("www.asdasd.cm");
//        Integer rows = bookMapper.changeBook(book);
//        System.out.println(rows);
//    }
//
//    @Test
//    public void deletebook()
//    {
//        String isbn = "1234";
//        Integer rows = bookMapper.deleteBook(isbn);
//        System.out.println(rows);
//    }
//
//    @Test
//    public void findbyISBN()
//    {
//        String ISBN = "1sa23";
//        Book book = bookMapper.findBookByISBN(ISBN);
//        System.out.println(book);
//    }
//
//
//    @Test
//    public void findbookbypublished()
//    {
//        String published = "2022-04-11";
//        List <Book> book = bookMapper.findBooksByPublished(published,0,10);
//        System.out.println(book);
//    }
//
//
//    @Test
//    public void findallbook()
//    {
//
//        List <Book> book = bookMapper.findAllBooks(0,10);
//        System.out.println(book);
//    }
//
//    @Test
//    public void findbyname()
//    {
//        List <Book> book = bookMapper.findBookByname("small dog",0,1);
//        System.out.println(book.size());
//    }
//    @Test
//    public void findbyauthor()
//    {
//        List <Book> book = bookMapper.findBooksByAuthor("me",0,2);
//        System.out.println(book.size());
//    }
//    @Test
//    public void counttest()
//    {
//        Integer ans = bookMapper.findBookCountByAuthor("me");
//        System.out.println(ans);
//    }
//
//    @Test
//    public void testAddCopiesNum(){
//        System.out.println(bookMapper.addCopiesNum("100001010"));
//    }
//
//    @Test
//    public void testReduceCopiesNum(){
//        System.out.println(bookMapper.reduceCopiesNum("100001010"));
//    }

    @Test
    public void testAddcopies(){
        int ans = bookMapper.addCopies(0,"9780140328721","B101, 1st Floor, South Building, 2 A, 5 columns, 1-3 floors",0,0);
        System.out.println(ans);
    }




}