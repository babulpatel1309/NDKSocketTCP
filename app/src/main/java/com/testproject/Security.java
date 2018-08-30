package com.testproject;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Security {

    private static KeyPairGenerator kpg;
    private static KeyPair kp;
    private static PublicKey publicKey;
    private static PrivateKey privateKey;
    private static Cipher cipher;
    private static byte[] encryptedBytes;

    public static byte[] RSAEncrypt(final String plain) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        kp = kpg.genKeyPair();
        publicKey = kp.getPublic();
        privateKey = kp.getPrivate();

        cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        encryptedBytes = cipher.doFinal(plain.getBytes());
        System.out.println("EEncrypted?????" + new String(org.apache.commons.codec.binary.Hex.encodeHex(encryptedBytes)));
        return encryptedBytes;
    }

    public static String RSADecrypt(final byte[] bytes) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        encryptedBytes = cipher.doFinal(bytes);
        String decrypted = new String(encryptedBytes);
        System.out.println("DDecrypted?????" + decrypted);
        return decrypted;
    }
}
