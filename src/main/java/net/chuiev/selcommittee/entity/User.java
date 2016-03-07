package net.chuiev.selcommittee.entity;

import java.io.Serializable;

/**
 * Created by Alex on 3/7/2016.
 */
public class User implements Serializable {
    private static final long serialVersionUID = -5167561540013611118L;

    private int id;
    //1 - Admin
    //2 - Client
    private int role;
    private String email;
    private String password;

    public User(int id, int role, String email, String password) {
        this.id = id;
        this.role = role;
        this.email = email;
        this.password = password;
    }

    public User(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", role=" + role +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
