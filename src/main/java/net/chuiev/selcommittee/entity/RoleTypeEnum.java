package net.chuiev.selcommittee.entity;

/**
 * RoleTypeEnum enum. There are only two types of roleType.
 *
 * @author Oleksii Chuiev
 *
 */
public enum RoleTypeEnum {
    ADMIN, CLIENT;

    public String getName() {
        return name().toLowerCase();
    }

}
