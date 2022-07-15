package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/merge-strings-alternately/
 *
 * @Author jmc
 * @Description
 * @Date 2022/7/15 17:55
 **/
public class Problem_1768_MergeStringsAlternately {
    public static String mergeAlternately(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < Math.min(len1, len2); i++) {
            sb.append(word1.charAt(i)).append(word2.charAt(i));
        }

        if (len1 != len2) {
            sb.append(len1 > len2 ? word1.substring(len2, len1) : word2.substring(len1, len2));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String word1 = "abcd", word2 = "pq";
        System.out.println(mergeAlternately(word1, word2));
    }
}
