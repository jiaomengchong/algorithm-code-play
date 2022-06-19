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


    public static void main(String[] args) {
        // "000101010011011001011101111000111111100001011000000100010000111100000011111001000111100111101001111001011101001011011101001011011001111111010011100011110111010000010000010111001001111101100001111"
        //300429827
        String s = "000101010011011001011101111000111111100001011000000100010000111100000011111001000111100111101001111001011101001011011101001011011001111111010011100011110111010000010000010111001001111101100001111";
        int k = 300429827;
        System.out.println(longestSubsequence(s, k));

        StringBuffer sb = new StringBuffer("100000");
        System.out.println(getDecimal(sb));
    }


}
