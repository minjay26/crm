package cn.tendata.crm.admin.web.util;

public abstract class SecurityAccess {

    public static final String PERMISSION_PREFIX = "CRM:";

    public static final String PERMISSION_ADMIN_VIEW = PERMISSION_PREFIX + "ADMIN:VIEW";

    public static final String HAS_PERMISSION_ADMIN_VIEW = "hasAuthority('" + PERMISSION_ADMIN_VIEW + "')";


    public static String hasAuthority(String authority) {
        return "hasAuthority('" + authority + "')";
    }

    private SecurityAccess() {

    }
}
