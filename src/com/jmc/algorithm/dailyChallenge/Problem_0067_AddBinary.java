package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode-cn.com/problems/add-binary/
 */
public class Problem_0067_AddBinary {
    public static String addBinary(String a, String b) {
        // 1010
        // 1011
        int maxN = Math.max(a.length(), b.length());
        int minN = Math.min(a.length(), b.length());
        char[] ca = new char[a.length()];
        char[] cb = new char[b.length()];
        int indexA = 0;
        int indexB = 0;

        for (int i = a.length() - 1; i >= 0; i--) {
            ca[indexA++] = a.charAt(i);
        }

        for (int i = b.length() - 1; i >= 0; i--) {
            cb[indexB++] = b.charAt(i);
        }

        boolean carry = false;
        char[] ans = new char[maxN];
        for (int i = 0; i < minN; i++) {
            if ((ca[i] ^ cb[i]) == 0) {
                ans[i] = carry ? '1' : '0';
                carry = ca[i] == '1';
            } else {
                ans[i] = carry ? '0' : '1';
                carry = ans[i] == '0';
            }
        }

        char[] bigChars = a.length() >= b.length() ? ca : cb;
        for (int i = minN; i < maxN; i++) {
            if (carry) {
                ans[i] = bigChars[i] == '1' ? '0' : '1';
                carry = ans[i] == '0';
            } else {
                ans[i] = bigChars[i];
                carry = false;
            }
        }

        char[] res = new char[maxN];
        int index = 0;
        for (int i = maxN - 1; i >= 0; i--) {
            res[index++] = ans[i];
        }
        return carry ? String.format("1%s", String.valueOf(res)) : String.valueOf(res);
    }



    public static void main(String[] args) {
        //     "10100000100100110110010000010101111011011001101110111111111101000000101111001110001111100001101"
        // "110101001011101110001111100110001010100001101011101010000011011011001011101111001100000011011110011"
        String a = "10100000100100110110010000010101111011011001101110111111111101000000101111001110001111100001101";
        String b = "110101001011101110001111100110001010100001101011101010000011011011001011101111001100000011011110011";
        a = "11";
        b =  "1";
        //  100101
        System.out.println(addBinary(a, b));
    }
}
