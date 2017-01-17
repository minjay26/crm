package cn.tendata.crm.admin.web.util;

public abstract class SecurityAccess {

    public static final String PERMISSION_PREFIX = "CRM:";

    public static final String PERMISSION_ADMIN = PERMISSION_PREFIX + "ADMIN";

    public static final String HAS_PERMISSION_ADMIN_VIEW = "hasAuthority('" + PERMISSION_ADMIN + "')";


    public static String hasAuthority(String authority) {
        return "hasAuthority('" + authority + "')";
    }

    private SecurityAccess() {

    }
}
