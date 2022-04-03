package com.jmc.algorithm.weekProblem.class_2022_03_02_week;

/**
 * 来自字节飞书团队
 * 小歪每次会给你两个字符串：
 * 笔记s1和关键词s2，请你写一个函数
 * 判断s2的排列之一是否是s1的子串
 * 如果是，返回true
 * 否则，返回false
 */
public class Code02_StringCheck {

    public static boolean check(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        if (len1 < len2) {
            return false;
        }

        int[] count = new int[256];
        char[] chars2 = s2.toCharArray();
        for (char ch : chars2) {
            count[ch]++;
        }

        int L = 0;
        int R = 0;
        int all = len2;
        char[] chars1 = s1.toCharArray();
        while (R != len1) {
            if (all == 0) {
                return true;
            }

            // 窗口形成
            // 012345
            // xyzabc
            // cba
            if (R - L == len2) {
                if (count[chars1[L++]]++ >= 0) {
                    all++;
                }
            }

            if (--count[chars1[R++]] >= 0) {
                all--;
            }
        }

        return all == 0;
    }

    public static void main(String[] args) {
        //           012345
        String s1 = "xyzaxbcyyyy";
        String s2 = "cybx";
        System.out.println(check(s1, s2));
    }
}
