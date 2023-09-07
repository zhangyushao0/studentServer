package com.studentDemo.librarymanagement;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
@Entity
@Table(name = "DBooks")
@Inheritance(strategy = InheritanceType.JOINED) // 使用joined策略进行继承
public class DBooks {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="isbn")
    private String isbn;

    @Column(name="bookname")
    private String bookname;

    @Column(name="author")
    private String author;
    @Column(name="price")
    private int price;
    @Column(name="subject")
    private String subject;
    @Column(name="stock")
    private int stock;
    @Column(name="publisher")
    private String publisher;
    @Column(name="genre")
    private String genre;
    @Column(name="brief")
    private String brief;

    public void setIsbn(String isbn){
        this.isbn=isbn;
    }
    public void setBookname(String bookname){
        this.bookname=bookname;
    }
    public void setAuthor(String author){
        this.author=author;
    }
    public void setPrice(int price){
        this.price=price;
    }
    public void setSubject(String subject){
        this.subject=subject;
    }
    public void setStock(int stock){
        this.stock=stock;
    }
    public void setPublisher(String publisher){
        this.publisher=publisher;
    }
    public void setGenre(String genre){
        this.genre=genre;
    }
    public void setBrief(String brief){
        this.brief=brief;
    }
    public  String getIsbn(){return isbn;}
    public  String getBookname(){return bookname;}
    public  String getAuthor(){return author;}
    public  int getPrice(){return price;}
    public  String getSubject(){return subject;}
    public  int getStock(){return  stock;}
    public  String getPublisher(){return publisher;}
    public  String getGenre(){return genre;}
    public  String getBrief(){return brief;}


}
