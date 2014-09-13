package com.struts.registration.dao;

import java.util.List;

public interface Dao<T, ID> {

    T findById(ID id);

    List<T> findAll();

    T save(T entity);

    T update(T entity);

    void delete(T entity);
}
