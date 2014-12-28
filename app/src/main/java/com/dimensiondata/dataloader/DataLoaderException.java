package com.dimensiondata.dataloader;

import com.dimensiondata.hibernate.HibernateServerServiceException;

public class DataLoaderException extends RuntimeException {
    public DataLoaderException(String s) {
        super(s);
    }

    public DataLoaderException(HibernateServerServiceException e) {
        super(e);
    }
}
