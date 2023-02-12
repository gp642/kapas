package com.example.kapas.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncryptionUtil {

    private static final String ENCRYPTION_ALGORITHM = "AES";
    private static final String AES_128_KEY = "AES_128_KEY";
    private static final String SECRET_KEY = System.getenv(AES_128_KEY) == null ? System.getProperty(AES_128_KEY) : System.getenv(AES_128_KEY);
    private static final int SECRET_KEY_SIZE = 16;

    private EncryptionUtil() {

    }

    public static String encrypt(String value) throws Exception {
        byte[] cipherTextBytes;
        Cipher c = Cipher.getInstance(ENCRYPTION_ALGORITHM);

        if (SECRET_KEY == null || SECRET_KEY.isEmpty() || SECRET_KEY.length() != SECRET_KEY_SIZE)
            throw new Exception("Please Set AES Env Key of 16 characters First");

        SecretKey secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), ENCRYPTION_ALGORITHM);
        c.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        cipherTextBytes = c.doFinal(value.getBytes(StandardCharsets.UTF_8));
        return Base64.getUrlEncoder().encodeToString(cipherTextBytes);
    }
}
