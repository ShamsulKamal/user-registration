package com.struts.registration.dao;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Shamsul Kamal
 *
 * @param <T>
 * @param <ID>
 */
public interface Dao<T, ID extends Serializable> {

    T findById(ID id);

    List<T> findAll();

    T save(T entity);

//    T update(T entity);

    void delete(T entity);
}
