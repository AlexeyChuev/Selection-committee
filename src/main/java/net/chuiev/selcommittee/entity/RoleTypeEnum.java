package net.chuiev.selcommittee.entity;

/**
 * Created by Alex on 3/7/2016.
 */
public enum RoleTypeEnum {
    ADMIN, CLIENT;

    public String getName() {
        return name().toLowerCase();
    }
}
