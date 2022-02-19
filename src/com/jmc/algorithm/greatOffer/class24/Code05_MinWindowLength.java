package com.jmc.algorithm.greatOffer.class24;

/**
 * 给定两个字符串str1、str2
 * 在str1中寻找一个最短子串，能包含str2的所有字符
 * 字符顺序无所谓，str1的这个最短子串也可以包含多余的字符
 * 返回这个最短包含子串的长度
 *
 * @author jmc
 * @version 1.0
 * @date 2021/7/27 17:04
 */
public class Code05_MinWindowLength {

    public static int minLength(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() < str2.length()) {
            return Integer.MAX_VALUE;
        }

        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        // 欠账表
        int[] map = new int[256];
        // 将str2中字符词频统计放入map中
        for (int i = 0; i < s2.length; i++) {
            map[s2[i]]++;
        }

        // 滑动窗口登场
        int ans = Integer.MAX_VALUE;
        int all = s2.length;
        int L = 0;
        int R = 0;
        while (R != s1.length) {
            map[s1[R]]--;
            if (map[s1[R]] >= 0) {
                all--;
            }
            if (all == 0) {
                while (map[s1[L]] < 0) {
                    map[s1[L++]]++;
                }
                ans = Math.min(ans, R - L + 1);
                all++;
                map[s1[L++]]++;
            }
            R++;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    public static void main(String[] args) {
        String str1 = "adabbca";
        String str2 = "acb";
        System.out.println(minLength(str1, str2));
    }
}
