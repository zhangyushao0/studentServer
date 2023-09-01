package com.studentDemo.campusStore;

import com.studentDemo.hibernateutil.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class ProductDAOImplTest {
    private Session session;
    private Transaction transaction;
    private ProductDAOImpl productDAO;

    @Before
    public void setUp() {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        productDAO = new ProductDAOImpl();
    }

    @After
    public void tearDown() {
        // transaction.rollback();
        session.close();
    }

    @Test
    public void testGetProductById() {
        // Create a new product and save it to the database
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(10.0);
        session.saveOrUpdate(product);

        // Get the product by its ID using the DAO
        Product retrievedProduct = productDAO.getProductById(3L);

        // Check that the retrieved product matches the original product
        assertNotNull(retrievedProduct);
        assertEquals(product.getName(), retrievedProduct.getName());
        assertEquals(product.getPrice(), retrievedProduct.getPrice(), 0.001);
    }

    @Test
    public void testGetAllProducts() {
        // Create some new products and save them to the database
        Product product1 = new Product();
        product1.setName("Test Product 1");
        product1.setPrice(10.0);
        Product product2 = new Product();
        product2.setName("Test Product 2");
        product2.setPrice(20.0);
        session.save(product1);
        session.save(product2);
        transaction.commit();
        // Get all products using the DAO
        List<Product> products = productDAO.getAllProducts();

        // Check that the correct number of products were retrieved
        assertEquals(2, products.size());

        // Check that the retrieved products match the original products
        assertEquals(product1.getName(), products.get(0).getName());
        assertEquals(product1.getPrice(), products.get(0).getPrice(), 0.001);
        assertEquals(product2.getName(), products.get(1).getName());
        assertEquals(product2.getPrice(), products.get(1).getPrice(), 0.001);
    }

    @Test
    public void testSaveProduct() {
        // Create a new product and save it using the DAO
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(10.0);
        product.setCategory("test");
        product.setDescription("test");
        product.setImageUrl("test");
        productDAO.saveProduct(product);

        // Get the product by its ID using Hibernate
        Product retrievedProduct = session.get(Product.class, product.getId());

        // Check that the retrieved product matches the original product
        assertNotNull(retrievedProduct);
        assertEquals(product.getName(), retrievedProduct.getName());
        assertEquals(product.getPrice(), retrievedProduct.getPrice(), 0.001);
    }

    @Test
    public void testDeleteProduct() {
        // Create a new product and save it to the database
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(10.0);

        session.save(product);

        // Delete the product using the DAO
        productDAO.deleteProduct(product.getId());

        // Get the product by its ID using Hibernate
        Product retrievedProduct = session.get(Product.class, product.getId());

        // Check that the product was deleted
        assertNull(retrievedProduct);
    }
}