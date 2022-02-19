package com.jmc.algorithm.greatOffer.class05;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * @author jmc
 * @version 1.0
 * @date 2021/5/8 18:11
 */
public class Hash {
    private MessageDigest hash;

    public Hash(String algorithm) {
        try {
            hash = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String hashCode(String input) {
        return DatatypeConverter.printHexBinary(hash.digest(input.getBytes())).toUpperCase();
    }

    public static void main(String[] args) {
        System.out.println("支持的算法：");
        for (String algorithm : Security.getAlgorithms("MessageDigest")) {
            System.out.println(algorithm);
        }
        System.out.println("===================");
        String input1 = "jiaomengchong1111111111111111111111111";
        String input2 = "jiaomengchong2";
        String input3 = "jiaomengchong3";
        Hash hash = new Hash("SHA-256");
        System.out.println(hash.hashCode(input1));
        System.out.println(hash.hashCode(input2));
        System.out.println(hash.hashCode(input3));
    }
}
