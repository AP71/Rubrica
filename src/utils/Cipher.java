package utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


public class Cipher {

    public static byte[] genSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public static byte[] encrypt(byte[] salt, String password) {
        try {
            byte[] messageDigest;
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            messageDigest = md.digest((new String(salt).concat(password)).getBytes());
            return messageDigest;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
