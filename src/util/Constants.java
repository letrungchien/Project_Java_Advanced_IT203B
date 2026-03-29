package util;

import java.math.BigDecimal;

public class Constants {

    // ====== DB settings ======
    public static final String DB_URL =
            "jdbc:mysql://localhost:3306/cyber?useSSL=false&serverTimezone=UTC";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "123456";

    // ====== Roles ======
    public static final String ROLE_CUSTOMER = "CUSTOMER";
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_STAFF = "STAFF";

    // ====== Order Status ======
    public static final String STATUS_PENDING = "PENDING";
    public static final String STATUS_CONFIRMED = "CONFIRMED";
    public static final String STATUS_SERVING = "SERVING";
    public static final String STATUS_DONE = "DONE";

    // ====== PC Status ======
    public static final String PC_STATUS_AVAILABLE = "AVAILABLE";
    public static final String PC_STATUS_BUSY = "BUSY";
    public static final String PC_STATUS_INACTIVE = "INACTIVE";

    // ====== Default values ======
    public static final BigDecimal ZERO = new BigDecimal("0.00");

    // ====== Date format ======
    public static final String DATETIME_INPUT_FORMAT = "yyyy-MM-dd HH:mm";
}