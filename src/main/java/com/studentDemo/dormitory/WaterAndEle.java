package com.studentDemo.dormitory;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;
import java.io.Serializable;

@Getter
@Entity
@Data
@Table(name = "walterAndEle2")
public class WaterAndEle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "walId")
    private long walId;
    @Column(name = "walDate")
    private String walDate;
    @Column(name = "walNum")
    private long walNum;
    @Column(name = "walType")
    private String walType;

    @ManyToOne
    @JoinColumn(name = "dor_num1")
    private Dormitory dormitory;

    @Override
    public String toString() {
        return "WaterAndEle{" +
                "walId=" + walId +
                ", walDate='" + walDate + '\'' +
                ", walNum=" + walNum +
                ", walType='" + walType + '\'' +
                '}';
    }

    public void setWalId(long walId) {
        this.walId = walId;
    }

    public void setWalDate(String walDate) {
        this.walDate = walDate;
    }

    public void setWalNum(long walNum) {
        this.walNum = walNum;
    }

    public long getWalId() {
        return walId;
    }

    public String getWalDate() {
        return walDate;
    }

    public long getWalNum() {
        return walNum;
    }

    public String getWalType() {
        return walType;
    }

    public Dormitory getDormitory() {
        return dormitory;
    }

    public void setWalType(String walType) {
        this.walType = walType;
    }

    public void setDormitory(Dormitory dor_num1) {
        this.dormitory = dor_num1;
    }

}
