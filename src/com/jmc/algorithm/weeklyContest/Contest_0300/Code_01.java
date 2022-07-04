package com.jmc.algorithm.weeklyContest.Contest_0300;

/**
 * @Author jmc
 * @Description
 * @Date 2022/7/4 20:59
 **/
public class Code_01 {
    public static String decodeMessage(String key, String message) {
        int[] decrypt = new int[26];
        int count = 1;
        for (int i = 0; i < key.length(); i++) {
            if (Character.isSpaceChar(key.charAt(i))) {
                continue;
            }
            int index = key.charAt(i) - 'a';
            if (i == 0) {
                decrypt[index] = count++;
            } else if (decrypt[index] == 0) {
                decrypt[index] = count++;
            }
        }
        StringBuffer sb = new StringBuffer();
        for (char ch : message.toCharArray()) {
            if (Character.isSpaceChar(ch)) {
                sb.append(" ");
                continue;
            }
            int index = ch - 'a';
            char c = (char) (decrypt[index] + 'a' - 1);
            sb.append(c);
        }
        return sb.toString();
    }
}
