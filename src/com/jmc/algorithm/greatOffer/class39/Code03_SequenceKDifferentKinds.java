package com.jmc.algorithm.greatOffer.class39;

/**
 * 来自百度
 * 给定一个字符串str，一个整数k
 * str子序列的字符种数不超过k种，返回满足条件的子序列种数
 *
 * @Author jmc
 * @Description
 * @Date 2021/9/17 23:37
 **/
public class Code03_SequenceKDifferentKinds {
    public static int ways1(String s, int k) {
        if (s == null || s.length() == 0 || k <= 0) {
            return 0;
        }

        char[] str = s.toCharArray();
        int[] counts = new int[26];
        int N = str.length;
        for (int i = 0; i < N; i++) {
            counts[str[i] - 'a']++;
        }

        return process1(counts, 0, k);
    }

    private static int process1(int[] counts, int index, int rest) {
        if (index == counts.length) {
            return rest == 0 ? 1 : 0;
        }

        int p1 = process1(counts, index + 1, rest);
        int p2 = 0;
        if (rest > 0) {
            p2 = ((1 << counts[index]) - 1) * process1(counts, index + 1, rest - 1);
        }
        return p1 + p2;
    }

    public static void main(String[] args) {
        String s = "aaaaaaaaacdddeffgbc";
        int k = 5;
        System.out.println(ways1(s, k));
    }
}
