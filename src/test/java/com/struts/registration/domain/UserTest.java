package com.struts.registration.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.struts.registration.dao.DaoFactory;
import com.struts.registration.utils.HibernateUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserTest {
    static DaoFactory daoFactory;
    static Session session;

    @BeforeClass
    public static void setUpDaoFactory() {
//        daoFactory = DaoFactoryUtil.getInstance().getDaoFactory();
        daoFactory = DaoFactory.instance(DaoFactory.FACTORY);
        HibernateUtil.getSessionFactory().openSession();//.beginTransaction();
    }

    @AfterClass
    public static void shutDownClass() {
        HibernateUtil.getSessionFactory().close();
        daoFactory = null;
    }

//    @Before
//    public void setUp() {
//
//    }
//
//    @After
//    public void shutDown() {
//
//    }

    @Test
    public void test1Save() {
        println("test1Save");
        User user = new User();
        user.setUsername("admin");
        user.setPassword("password");
        user.setEmail("admin@mail.com");
        user.setGender(Gender.MALE);
        user.addHobbyType(HobbyType.SPORT);

        Transaction transaction = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
        daoFactory.getUserDao().save(user);
        transaction.commit();

//        HibernateUtil.getSessionFactory().openSession().getTransaction().commit();

        assertEquals("creator", user.getCreatedBy());
        assertEquals("creator", user.getLastUpdatedBy());
        assertEquals(Gender.MALE, user.getGender());
    }

    @Test
    public void test2List() {
        println("test2List");
        Transaction transaction = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
        List<User> users = daoFactory.getUserDao().findAll();
        transaction.commit();
        assertEquals(users.size(), 1);
    }

    @Test
    public void test3Update() {
        println("test3Update");
        Transaction transaction = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();

        User user = daoFactory.getUserDao().findById(1L);
        assertNotNull("User not found", user);
        user.setPassword("password123");
        daoFactory.getUserDao().save(user);
        
        transaction.commit();

        assertEquals("password123", user.getPassword());
        assertEquals("updater", user.getLastUpdatedBy());
    }

    private void println(String string) {
        System.out.println(string);
    }
}
