package com.dimensiondata.hibernate.callback;

import com.dimensiondata.hibernate.HibernateUtil;
import org.hibernate.Session;

public class HibernateSession<T> {
    public T callback(SessionCallback<T> sessionCallback) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            return sessionCallback.callback(session);
        } finally {
            session.close();
        }
    }
}
