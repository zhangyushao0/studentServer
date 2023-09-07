package com.studentDemo.dormitory;

import com.studentDemo.hibernateutil.HibernateUtil;
import com.studentDemo.user.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class DormitoryDAOImpl implements DormitoryDAO{

    @Override//
    public void saveDormi(Dormitory dormitory) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(dormitory);
        session.getTransaction().commit();
        session.close();
    }
    @Override
    public void saveDorRepair(Repair repair) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        //session.saveOrUpdate(repair.getDormitory());
        session.saveOrUpdate(repair);
        session.getTransaction().commit();
        session.close();
    }
    public void saveDorWal(WaterAndEle waterAndEle) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.saveOrUpdate(waterAndEle);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Dormitory getStuByNum(long stuNum) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Dormitory dormitory = session.get(Dormitory.class ,stuNum);
        return dormitory;
    }
    @Override
    public List<Dormitory> getAllStuDor() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Dormitory> dormitories= session.createQuery("FROM Dormitory ", Dormitory.class).list();
        return dormitories;

    }
    @Override
    public List<Repair> getALLDorRe() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Repair> repairs= session.createQuery("FROM Repair ", Repair.class).list();
        return repairs;
    }
    @Override
    public List<WaterAndEle> getAllDorWal() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<WaterAndEle> waterAndEles = session.createQuery("FROM WaterAndEle ", WaterAndEle.class).list();
        return waterAndEles;
    }


    public void deleteDormi(long stuId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Dormitory dormitory = session.get(Dormitory.class, stuId);
        System.out.println(dormitory);
        if (dormitory != null) {
            session.delete(dormitory);
        }
        session.getTransaction().commit();
        session.close();

    }


}
