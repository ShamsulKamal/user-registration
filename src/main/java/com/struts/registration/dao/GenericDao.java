package com.struts.registration.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
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
    private Session session;

    @SuppressWarnings("unchecked")
    public GenericDao() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public void setSession(Session s) {
        this.session = s;
    }

    protected Session getSession() {
        if (session == null)
            session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T findById(ID id) {
        T entity;
        try {
            entity = (T) getSession().load(persistentClass, id);
        } catch (HibernateException e) {
            logger.error("No User found with id " + id, e);
            throw new ApplicationException("No User found with id " + id, e);
        }
        return entity;
    }

    @Override
    public List<T> findAll() {
        return findByCriteria();
    }

    @Override
    public T save(T entity) {
        try {
            getSession().saveOrUpdate(entity);
        } catch (Exception e) {
            throw new ApplicationException(entity.getClass().getSimpleName() + " was unable to save", e);
        }
        return entity;
    }

    @Override
    public void delete(T entity) {
        try {
            getSession().delete(entity);
        } catch (HibernateException e) {
            throw new ApplicationException(entity.getClass().getSimpleName() + " was unable to delete", e);
        }
    }

    /**
     * Use this inside subclasses as a convenience method.
     */
    @SuppressWarnings("unchecked")
    protected List<T> findByCriteria(Criterion... criterion) {
        Criteria crit = getSession().createCriteria(persistentClass);
        for (Criterion c : criterion) {
            crit.add(c);
        }
        return crit.list();
   }
}
