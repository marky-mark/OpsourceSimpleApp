package com.dimensiondata.hibernate.server.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table
public class Server {

    @Id
    @NotNull
    @Size(min = 1, max = 128)
	private String id;

    @NotNull
    @Size(min = 1, max = 255)
	private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Server)) return false;

        Server server = (Server) o;

        if (id != null ? !id.equals(server.id) : server.id != null) return false;
        if (name != null ? !name.equals(server.name) : server.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
