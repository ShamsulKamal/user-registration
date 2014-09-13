package com.struts.registration.domain;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.struts.registration.dao.DaoFactory;
import com.struts.registration.utils.DaoFactoryUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserTest {
    static DaoFactory daoFactory;

    @BeforeClass
    public static void setUpDaoFactory() {
        daoFactory = DaoFactoryUtil.getInstance().getDaoFactory();
    }

    @AfterClass
    public static void shutDownClass() {
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
        System.out.println("test1Save");
        User user = new User();
        user.setFirstName("shamsul");
        user.setLastName("kamal");
        daoFactory.getUserDao().save(user);

        assertEquals("creator", user.getCreatedBy());
        assertEquals("creator", user.getLastUpdatedBy());
    }

    @Test
    public void test2List() {
        System.out.println("test2List");
        List<User> users = daoFactory.getUserDao().findAll();
        assertEquals(users.size(), 1);
    }

    @Test
    public void test3Update() {
        System.out.println("test3Update");
        User user = daoFactory.getUserDao().findById(1L);
        assertNotNull("User not found", user);
        user.setFirstName("Shamsul");
        daoFactory.getUserDao().update(user);

        assertEquals("Shamsul", user.getFirstName());
        assertEquals("updater", user.getLastUpdatedBy());
    }
}
