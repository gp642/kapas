package com.kapas.util;

import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncryptionUtil {

    private static final String ENCRYPTION_ALGORITHM = "AES/GCM/NoPadding";
    private static final String AES_256_KEY = "AES_256_KEY";
    private static final String AES_256_IV = "AES_256_IV";
    private static final String SECRET_KEY = System.getenv(AES_256_KEY) == null ? System.getProperty(AES_256_KEY) : System.getenv(AES_256_KEY);
    private static final String SECRET_IV = System.getenv(AES_256_IV) == null ? System.getProperty(AES_256_IV) : System.getenv(AES_256_IV);
    private static final int SECRET_KEY_SIZE = 32;
    private EncryptionUtil() {

    }

    public static String encrypt(String value) throws Exception {
        byte[] cipherTextBytes;
        Cipher c = Cipher.getInstance(ENCRYPTION_ALGORITHM);

        if (StringUtils.isEmpty(SECRET_KEY) || SECRET_KEY.length() != SECRET_KEY_SIZE)
            throw new Exception("Please Set AES Env Key of "+SECRET_KEY_SIZE+" characters First");

        if (StringUtils.isEmpty(SECRET_IV) || SECRET_IV.length() != SECRET_KEY_SIZE)
            throw new Exception("Please Set AES Env IV of "+SECRET_KEY_SIZE+" characters First");

        SecretKey secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");
        GCMParameterSpec spec = new GCMParameterSpec(128, SECRET_IV.getBytes(StandardCharsets.UTF_8));
        c.init(Cipher.ENCRYPT_MODE, secretKeySpec, spec);
        cipherTextBytes = c.doFinal(value.getBytes(StandardCharsets.UTF_8));
        return Base64.getUrlEncoder().encodeToString(cipherTextBytes);
    }
}
