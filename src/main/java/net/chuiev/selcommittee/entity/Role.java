package net.chuiev.selcommittee.entity;

import java.io.Serializable;

/**
 * Created by Alex on 3/7/2016.
 */
public class Role implements Serializable {
    private static final long serialVersionUID = 2200691353927261877L;

    private int id;
    private Enum roleType;

    public Role(int id, Enum roleType) {
        this.id = id;
        this.roleType = roleType;
    }

    public Role() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Enum getRoleType() {
        return roleType;
    }

    public void setRoleType(Enum roleType) {
        this.roleType = roleType;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleType='" + roleType + '\'' +
                '}';
    }
}
