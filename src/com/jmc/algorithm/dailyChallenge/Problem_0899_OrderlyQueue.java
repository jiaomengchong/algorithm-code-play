package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/problems/orderly-queue/
 */
public class Problem_0899_OrderlyQueue {
    public static String orderlyQueue(String s, int k) {
        if (k == 1) {
            StringBuffer sb = new StringBuffer(s);
            String min = s;
            for (int i = 1; i < s.length(); i++) {
                char ch = sb.charAt(0);
                sb.deleteCharAt(0);
                sb.append(ch);
                if (sb.toString().compareTo(min) < 0) {
                    min = sb.toString();
                }
            }
            return min;
        }

        if (k > 2) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            return new String(chars);
        }
        return "";
    }

    public static void main(String[] args) {
        String s = "baaca";
        int k = 3;
        System.out.println(orderlyQueue(s, k));
    }
}
