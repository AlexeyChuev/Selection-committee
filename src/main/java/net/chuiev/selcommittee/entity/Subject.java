package net.chuiev.selcommittee.entity;

import java.io.Serializable;

/**
 * Created by Алексей on 3/5/2016.
 */
public class Subject implements Serializable {
    private static final long serialVersionUID = -6225323023971292703L;

    private int id;
    private String name;

    public Subject(String name) {

        this.name = name;
    }

    public Subject(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
