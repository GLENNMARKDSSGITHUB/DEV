/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.util.enums;

/**
 * This is an enum for UserRoles
 */

public enum UserRoles {
    ROLE_SUPER_ADMIN("RS1", "ROLE_SUPER_ADMIN"),
    ROLE_ADMIN("RS2", "ROLE_ADMIN"),
    ROLE_USER("RS3", "ROLE_USER");

    private final String strRoleId;
    private final String strRole;

    UserRoles(String roleId, String role) {
        this.strRoleId = roleId;
        this.strRole = role;
    }

    public String getStrRole() {
        return strRole;
    }
}
