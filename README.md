# 图书管理系统

默认用户：张三

默认管理员：admin  密码：123456

**管理员模块未开发**

## 基本功能

（1）用户可通过相应界面，依据图书的ISBN，Title，Authors，Publisher，PublicationDate，Type的组合条件，查询数据库中的符合条件的书籍，程序需将查询结果以表格的方式展示给用户，用户可选择结果排序的依据。

（2）提供用户相应的方式借书。当用户选择借阅某本书籍时，应查询该书是否已经被借出：如果被借出，则显示信息（“该书已被借出，归还时间XXXXX”）；如该书未被借出，则查阅读者权限（是否超出该读者所能借阅的最大数目书籍），如未超出显示借书成功，并向数据库中增加一条借阅记录，如果超出，则显示信息（“已超过您的最大借阅数目）。

（3）提供用户相应的方式还书。还书成功时，将该次的借阅记录删除。



## 界面展示

![image-20240616010546657](https://engroc.oss-cn-fuzhou.aliyuncs.com/Typora/202406160105807.png)

![image-20240616010722792](https://engroc.oss-cn-fuzhou.aliyuncs.com/Typora/202406160107852.png)

![image-20240616010734666](https://engroc.oss-cn-fuzhou.aliyuncs.com/Typora/202406160107731.png)

![image-20240616010745414](https://engroc.oss-cn-fuzhou.aliyuncs.com/Typora/202406160107487.png)

![image-20240616010756105](https://engroc.oss-cn-fuzhou.aliyuncs.com/Typora/202406160107173.png)

![image-20240616010811316](https://engroc.oss-cn-fuzhou.aliyuncs.com/Typora/202406160108383.png)

![image-20240616010821860](https://engroc.oss-cn-fuzhou.aliyuncs.com/Typora/202406160108922.png)

![image-20240616011103918](https://engroc.oss-cn-fuzhou.aliyuncs.com/Typora/202406160111980.png)

![image-20240616010831187](https://engroc.oss-cn-fuzhou.aliyuncs.com/Typora/202406160108248.png)

![image-20240616010841711](https://engroc.oss-cn-fuzhou.aliyuncs.com/Typora/202406160108773.png)

![image-20240616010851824](https://engroc.oss-cn-fuzhou.aliyuncs.com/Typora/202406160108889.png)

![image-20240616010918191](https://engroc.oss-cn-fuzhou.aliyuncs.com/Typora/202406160109248.png)

![image-20240616010924610](https://engroc.oss-cn-fuzhou.aliyuncs.com/Typora/202406160109666.png)

![image-20240616011023395](https://engroc.oss-cn-fuzhou.aliyuncs.com/Typora/202406160110474.png)