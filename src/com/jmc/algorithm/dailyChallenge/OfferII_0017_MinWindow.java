package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/M1oyTv/
 */
public class OfferII_0017_MinWindow {
    public static String minWindow(String s, String t) {
        // 输入：s = "ADOBECODEBANC", t = "ABC"
        // 输出："BANC"
        // 解释：最短子字符串 "BANC" 包含了字符串 t 的所有字符 'A'、'B'、'C'
        char[] source = s.toCharArray();
        char[] target = t.toCharArray();
        int[] map = new int[256];
        for (int i = 0; i < t.length(); i++) {
            map[target[i]]++;
        }

        int L = 0;
        int R = 0;
        int all = t.length();
        String ans = "";
        while (R != source.length) {
            map[source[R]]--;
            if (map[source[R]] >= 0) {
                all--;
            }
            if (all == 0) {
                while (map[source[L]] < 0) {
                    map[source[L++]]++;
                }
                if (ans.length() == 0 || ans.length() > R - L + 1) {
                    ans = s.substring(L, R + 1);
                }
                all++;
                map[source[L++]]++;
            }
            R++;
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "a";
        String t = "a";
        System.out.println(minWindow(s, t));
    }
}
