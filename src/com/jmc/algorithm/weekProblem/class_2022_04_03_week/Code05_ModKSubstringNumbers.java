package com.jmc.algorithm.weekProblem.class_2022_04_03_week;

/**
 * 来自微众
 * 4.11笔试
 * 给定n位长的数字字符串和正数k，求该子符串能被k整除的子串个数
 * (n<=1000，k<=100)
 *
 * @Author jmc
 * @Description
 * @Date 2022/4/24 17:49
 **/
public class Code05_ModKSubstringNumbers {
    public static int ways1(String s, int k) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                String st = s.substring(i, j + 1);
                if (Long.valueOf(st) % k == 0) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static int ways2(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int ans = 0;
        int left = 0;
        int right = 0;
        for (int i = 0; i < s.length(); i++) {
            left = i;
            right = right < left ? left : right;
            while (left <= right && right < s.length()) {
                String st = s.substring(left, right + 1);
                if (Long.valueOf(st) % k == 0) {
                    ans++;
                } else if (left != right) {
                    break;
                }
                right++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "12468";
        int k = 4;
        System.out.println(ways1(s, k));
        System.out.println(ways2(s, k));
    }
}
