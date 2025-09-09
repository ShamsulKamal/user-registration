package com.struts.registration.domain;

import com.struts.registration.dao.DaoFactory;
import com.struts.registration.utils.HibernateUtil;
import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserTest {
    static DaoFactory daoFactory;
    static Session session;

    @BeforeClass
    public static void setUpDaoFactory() {
//        daoFactory = DaoFactoryUtil.getInstance().getDaoFactory();
        daoFactory = DaoFactory.instance(DaoFactory.FACTORY);
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
        Date date = new Date();
        user.setCreatedDate(date);
        user.setLastUpdated(date);
        user.setCreatedBy("creator");
        user.setLastUpdatedBy("creator");

        daoFactory.getUserDao().save(user);

        assertEquals("creator", user.getCreatedBy());
        assertEquals("creator", user.getLastUpdatedBy());
        assertEquals(Gender.MALE, user.getGender());

        List<User> users = daoFactory.getUserDao().findAll();
        assertEquals(1, users.size());

        User user2 = daoFactory.getUserDao().findById(1L);
        assertNotNull(user2);
    }

    private void println(String string) {
        System.out.println(string);
    }
}
