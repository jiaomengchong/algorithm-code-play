package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/check-if-one-string-swap-can-make-strings-equal/
 *
 * @Author jmc
 * @Description
 * @Date 2022/8/1 13:52
 **/
public class Problem_1790_CheckIfOneStringSwapCanMakeStringsEqual {
    public static boolean areAlmostEqual(String s1, String s2) {
        if (s1.equals(s2)) {
            return true;
        }
        int N = s1.length(), count = 0, index1 = 0, index2 = 0;
        for (int i = 0; i < N; i++) {
            if (count > 2) {
                return false;
            }
            if (s1.charAt(i) != s2.charAt(i)) {
                if (index1 == 0) {
                    index1 = i;
                } else {
                    index2 = i;
                }
                count++;
            }
        }
        if (count > 2 || s1.charAt(index2) != s2.charAt(index1) || s1.charAt(index1) != s2.charAt(index2)) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String s1 = "siyolsdcjthwsiplccjbuceoxmpjgrauocx";
        String s2 = "siyolsdcjthwsiplccpbuceoxmjjgrauocx";
        System.out.println(areAlmostEqual(s1, s2));
    }
}
