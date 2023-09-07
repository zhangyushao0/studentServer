package com.studentDemo.dormitory;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name ="dormitory1")

public class Dormitory implements Serializable {


    @Column(name = "dorId")
    private long dorId;

    @Column(name="stuName")
    private String stuName;


    @Column(name="stuNum")
    @Id
    private long stuNum;

    @Column(name="dorNum")
    private String dorNum;

    @Column(name="bedNum")
    private long bedNum;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "dor_num" ,referencedColumnName = "stuNum")
    private List<Repair> repair=new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "dor_num1" ,referencedColumnName = "stuNum")
    private List<WaterAndEle> waterAndEle=new ArrayList<>();


    public long getDorId(){return dorId;}
    public void setDorId(long dorId){this.dorId=dorId;}
    public String getStuName(){
        return stuName;
    }
    public void setStuName(String stuName){
        this.stuName=stuName;
    }

    public long getStuNum(){
        return stuNum;
    }
    public void setStuNum(long stuNum){
        this.stuNum=stuNum;
    }

    public String getDorNum(){
        return dorNum;
    }
    public void setDorNum(String dorNum){
        this.dorNum=dorNum;
    }

    public long getBedNum(){
        return bedNum;
    }
    public void setBedNum(long bedNum){
        this.bedNum=bedNum;
    }

    public List<Repair> getRepair(){
        return repair;
    }
    public void setRepair(List<Repair> repair){
        this.repair=repair;
    }

    public List<WaterAndEle> getWaterAndEle(){
        return waterAndEle;
    }
    public void setWaterAndEle(List<WaterAndEle> waterAndEle){
        this.waterAndEle=waterAndEle;
    }

    @Override
    public String toString() {
        return "Dormitory{" +
                "dorId=" + dorId +
                ", stuName='" + stuName + '\'' +
                ", stuNum=" + stuNum +
                ", dorNum='" + dorNum + '\'' +
                ", bedNum=" + bedNum +
                '}';
    }
}