package com.dimensiondata.jaxb.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "server", namespace = "http://www.opsource.net/simpleapp",propOrder = {
		"id",
		"name"
})
public class Server {

	@XmlElement(required = true, namespace = "http://www.opsource.net/simpleapp")
	public String id;
	@XmlElement(required = true, namespace = "http://www.opsource.net/simpleapp")
	public String name;

    public Server() {
    }

    public Server(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}