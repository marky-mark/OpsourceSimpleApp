package com.dimensiondata.hibernate.callback;

import com.dimensiondata.hibernate.HibernateServiceException;
import com.dimensiondata.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

public class HibernateTransactionSession<T> {
    
    public T callback(SessionCallback<T> sessionCallback) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            T value = sessionCallback.callback(session);
            session.getTransaction().commit();
            return value;
        } catch (ConstraintViolationException e) {
            session.getTransaction().rollback();
            throw new HibernateServiceException(e);
        } finally {
            session.close();
        }
    }
}
