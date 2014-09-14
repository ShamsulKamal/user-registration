package com.struts.registration.dao;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.struts.registration.domain.User;
import com.struts.registration.domain.UserProperties;
import com.struts.registration.exception.ApplicationException;
import com.struts.registration.utils.HibernateUtil;

/**
 *
 * @author Shamsul Kamal
 *
 */
public class UserDaoImpl extends GenericDao<User, Long> implements UserDao {
    private final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public User findByIdAndUuid(Long id, String uuid) {
        logger.debug(">>> findByIdAndUuid");
        String sql = "from User where id = :id and uuid = :uuid";
        Session session = HibernateUtil.currentSession();
        User user = (User) session.createQuery(sql)
            .setParameter(UserProperties.ID, id)
            .setParameter(UserProperties.UUID, uuid)
            .uniqueResult();

        if (user == null) {
            throw new ApplicationException("User with id " + id + " and uuid " + uuid + " can't be found");
        }
        return user;
    }
}
