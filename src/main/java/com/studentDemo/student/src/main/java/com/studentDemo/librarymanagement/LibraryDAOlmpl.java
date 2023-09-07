package com.studentDemo.librarymanagement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import com.studentDemo.hibernateutil.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.sqlite.core.DB;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
public class LibraryDAOlmpl implements LibraryDAO {
    @Override
    public void saveIntroduction(Introduction introduction) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        introduction.setHead("车南大学图书馆简介");
        session.saveOrUpdate(introduction);
        System.out.println("Introduction saveOrUpdata succeeded!");
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void saveInformation(Information information) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        System.out.println("Information openSession Succeeded!");
        System.out.println(information.getHead() + information.getBody());
        session.saveOrUpdate(information);
        System.out.println("Information saveOrUpdata succeeded!");
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void saveRecommendation(Recommendation recommendation) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.saveOrUpdate(recommendation);
        session.getTransaction().commit();
        session.close();
    }
    @Override
    public List<Information> getAllInformation() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Information> informationList = null;
        try {
            String hql = "FROM Information"; // Information 是您的实体类名
            Query<Information> query = session.createQuery(hql, Information.class);
            informationList = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        System.out.println(informationList);
        return informationList;
    }

    @Override
    public Information getInformationByHead(String head) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Information information = null;
        try {
            information = session.get(Information.class, head);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return information;
    }
    @Override
    public Introduction getIntroduction(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Introduction introduction = null;
        String head="车南大学图书馆简介";
        try {
            introduction = session.get(Introduction.class, head);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return introduction;
    }
    @Override
    public Introduction getIntroductionByHead(String head) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Introduction introduction = null;
        try {
            introduction = session.get(Introduction.class, head);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return introduction;
    }
    @Override
    public List<Recommendation> getAllRecommendation(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Recommendation> recommendations = null;
        try {
            String hql = "FROM Recommendation "; // Information 是您的实体类名
            Query<Recommendation> query = session.createQuery(hql, Recommendation.class);
            recommendations = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return recommendations;
    }

    @Override
    public void deleteInformation(String head) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Information information = session.get(Information.class, head);
        if (information != null) {
            session.delete(information);
        }
        session.getTransaction().commit();
        System.out.println("deleteInformation commit finished!");
        session.close();
    }

    @Override
    public void delIntroduction(String head) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Introduction introoduction = session.get(Introduction.class, head);
        if (introoduction != null) {
            session.delete(introoduction);
        }
        session.getTransaction().commit();
        System.out.println("delIntroduction commit finished!");
        session.close();
    }

    public DBooks getDBooksBYisbn(String isbn) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        DBooks dBooks = null;
        try {
            dBooks = session.get(DBooks.class, isbn);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return dBooks;
    }

    public List<DBooks> getDBooksBYname(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<DBooks> dBooksList = null;
        try {
            Query<DBooks> query = session.createQuery("FROM DBooks WHERE bookname LIKE :name", DBooks.class);
            query.setParameter("name", "%" + name + "%");
            dBooksList = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return dBooksList;
    }

    public List<DBooks> getDBooksBYauthor(String author) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<DBooks> dBooksList = null;
        try {
            Query<DBooks> query = session.createQuery("FROM DBooks WHERE author = :author", DBooks.class);
            query.setParameter("author", author);
            dBooksList = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return dBooksList;
    }



       /* public List<BooksDetail> getBooksDetail(String isbn) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = null;
            List<BooksDetail> booksDetailList = null;

            try {
                transaction = session.beginTransaction();
                DBooks dBooks = session.get(DBooks.class, isbn);

                if (dBooks != null) {
                    booksDetailList = dBooks.getBooksdetail();
                } else {
                    System.out.println("No DBooks found for ISBN: " + isbn);
                }

                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            } finally {
                if (session != null) {
                    session.close();
                }
            }

            return booksDetailList;
        }*/

    public List<DBooksDetail> getDBooksDetail(String isbn) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<DBooksDetail> dbooksDetailList = null;
        try {
                Query<DBooksDetail> query = session.createQuery("FROM DBooksDetail WHERE isbn = :isbn", DBooksDetail.class);
                query.setParameter("isbn", isbn);
            dbooksDetailList = query.list();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                session.close();
            }
            return dbooksDetailList;
    }
    public Boolean borrowBook(String bcode, String readername, String readerid) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            // 根据 bcode 查找对应的 DBooksDetail 记录
            DBooksDetail bookDetail = session.get(DBooksDetail.class, bcode);

            if (bookDetail != null) {
                // 检查是否已经借阅
                if (bookDetail.getIsBorrowed()) {
                    System.out.println("该书已被借阅");
                    return false;
                }

                // 设置借阅信息
                bookDetail.setIsborrowed(Boolean.TRUE);
                bookDetail.setReadername(readername);
                bookDetail.setReaderid(readerid);

                // 计算并设置借书到期日期（当前时间后30天）
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime dueDate = now.plusDays(30);
                // 使用 SimpleDateFormat 格式化为 "yyyy-MM-dd HH:mm:ss.SSS" 格式的字符串
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                String formattedDueDate = sdf.format(Date.from(dueDate.atZone(ZoneId.systemDefault()).toInstant()));

                // 设置借书到期日期
                bookDetail.setDue(formattedDueDate);


                session.update(bookDetail); // 更新记录
                transaction.commit(); // 提交事务
                System.out.println("借阅成功！");
                return true; // 操作成功
            } else {
                // 未找到对应的记录
                System.out.println("未找到对应的记录");
                transaction.rollback(); // 回滚事务
                return false; // 操作失败
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false; // 操作失败
        }
    }
    public DBooksDetail getDetail(String bcode){
        Session session = HibernateUtil.getSessionFactory().openSession();

        DBooksDetail dBooksDetail = null;
        try {
            dBooksDetail = session.get(DBooksDetail.class, bcode);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return dBooksDetail;
    }
    public Boolean returnBook(String bcode) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            // 根据 bcode 查找对应的 DBooksDetail 记录
            DBooksDetail bookDetail = session.get(DBooksDetail.class, bcode);

            if (bookDetail != null) {
                // 检查是否已经借阅
                if (!bookDetail.getIsBorrowed()) {
                    System.out.println("该书已被归还");
                    return false;
                }

                // 设置借阅信息
                bookDetail.setIsborrowed(Boolean.FALSE);
                bookDetail.setReadername(null);
                bookDetail.setReaderid(null);
                bookDetail.setDue(null);

                session.update(bookDetail); // 更新记录
                transaction.commit(); // 提交事务
                System.out.println("还书成功！");
                return true; // 操作成功
            } else {
                // 未找到对应的记录
                System.out.println("未找到对应书籍");
                transaction.rollback(); // 回滚事务
                return false; // 操作失败
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false; // 操作失败
        }
    }
    public List<DBooksDetail> getBooksOfReader(String readerid){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<DBooksDetail> dBooksDetails = null;
        try {
            Query<DBooksDetail> query = session.createQuery("FROM DBooksDetail WHERE readerid = :readerid", DBooksDetail.class);
            query.setParameter("readerid", readerid);
            dBooksDetails = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return dBooksDetails;
    }
    public Boolean addBook(DBooks dBooks,DBooksDetail dBooksDetail) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        DBooks dBooks1 = null;
        DBooksDetail dBooksDetail1 =null;
        String isbn=dBooks.getIsbn();
        try {
            dBooks1=session.get(DBooks.class, isbn);
            //System.out.println(dBooks.getIsbn());
            //System.out.println("dbooks get");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            dBooksDetail1=session.get(DBooksDetail.class,dBooksDetail.getBcode());
            //System.out.println(dBooksDetail.getBcode());
            //System.out.println("dbooksdetail get");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("1");
        if (dBooksDetail1 != null) {
            System.out.println("已存在该书籍");

            return false;
        } else if (dBooks1 == null) {
            dBooks.setStock(1);
            session.saveOrUpdate(dBooks);
            //System.out.println("2");
        } else {
            dBooks1.setStock(dBooks1.getStock() + 1);
            session.saveOrUpdate(dBooks1);
            //System.out.println("3");
        }
        dBooksDetail.setIsborrowed(false);
        dBooksDetail.setReadername(null);
        dBooksDetail.setReaderid(null);
        dBooksDetail.setDue(null);
        session.saveOrUpdate(dBooksDetail);
        transaction.commit();
        session.close();
        //System.out.println("4");
        return true;
    }
    public Boolean deleteBook(String bcode){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        DBooks dBooks1 = null;
        DBooksDetail dBooksDetail1 =null;
        try {
            dBooksDetail1=session.get(DBooksDetail.class,bcode);
            //System.out.println(dBooksDetail.getBcode());
            //System.out.println("dbooksdetail get");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(dBooksDetail1==null){
            System.out.println("该书已经被删除");
            return false;
        }
        String isbn=dBooksDetail1.getIsbn();
        try {
            dBooks1=session.get(DBooks.class, isbn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        if(dBooks1.getStock()<=1){
            session.delete(dBooks1);
        }
        else{
            dBooks1.setStock(dBooks1.getStock()-1);
            session.saveOrUpdate(dBooks1);

        }
        session.delete(dBooksDetail1);
        transaction.commit();
        session.close();
        //System.out.println("4");
        return true;
    }
}


