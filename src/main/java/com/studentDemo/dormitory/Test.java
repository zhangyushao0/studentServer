package com.studentDemo.dormitory;

import com.studentDemo.hibernateutil.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Iterator;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Dormitory dormitory = new Dormitory();
        Repair repair = new Repair();
        Repair repair1=new Repair();
        WaterAndEle waterAndEle = new WaterAndEle();
        WaterAndEle waterAndEle1 = new WaterAndEle();

        dormitory.setStuName("1");
        dormitory.setStuNum(6666);
        repair.setReCampus("546");
        repair.setReCondition("yidf");
        repair.setDormitory(dormitory);
        repair1.setReCampus("88");
        repair1.setReCondition("88");
        repair1.setDormitory(dormitory);
        waterAndEle.setWalDate("df");
        waterAndEle.setWalType("fgd");
        waterAndEle.setDormitory(dormitory);
        waterAndEle1.setWalDate("33");
        waterAndEle1.setWalType("99");
        waterAndEle1.setWalNum(55);
        waterAndEle1.setDormitory(dormitory);

        Session session = HibernateUtil.getSessionFactory().openSession();

        DormitoryDAOImpl dormitoryDAO = new DormitoryDAOImpl();
        /*Dormitory dormitory1=dormitoryDAO.getStuByNum(666);
        //List<Repair> repair2=dormitoryDAO.getStuByNum(666).getRepair();
        repair1.setDormitory(dormitory1);
        waterAndEle1.setDormitory(dormitory1);
        //dormitoryDAO.saveDormi(dormitory);
        //dormitoryDAO.saveDorRepair(repair);
        dormitoryDAO.saveDorRepair(repair1);
       // dormitoryDAO.saveDorWal(waterAndEle);
        dormitoryDAO.saveDorWal(waterAndEle1);*/

        //Dormitory dormitory1=dormitoryDAO.getStuByNum(555);
        //System.out.println(dormitory1);

        List<WaterAndEle> waterAndEles=dormitoryDAO.getAllDorWal();
        for(WaterAndEle waterAndEle2:waterAndEles){
            System.out.println(waterAndEle2);
        }
      //session1.beginTransaction().commit();
        //session1.close();




    }
}
