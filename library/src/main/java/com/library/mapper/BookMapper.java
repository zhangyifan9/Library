package com.library.mapper;
import com.library.bean.Book;
import com.library.bean.Copies;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: Zilong Lin
 * @Date: 2022/4/5 14:21
 * @Version: v1.0
 * @Description: Operations related to the Book table
 */
//图书管理的持久层接口
@Mapper//有多个Mapper，可以在启动类配置mapper接口文件的位置，一次性加载所有的mapper
public interface BookMapper {

    //返回图书数
    public Integer findBookCountByName(@Param("name") String name);
    public Integer findBookCountByAuthor(@Param("bauthor") String bauthor);
    public Integer findBookCountByPublished(@Param("published") String published);
    public Integer findBookCountAll();

    /**
     * 根据书名返回书的信息
     * @param name  书名
     * @return 如果找到返回这本书的数据，如果没有找到返回null值
     */
    public List<Book> findBookByname(@Param("name") String name , @Param("offset") Integer offset, @Param("limit") Integer limit);

    /**
     * 根据ISBN码返回书的信息
     * @param isbn  isbe码
     * @return 如果找到返回这本书的数据，如果没有找到返回null值
     */
    public Book findBookByISBN (String isbn);

    /**
     * 根据作者返回该作者的所有书
     * @param bauthor
     * @param offset
     * @param limit
     * @return  返回书列表，若没有找到则返回null
     */
    public List<Book> findBooksByAuthor(@Param("bauthor") String bauthor, @Param("offset") Integer offset, @Param("limit") Integer limit);

    public List<Book> findBooksByPublished(@Param("published") String published,@Param("offset") Integer offset, @Param("limit") Integer limit);


    public List<Book> findAllBooks(@Param("offset") Integer offset, @Param("limit") Integer limit);

    /**
     * 插入图书
     * @param book，图书的数据 这里注意参数中的book_id 前端应使用bookId
     * @return 如果插入成功则返回新插入的book的主键id，（增删改都将受到影响的行数作为返回值，可以根据返回值来判断执行是否成功）
     */
    public Integer insert(Book book);

    /**
     * 修改书的信息，这里的通过先查找到这本书，要修改这本书时，
     * 点击书右侧的修改按钮，填写可修改部分的表格，返回整个表
     * @param book
     * @return 返回0表示修改成功， 返回1表示修改失败
     */
    public Integer changeBook(Book book);

    /**
     * 在数据库中删除书，这里的通过先查找到这本书，要删除这本书时，
     * 点击书右侧的删除按钮
     * @param isbn
     * @return 返回0表示删除成功， 返回1表示删除失败
     */
    public Integer deleteBook(String isbn);



    // copiesnum++
    public int addCopiesNum(String isbn); //只在新增一个书的副本时使用，并非借书还书时修改

    // copiesnum--
    public int reduceCopiesNum(String isbn);//只在删除一个书的副本时使用，并非借书还书时修改
//
//    // 获取指定book_id的copiesnum
//    public int getCopiesNumById(int book_id);
//
//    // 预约数+1
//    public int addResvNum(int book_id);
//
//    // 预约数-1
//    public int reduceResvNum(int book_id);
//
//    // 获取指定书籍的预约数
//    public int getResvNumById(int book_id);

    public int findCountAllCopies(@Param("isbn") String isbn);

    public List<Copies> findAllCopies(@Param("isbn") String isbn,@Param("offset") Integer offset, @Param("limit") Integer limit);

    public int addCopies(@Param("barcode") Integer barcode,@Param("isbn") String isbn, @Param("racknum") String racknum, @Param("reserved") Integer reserved,@Param("borrowed") Integer borrowed);

    public  int deleteCopies(Integer barcode);

    public int reserveCopies(Integer barcode);

    public int cancelReserveCopies(Integer barcode);

    public int borrowCopies(Integer barcode);

    public int cancelBorrowCopies(Integer barcode);

}
