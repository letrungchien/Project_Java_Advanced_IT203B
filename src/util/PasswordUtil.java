package util;

import org.mindrot.jbcrypt.BCrypt;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtil {
    private PasswordUtil() {
    }

    /** Hash mật khẩu bằng bcrypt (dùng khi đăng ký). */
    public static String hashBcrypt(String plainText) {
        if (plainText == null) plainText = "";
        return BCrypt.hashpw(plainText, BCrypt.gensalt());
    }

    public static boolean isBcryptHash(String stored) {
        return stored != null && stored.startsWith("$2");
    }

    /** So khớp mật khẩu: bcrypt hoặc plain (tài khoản cũ chưa migrate). */
    public static boolean matches(String plainText, String stored) {
        if (plainText == null) plainText = "";
        if (stored == null) return plainText.isEmpty();
        if (isBcryptHash(stored)) {
            return BCrypt.checkpw(plainText, stored);
        }
        return plainText.equals(stored);
    }

    public static String hashSHA256(String plainText) {
        if (plainText == null) plainText = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(plainText.getBytes(StandardCharsets.UTF_8));
            return toHex(hash);
        } catch (NoSuchAlgorithmException e) {
            // SHA-256 luôn có trong Java chuẩn.
            throw new RuntimeException("SHA-256 algorithm not available", e);
        }
    }

    private static String toHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}