package com.dimensiondata.jaxb.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "server"
})
@XmlRootElement(name = "servers", namespace = "http://www.opsource.net/simpleapp")
public class Servers {

    @XmlElement(required = true, namespace = "http://www.opsource.net/simpleapp")
    protected List<Server> server;

    public Servers() {
    }

    public Servers(List<Server> server) {
        this.server = server;
    }

    public List<Server> getServer() {
        if (server == null) {
            server = new ArrayList<Server>();
        }
        return this.server;
    }

}
