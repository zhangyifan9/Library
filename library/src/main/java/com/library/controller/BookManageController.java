package com.library.controller;

import com.library.annotation.LibrarianRequired;
import com.library.annotation.LoginRequired;
import com.library.bean.Book;
import com.library.bean.RespPage;
import com.library.service.BookService;
import com.library.utils.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Zhang Ning
 * @Date: 2022/4/8 16:30
 * @Version: v1.0
 * @Description: 这里是图书管理员管理图书相关的接口
 */
@RestController
public class BookManageController {

    @Autowired
    @Qualifier("bookServiceImpl")
    private BookService bookService;

    /**
     * 通过书名查找图书接口
     * @param name 书名
     * @param page 前端第几页，默认第一页
     * @param size 前端一页显示多少条信息， 默认10条
     * @return 若找到，则返回N本书的全部信息，以List存放，若没有找到，则返回NULL
     */
//    @LibrarianRequired
//    @LoginRequired
    @RequestMapping(path = "/bookManage/searchByName",method = RequestMethod.POST)
    @CrossOrigin(origins = "*",allowCredentials = "true")
    public RespPage findBookByname(@RequestParam String name, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size){

        return bookService.findBookByname(name,page,size);
    }

    /**
     * 添加图书接口
     * @param book 参数为一个书类实体
     * @return 返回插入结果信息成功或失败
     */
//    @LibrarianRequired
//    @LoginRequired
    @RequestMapping(path = "/bookManage/insert",method = RequestMethod.POST)
    @CrossOrigin(origins = "*",allowCredentials = "true")
    public Map<String,Object> insert(Book book){
        Map<String,Object> map = new HashMap<>();

        Integer ans =  bookService.insert(book);
        if(ans == 1)
        {
            //insert返回结果是1证明数据库中已经存在该书
            map.put("msg","添加失败，数据库已有该书");
        }
        else
        {
            map.put("msg","添加成功");
        }

        return map;
    }

    /**
     * 修改图书接口
     * @param book 参数为一个书类实体，因为无法修改图书的主码id,故前端的修改表单中应该保证主码不能改变
     * @return 返回修改结果信息成功或失败
     */
//    @LibrarianRequired
//    @LoginRequired
    @RequestMapping(path = "/bookManage/changebook",method = RequestMethod.POST)
    @CrossOrigin(origins = "*",allowCredentials = "true")
    public Map<String,Object> changebook(Book book){
        Map<String,Object> map = new HashMap<>();

        Integer ans =  bookService.changeBook(book);
        if(ans == 1)
        {
            //insert返回结果是1证明数据库中已经存在该书
            map.put("msg","修改失败，数据库没有该书");
        }
        else
        {
            map.put("msg","修改成功");
        }

        return map;
    }

    /**
     * 删除图书接口
     * @param bookid 参数为一个书的id，
     * @return 返回删除结果信息成功或失败
     */
//    @LibrarianRequired
//    @LoginRequired
    @RequestMapping(path = "/bookManage/deletebook",method = RequestMethod.POST)
    @CrossOrigin(origins = "*",allowCredentials = "true")
    public Map<String,Object> deletebook(@RequestParam Integer bookid){
        Map<String,Object> map = new HashMap<>();

        Integer ans =  bookService.deleteBook(bookid);
        if(ans == 1)
        {
            //insert返回结果是1证明数据库中已经存在该书
            map.put("msg","删除失败，数据库没有该书");
        }
        else
        {
            map.put("msg","删除成功");
        }

        return map;
    }


    /**
     * 通过book id查找图书接口
     * @param bId 书的主码bid
     * @return 若找到，则返回书的全部信息，若没有找到，则返回NULL
     */
//    @LibrarianRequired
//    @LoginRequired
    @RequestMapping(path = "/bookManage/findBookByid",method = RequestMethod.POST)
    @CrossOrigin(origins = "*",allowCredentials = "true")
    public Book findBookByid(@RequestParam Integer bId){

        return bookService.findBookByid(bId);
    }

    /**
     * 通过书的ISBN码查找图书接口
     * @param ISBN 书的ISBN码
     * @return 若找到，则返回书的全部信息，若没有找到，则返回NULL
     */
//    @LibrarianRequired
//    @LoginRequired
    @RequestMapping(path = "/bookManage/findBookByISBN",method = RequestMethod.POST)
    @CrossOrigin(origins = "*",allowCredentials = "true")
    public Book findBookByISBN(@RequestParam String ISBN){

        return bookService.findBookByISBN(ISBN);
    }

    /**
     * 通过作者查找图书接口
     * 根据作者找书，因为一个作者可能写过多本书，所以这里返回一个书的列表
     * @param author 书的作者
     * @param page  前端显示第一页（数据太多时一页显示不完）默认1
     * @param size  一页数据数量，默认10
     * @return
     */
//    @LibrarianRequired
//    @LoginRequired
    @RequestMapping(path = "/bookManage/findBooksByAuthor",method = RequestMethod.POST)
    @CrossOrigin(origins = "*",allowCredentials = "true")
    public RespPage findBooksByAuthor(@RequestParam String author, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size){

        return bookService.findBooksByAuthor(author,page,size);
    }

}
