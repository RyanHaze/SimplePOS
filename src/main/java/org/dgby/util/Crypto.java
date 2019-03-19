package org.dgby.util;

import java.io.*;
import java.nio.file.*;
import java.security.*;
import java.security.spec.*;
import java.util.Base64;

import javax.crypto.*;

public class Crypto {
    private String algorithim;
    private PrivateKey privateKey;
    private PublicKey publicKey;

    public Crypto() {
        this.setAlgorithm("RSA");
    }

    public Crypto(String algorithim) {
        this.setAlgorithm(algorithim);
    }

    private PKCS8EncodedKeySpec getKey(String path) throws IOException {
        byte[] encodedKey = Files.readAllBytes(Paths.get(path));
        return new PKCS8EncodedKeySpec(encodedKey);
    }

    public boolean loadPrivateKey(String path) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        PKCS8EncodedKeySpec keySpec = getKey(path);
        if (keySpec != null) {
            this.privateKey = KeyFactory.getInstance(this.algorithim).generatePrivate(keySpec);
            return true;
        }
        this.privateKey = null;
        return false;
    }

    public void unloadPrivateKey() {
        this.privateKey = null;
        // NOTE: The privateKey data is still in memory until the GC runs.
        // Should we use some "unsavory" technique to clear this?
        // For the purpose of this project, we will elect not to.
    }

    public boolean hasPrivateKey() {
        return this.privateKey != null;
    }

    public boolean loadPublicKey(String path) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        PKCS8EncodedKeySpec keySpec = getKey(path);
        if (keySpec != null) {
            this.publicKey = KeyFactory.getInstance(this.algorithim).generatePublic(keySpec);
            return true;
        }
        this.publicKey = null;
        return false;
    }

    public void unloadPublicKey() {
        this.publicKey = null;
    }

    public boolean hasPublicKey() {
        return this.publicKey != null;
    }

    public String getAlgorithm() {
        return this.algorithim;
    }

    public void setAlgorithm(String algorithm) {
        this.unloadPrivateKey();
        this.unloadPublicKey();
        this.algorithim = algorithm;
    }

    public String encrypt(String input) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        return this.bencrypt(input.getBytes());
    }

    public String decrypt(String input) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        return new String(this.bdecrypt(input));
    }

    public String bencrypt(byte[] input) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        if (!this.hasPublicKey())
            return null;

        Cipher cipher = Cipher.getInstance(this.algorithim);
        cipher.init(Cipher.ENCRYPT_MODE, this.publicKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(input));
    }

    public byte[] bdecrypt(String input) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        if (!this.hasPrivateKey())
            return null;

        Cipher cipher = Cipher.getInstance(this.algorithim);
        cipher.init(Cipher.DECRYPT_MODE, this.privateKey);
        return cipher.doFinal(Base64.getDecoder().decode(input));
    }
}