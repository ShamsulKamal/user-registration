package com.struts.registration.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.struts.registration.domain.User;
import com.struts.registration.exception.ApplicationException;
import com.struts.registration.utils.HibernateUtil;

/**
 *
 * @author Shamsul Kamal
 *
 */
public class UserDaoImpl implements UserDao {
    private final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public User findById(Long id) {
        User user = null;
        try {
            Session session = HibernateUtil.currentSession();
            user = (User) session.get(User.class, id);
        } catch (HibernateException e) {
            logger.error("No User found with id " + id, e);
            throw new ApplicationException("No User found with id " + id, e);
        }
        finally {
            HibernateUtil.closeSession();
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        Session session = HibernateUtil.currentSession();
        Criteria criteria = session.createCriteria(User.class);
        return criteria.list();
    }

    @Override
    public User save(User entity) {
        Session session = HibernateUtil.currentSession();
        Transaction txn = null;
        try {
            txn = session.beginTransaction();
            session.save(entity);
            txn.commit();
        } catch (Exception e) {
            if (txn != null) {
                txn.rollback();
            }
            throw new ApplicationException("User " + entity.getFirstName() + " unable to save", e);
        }
        finally {
            HibernateUtil.closeSession();
        }
        return entity;
    }

    @Override
    public User update(User entity) {
        Session session = HibernateUtil.currentSession();
        Transaction txn = null;
        try {
            txn = session.beginTransaction();
            session.update(entity);
            txn.commit();
        } catch (Exception e) {
            if (txn != null) {
                txn.rollback();
            }
            throw new ApplicationException("User " + entity.getFirstName() + " unable to update", e);
        }
        finally {
            HibernateUtil.closeSession();
        }
        return entity;
    }

    @Override
    public void delete(User entity) {
        Session session = HibernateUtil.currentSession();
        Transaction txn = null;
        try {
            txn = session.beginTransaction();
            session.delete(entity);
            txn.commit();
        } catch (HibernateException e) {
            if (txn != null) {
                txn.rollback();
            }
            throw new ApplicationException("User " + entity.getFirstName() + " unable to delete", e);
        }
        finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public User findByIdAndUuid(Long id, String uuid) {
        String sql = "from User where id = :id and uuid = :uuid";
        Session session = HibernateUtil.currentSession();
        User user = (User) session.createQuery(sql)
            .setParameter("id", id)
            .setParameter("uuid", uuid)
            .uniqueResult();

        if (user == null) {
            throw new ApplicationException("User with id " + id + " and uuid " + uuid + " can't be found");
        }
        return user;
    }
}
