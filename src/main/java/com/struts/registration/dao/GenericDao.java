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
    private final Class<T> persistentClass;
    private Session session;

    @SuppressWarnings("unchecked")
    public GenericDao() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected Session getSession() {
        if (session == null) {
            session = HibernateUtil.getSessionFactory().openSession();
        }
        return session;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T findById(ID id) {
        T entity;
        try {
            entity = (T) getSession().get(persistentClass, id);
            if (entity == null) {
                throw new ApplicationException("No entity found with id " + id);
            }
        } catch (HibernateException e) {
            logger.error("No entity found with id {}", id, e);
            throw new ApplicationException("No entity found with id " + id, e);
        }
        return entity;
    }

    @Override
    public List<T> findAll() {
        return findByCriteria();
    }

    @Override
    public T save(T entity) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ApplicationException(entity.getClass().getSimpleName() + " was unable to save", e);
        }
        return entity;
    }

    @Override
    public void delete(T entity) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ApplicationException(entity.getClass().getSimpleName() + " was unable to delete", e);
        }
    }

    /**
     * Use this inside subclasses as a convenience method.
     */
    @SuppressWarnings("unchecked")
    protected List<T> findByCriteria(Criterion... criterion) {
        // FIXME: replace deprecated method
        Criteria crit = getSession().createCriteria(persistentClass);
        for (Criterion c : criterion) {
            crit.add(c);
        }
        return crit.list();
   }
}
