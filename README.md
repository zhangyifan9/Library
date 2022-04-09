# Library
XD Library management System

## 1.library
library主要是springboot的源代码
library\src\main\java\com\library\controller里存放的是controller层的接口文件，定义了接口的参数和返回值
## 2. jar包
jar包是打包好的后端代码，双击可以直接运行，方便前端测试，就是双击不大好关，可能得命令行里去关。
![S4$LC(DRC3IH 4)X$4A ~F8](https://user-images.githubusercontent.com/64180188/162550873-53a30988-034b-4e7b-ba3d-a000fd7eb4d7.png)

## 3.db.sql
db.sql是书库部分

## 4. 后端情况说明
1. 后端写了拦截器，只有在登录之后才能访问后端的相关接口，但是为了方便前端测试，拦截器现在已关闭。
2. 后端接口接受参数时，均接受x-www-form-urlencoded;charset=UTF-8
3. ![image](https://user-images.githubusercontent.com/64180188/162551003-599843a4-d592-457c-9479-ae6fc4bc7a99.png)
4. 后端没有单独写参数的验证，即如果参数不符合后端接口要求，只会返回编译器报错内容，因此尽量前端加一个参数验证。

## 5. 前端
1. 建议一个人先写一个整体框架，包括路由跳转并确定一个整体的布局，其他成员分工写页面。


