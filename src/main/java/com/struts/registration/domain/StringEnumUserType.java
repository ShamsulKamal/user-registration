package com.struts.registration.domain;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

/**
 *
 * @author Shamsul Kamal
 * http://web.archive.org/web/20080518065304/http://www.hibernate.org/312.html
 * @param <E>
 */
public class StringEnumUserType<E extends Enum<E>> implements UserType {
    private Class<E> clazz = null;
    private E[] theEnumValues;

    /**
     * Contrary to the example mapping to a VARCHAR, this would
     * @param c the class of the enum.
     * @param e The values of enum (by invoking .values()).
     */
    protected StringEnumUserType(Class<E> c, E[] e) {
        this.clazz = c;
        this.theEnumValues = e;
    }

    private static final int[] SQL_TYPES = {Types.VARCHAR};

    /**
     * simple mapping to a VARCHAR.
     */
    public int[] sqlTypes() {
        return SQL_TYPES;
    }

    public Class returnedClass() {
        return clazz;
    }

    /**
     * From the VARCHAR in the DB, get the enum.  Because there is no
     * Enum.valueOf(class,int) method, we have to iterate through the given enum.values()
     * in order to find the correct "int".
     */
    public Object nullSafeGet(ResultSet resultSet, String[] names, SessionImplementor sessionImplementor,  Object owner)
        throws HibernateException, SQLException {
        String name = resultSet.getString( names[0] );
        E result = null;
        if (!resultSet.wasNull()) {
            try {
                for(int i=0; i < theEnumValues.length && result == null; i++) {
                    result = theEnumValues[i].valueOf(clazz, name);
//                    if (theEnumValues[i].name().equals(name)) {
//                        result = theEnumValues[i];
//                    }
                }
            } catch (SecurityException e) {
                result = null;
            } catch (IllegalArgumentException e) {
                result = null;
            }
        }
        return result;
    }

    /**
     * set the VARCHAR in the DB based on enum.name() value, BEWARE this
     * could change.
     */
    public void nullSafeSet(PreparedStatement preparedStatement,
      Object value, int index, SessionImplementor sessionImplementor) throws HibernateException, SQLException {
        if (value == null) {
            preparedStatement.setNull(index, Types.VARCHAR);
        } else {
            preparedStatement.setString(index, ((Enum)value).name());
        }
    }

    public Object deepCopy(Object value) throws HibernateException{
        return value;
    }

    public boolean isMutable() {
        return false;
    }

    public Object assemble(Serializable cached, Object owner)
        throws HibernateException {
         return cached;
    }

    public Serializable disassemble(Object value) throws HibernateException {
        return (Serializable)value;
    }

    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original;
    }
    public int hashCode(Object x) throws HibernateException {
        return x.hashCode();
    }
    public boolean equals(Object x, Object y) throws HibernateException {
        if (x == y)
            return true;
        if (null == x || null == y)
            return false;
        return x.equals(y);
    }
}
