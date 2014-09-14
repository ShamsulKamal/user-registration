package com.struts.registration.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.struts.registration.exception.ApplicationException;
import com.struts.registration.utils.HibernateUtil;

/**
 * @see DaoFactoryManager
 *
 * @author Shamsul Kamal
 *
 * @param <T> entity
 * @param <ID> id of entity
 */
public abstract class GenericDao<T, ID extends Serializable> implements Dao<T, ID> {
    private final Logger logger = LoggerFactory.getLogger(GenericDao.class);
    private Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public GenericDao() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @SuppressWarnings("unchecked")
    @Override
    public T findById(ID id) {
        T entity;
        try {
            Session session = HibernateUtil.currentSession();
            entity = (T) session.get(persistentClass, id);
        } catch (HibernateException e) {
            logger.error("No User found with id " + id, e);
            throw new ApplicationException("No User found with id " + id, e);
        }
        finally {
            HibernateUtil.closeSession();
        }
        return entity;
    }

    @Override
    public List<T> findAll() {
        return findByCriteria();
    }

    @Override
    public T save(T entity) {
        Session session = HibernateUtil.currentSession();
        Transaction txn = null;
        try {
            txn = session.beginTransaction();
            session.saveOrUpdate(entity);
            txn.commit();
        } catch (Exception e) {
            if (txn != null) {
                txn.rollback();
            }
            throw new ApplicationException(entity.getClass().getSimpleName() + " was unable to save", e);
        }
        finally {
            HibernateUtil.closeSession();
        }
        return entity;
    }

    @Override
    public void delete(T entity) {
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
            throw new ApplicationException(entity.getClass().getSimpleName() + " was unable to delete", e);
        }
        finally {
            HibernateUtil.closeSession();
        }
    }

    /**
     * Use this inside subclasses as a convenience method.
     */
    @SuppressWarnings("unchecked")
    protected List<T> findByCriteria(Criterion... criterion) {
        try {
            Session session = HibernateUtil.currentSession();
            Criteria crit = session.createCriteria(persistentClass);
            for (Criterion c : criterion) {
                crit.add(c);
            }
            return crit.list();
        }
        finally {
            HibernateUtil.closeSession();
        }
   }
}
