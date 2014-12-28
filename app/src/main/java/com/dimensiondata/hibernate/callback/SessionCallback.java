package com.dimensiondata.hibernate.callback;

import org.hibernate.Session;

public interface SessionCallback<T> {
    T callback(Session session);
}
