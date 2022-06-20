package com.jmc.algorithm.weeklyContest.contest_0298;

import java.util.TreeMap;

/**
 * 测试链接：https://leetcode.cn/contest/weekly-contest-298/problems/longest-binary-subsequence-less-than-or-equal-to-k/
 */
public class Contest_0298_03 {
    public static int longestSubsequence(String s, int k) {
        StringBuffer sb = new StringBuffer();
        char[] str = s.toCharArray();
        TreeMap<Integer, Character> treeMap = new TreeMap<>();
        for (int i = 0; i < s.length(); i++) {
            sb.append(str[i]);
        }

        sb.reverse();
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '0') {
                treeMap.put(i, '0');
            }
        }


        int ans = treeMap.size();
        StringBuffer res = new StringBuffer();
        for (int i = 0; i < treeMap.size(); i++) {
            res.append('0');
        }

        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '1') {
                int floorKey = treeMap.floorKey(i) == null ? 0 : treeMap.floorKey(i) + 1;
                res.insert(floorKey, '1');
                if (getDecimal(res) <= k) {
                    ans++;
                } else {
                    return ans;
                }
            }
        }

        return ans;
    }

    private static int getDecimal(StringBuffer sb) {
        int ans = 0;
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) != '0') {
                ans += 1 << i;
            }
        }
        return ans;
    }

    public static int longestSubsequence1(String s, int k) {
        // 输入：s = "1001010", k = 5 [101]
        // 输出：5
        String sk = Integer.toBinaryString(k);
        int ans = sk.length() - 1;
        int N = s.length();
        if (N < sk.length()) {
            return N;
        }
        if (s.substring(N - sk.length()).compareTo(sk) <= 0) {
            ans++;
        }

        int zeroCount = 0;
        for (int i = N - sk.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                zeroCount++;
            }
        }
        return ans + zeroCount;
    }

    public static void main(String[] args) {
        // "000101010011011001011101111000111111100001011000000100010000111100000011111001000111100111101001111001011101001011011101001011011001111111010011100011110111010000010000010111001001111101100001111"
        //300429827
        // "0111101"
        // 518459120
        String s = "0111101";
        int k = 518459120;
        System.out.println(longestSubsequence(s, k));

        System.out.println(longestSubsequence1(s, k));
    }


}
