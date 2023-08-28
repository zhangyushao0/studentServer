// BEGIN: 8f7e2b4c3d5a
package com.studentDemo.hibernateutil;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HibernateUtilTest {

    @Test
    public void testSessionFactory() {
        System.out.println("testSessionFactory");
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Assertions.assertNotNull(sessionFactory);
    }
}
// END: 8f7e2b4c3d5a