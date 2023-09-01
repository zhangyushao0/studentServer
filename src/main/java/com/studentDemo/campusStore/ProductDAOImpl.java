package com.studentDemo.campusStore;

import com.studentDemo.hibernateutil.HibernateUtil;
import org.hibernate.Session;
import java.util.List;

public class ProductDAOImpl {
    public Product getProductById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Product product = session.get(Product.class, id);
        session.close();
        return product;
    }

    public List<Product> getAllProducts() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Product> products = session.createQuery("FROM Product", Product.class).list();
        session.close();
        return products;
    }

    public void saveProduct(Product product) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(product);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteProduct(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Product product = session.get(Product.class, id);
        if (product != null) {
            session.delete(product);
        }
        session.getTransaction().commit();
        session.close();
    }

    public List<Product> getProducts(int page) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Product> products = session.createQuery("FROM Product", Product.class).list();
        int i = 0;
        for (Product product : products) {
            System.out.println(product);
            i++;
            if (i == 10) {
                break;
            }
        }
        return products;
    }
}
