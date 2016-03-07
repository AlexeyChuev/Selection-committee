package net.chuiev.selcommittee.entity;

import java.io.Serializable;

/**
 * Created by Alex on 3/7/2016.
 */
public class Administrator implements Serializable {
    private static final long serialVersionUID = -1922223033971552783L;

    private int id;
    private int userId;

    public Administrator(int id, int userId) {
        this.id = id;
        this.userId = userId;
    }

    public Administrator(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "id=" + id +
                ", userId=" + userId +
                '}';
    }
}
