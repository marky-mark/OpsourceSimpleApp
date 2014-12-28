package com.dimensiondata.hibernate;

public class HibernateServerServiceException extends RuntimeException {
    public HibernateServerServiceException(Exception e) {
        super(e);
    }
}
