package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode-cn.com/problems/add-binary/
 */
public class Problem_0067_AddBinary {
    public static String addBinary(String a, String b) {
        Long numA = Long.parseLong(a, 2);
        Long numB = Long.parseLong(b, 2);
        Long sum = numA + numB;
        return Long.toBinaryString(sum);
    }



    public static void main(String[] args) {
        //     "10100000100100110110010000010101111011011001101110111111111101000000101111001110001111100001101"
        // "110101001011101110001111100110001010100001101011101010000011011011001011101111001100000011011110011"
        String a = "10100000100100110110010000010101111011011001101110111111111101000000101111001110001111100001101";
        String b = "110101001011101110001111100110001010100001101011101010000011011011001011101111001100000011011110011";
        System.out.println(addBinary(a, b));
    }
}
