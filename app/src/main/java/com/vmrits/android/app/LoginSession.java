package com.vmrits.android.app;

public class LoginSession {
    public static String getIsvalidUser() {
        return ISVALID_USER;
    }

    public static void setIsvalidUser(String isvalidUser) {
        ISVALID_USER = isvalidUser;
    }

    private static String ISVALID_USER="false";
}
