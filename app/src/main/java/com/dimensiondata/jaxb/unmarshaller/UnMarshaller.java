package com.dimensiondata.jaxb.unmarshaller;

import java.io.InputStream;

public interface UnMarshaller {
    <T> T unMarshal(Class<T> docClass, InputStream inputStream);
}
