package com.struts.registration.dao;

import com.struts.registration.domain.User;

/**
 *
 * @author Shamsul Kamal
 *
 */
public interface UserDao extends Dao<User, Long> {
    User findByIdAndUuid(Long id, String uuid);
}
