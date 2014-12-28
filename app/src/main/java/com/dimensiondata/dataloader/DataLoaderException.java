package com.dimensiondata.dataloader;

import com.dimensiondata.hibernate.HibernateServiceException;

public class DataLoaderException extends RuntimeException {
    public DataLoaderException(String s) {
        super(s);
    }

    public DataLoaderException(HibernateServiceException e) {
        super(e);
    }
}
