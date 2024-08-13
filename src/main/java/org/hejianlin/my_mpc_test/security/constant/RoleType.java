package org.hejianlin.my_mpc_test.security.constant;

public enum RoleType {
    ADMIN("ROLE_admin"), USER("ROLE_user");

    private String roleName;

    RoleType(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
