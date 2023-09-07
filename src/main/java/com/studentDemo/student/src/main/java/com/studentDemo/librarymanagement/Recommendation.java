package com.studentDemo.librarymanagement;
import javax.persistence.Entity;
import javax.persistence.Table;

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
@Table(name = "recommendation")
@Inheritance(strategy = InheritanceType.JOINED) // 使用joined策略进行继承
public class Recommendation {
    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="num")
    private int num;

    @Column(name = "name")
    private String name;

    @Column(name="author")
    private String author;

    @Column(name="profile")
    private String profile;
    public String getProfile(){
        return profile;
    }
    public void setProfile(String profile){
        this.profile=profile;
    }
    public int getRBookNum(){
        return num;
    }
    public void setRBookNum(int num){
        this.num=num;
    }
    public String  getRBookName(){
        return name;
    }
    public void setRBookName(String name){
        this.name=name;
    }
    public String getRBookAuthor(){
        return author;
    }
    public void setRBookAuthor(String author){
        this.author=author;
    }
}
