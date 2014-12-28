package com.dimensiondata.hibernate;

public class HibernateServiceException extends RuntimeException {
    public HibernateServiceException(Exception e) {
        super(e);
    }
}
