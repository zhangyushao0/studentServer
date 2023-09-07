package com.studentDemo.librarymanagement;
import javax.persistence.*;
import lombok.Getter;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
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
@Table(name = "dbooksdetail")
@Inheritance(strategy = InheritanceType.JOINED) // 使用joined策略进行继承
public class DBooksDetail {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="bcode")
    private String bcode;
    @Column(name="isbn")
    private String isbn;
    @Column(name="callnum")
    private String callnum;
    @Column(name="location")
    private String location;
    @Column(name="isborrowed")
    private Boolean isborrowed;
    @Column(name="readername")
    private String readername;
    @Column(name="readerid")
    private String readerid;
    @Column(name="bookname")
    private String bookname;

    @Column(name = "due")
    //@Temporal(TemporalType.TIMESTAMP) // 表示保存日期和时间
    private String due;

    public void setIsborrowed(Boolean isborrowed) {
        this.isborrowed = isborrowed;
    }

    public void setBcode(String bcode) {
        this.bcode = bcode;
    }

    public void setCallnum(String callnum) {
        this.callnum = callnum;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public String  getBcode(){return bcode;}
    public String getCallnum(){return callnum;}
    public String getLocation(){return location;}
    public Boolean getIsBorrowed(){return isborrowed;}
    public String getIsbn(){return  isbn;}
    public String getDue() {return due;}

    // Setter方法
    public void setDue(String due) {this.due = due;}

    public String getReadername() {
        return readername;
    }

    public String getReaderid() {
        return readerid;
    }

    // Setter方法
    public void setReadername(String readername) {
        this.readername = readername;
    }

    public void setReaderid(String readerid) {
        this.readerid = readerid;
    }
    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

}
