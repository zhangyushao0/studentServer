package com.studentDemo.dormitory;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name ="repair1")
public class Repair implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reId")
    private long reId;

    @Column(name = "reType")
    private String reType;

    @Column(name = "reCampus")
    private String reCampus;

    @Column(name = "rePlace1")
    private String rePlace;

    @Column(name = "rePhone")
    private String rePhone;

    @Column(name = "reDate")
    private String reDate;

    @Column(name = "reCondition")
    private String reCondition;

    @Column(name = "reEvaluate")
    private String reEvaluate;

    @ManyToOne
    @JoinColumn(name="dor_num")
    private Dormitory dormitory;


    @Override
    public String toString() {
        return "Repair{" +
                "reId=" + reId +
                ", reType='" + reType + '\'' +
                ", reCampus='" + reCampus + '\'' +
                ", rePlace='" + rePlace + '\'' +
                ", rePhone='" + rePhone + '\'' +
                ", reDate='" + reDate + '\'' +
                ", reCondition='" + reCondition + '\'' +
                ", reEvaluate='" + reEvaluate + '\'' +
                '}';
    }

    public  long getReId(){
        return reId;
    }
    public void setReId(long reId){
        this.reId=reId;
    }

    public String getReType()
    {
        return reType;
    }
    public void setReType(String reType){
        this.reType=reType;
    }

    public String getReCampus()
    {
        return reCampus;
    }
    public void setReCampus(String reCampus){
        this.reCampus=reCampus;
    }

    public String getRePlace()
    {
        return rePlace;
    }
    public void setRePlace(String rePlace){
        this.rePlace=rePlace;
    }

    public String getRePhone()
    {
        return rePhone;
    }
    public void setRePhone(String rePhone){
        this.rePhone=rePhone;
    }

    public String getReDate(){
        return reDate;
    }
    public void setReDate(String reDate){
        this.reDate=reDate;
    }

    public String getReCondition(){
        return reCondition;
    }
    public void setReCondition(String reCondition){
        this.reCondition=reCondition;
    }

    public String getReEvaluate(){return  reEvaluate;}
    public void setReEvaluate(String reEvaluate){this.reEvaluate=reEvaluate;}

    public Dormitory setDormitory(){return  dormitory;}
    public void getDormitory(Dormitory dor_num){this.dormitory=dor_num;}


}

