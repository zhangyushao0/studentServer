package com.studentDemo.librarymanagement;
import java.util.ArrayList; // 引入 ArrayList 类
public class BookList {
    private ArrayList<Book> books=new ArrayList<Book>();

    public BookList() {
        books.add(new Book("三国演义","罗贯中",19,"小说","O-13-19/31"));
        books.add(new Book("西游记","吴承恩",29,"小说","O-13-12/1"));
        books.add(new Book("红楼梦","曹雪芹",25,"小说","O-13-2/71"));
    }
    public Book getBook(int pos){ //获取某个位置上的书籍
        return books.get(pos);
    }
    public void setBook(int pos,Book book){ //设置某个位置上的书籍
        books.set(pos,book);
    }
    public ArrayList<Book> getBooks(){  //获取书架，便于后面的Operation的使用
        return books;
    }

}
