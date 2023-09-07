package com.studentDemo.librarymanagement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
public interface LibraryDAO {
    //void saveInformation(String head, String body, String year, String month, String day);
    void saveInformation(Information information);//存储公告内容
    void saveIntroduction(Introduction introduction);//存储介绍内容
    void saveRecommendation(Recommendation recommendation);//存储推荐书目内容
    Information getInformationByHead(String head);//查看公告内容
    List<Information> getAllInformation();
    List<Recommendation> getAllRecommendation();//查看推荐书目
    Introduction getIntroduction();
    Introduction getIntroductionByHead(String head);//查看介绍内容
    void deleteInformation(String head);//通过标题删除消息
    void delIntroduction(String head);//通过标题删除图书馆介绍

    DBooks getDBooksBYisbn(String isbn);//通过isbn号索引图书
    List<DBooks> getDBooksBYname(String name);//通过书名索引图书
    List<DBooks> getDBooksBYauthor(String author);//通过作者名索引图书
    List<DBooksDetail> getDBooksDetail(String isbn);//通过isnb号返回查找到的图书的详细信息

    Boolean borrowBook(String bcode,String readername, String readerid);//通过bcode码、借阅者姓名、借阅者id借书
    Boolean returnBook(String bcode);//根据书籍的bcode编码号归还书籍
    DBooksDetail getDetail(String bcode);//管理员根据书籍的编码查看借阅情况
    List<DBooksDetail> getBooksOfReader(String readerid);//根据借阅者的id查看借阅的图书
    //增添一本新书
    Boolean addBook(DBooks dBooks,DBooksDetail dBooksDetail);//添加新书
    Boolean deleteBook(String bcode);//删除书籍
}