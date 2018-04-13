package com.accenture.im.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.accenture.im.exception.BusinessFailureException;

public class EncryptionUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(EncryptionUtils.class);

    private static final String KEY_DERIVATION_ALGORITHM = "PBKDF2WithHmacSHA256";
    private static final String HASH_ALGORITHM = "SHA-256";
    private static final Integer ITERATION_COUNT = 5;
    private static final Integer KEY_LENGTH = 256;

    private EncryptionUtils() {
        super();
    }

    public static String derivePassword(String salt, String password) {
        return deriveHashString(salt, password);
    }

    public static String deriveSecretQuestion(String salt, String secretQuestion) {
        return deriveHashString(salt, secretQuestion);
    }

    /**
     * 文字列のハッシュを導出する。
     *
     * @param salt 導出プロセスにて用いるsalt
     * @param str ハッシュ化対象の文字列
     * @return ハッシュ化された文字列
     */
    private static String deriveHashString(String salt, String str) {
        char[] charArray = str.toCharArray();
        byte[] hashedSalt = hashStr(salt);

        PBEKeySpec keySpec = new PBEKeySpec(charArray, hashedSalt, ITERATION_COUNT, KEY_LENGTH);

        SecretKeyFactory factory;
        try {
            factory = SecretKeyFactory.getInstance(KEY_DERIVATION_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e.getMessage());
            throw new BusinessFailureException(e);
        }

        SecretKey key;
        try {
            key = factory.generateSecret(keySpec);
        } catch (InvalidKeySpecException e) {
            LOGGER.error(e.getMessage());
            throw new BusinessFailureException(e);
        }

        StringBuilder result = new StringBuilder(64);
        for (byte b : key.getEncoded()) {
            result.append(String.format("%02x", b & 0xff));
        }

        return result.toString();
    }

    private static byte[] hashStr(String baseStr) {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance(HASH_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e.getMessage());
            throw new BusinessFailureException(e);
        }
        messageDigest.update(baseStr.getBytes(StandardCharsets.UTF_8));
        return messageDigest.digest();
    }
}
