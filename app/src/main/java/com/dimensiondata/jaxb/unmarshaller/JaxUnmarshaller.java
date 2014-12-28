package com.dimensiondata.jaxb.unmarshaller;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;

public class JaxUnmarshaller implements UnMarshaller {

	public <T> T unMarshal(Class<T> docClass, InputStream inputStream) {
        try {
            JAXBContext jc = JAXBContext.newInstance(docClass);
            Unmarshaller u = jc.createUnmarshaller();
            return (T) u.unmarshal( inputStream );
        } catch (JAXBException e) {
            throw new UnMarshalException(e);
        }
	}
}
