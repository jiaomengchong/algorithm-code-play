package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/implement-strstr/
 *
 * @Author jmc
 * @Description
 * @Date 2022/5/9 16:15
 **/
public class Problem_0028_ImplementStrstr {

    public static int strStr(String s1, String s2) {
        if (s1.length() < s2.length()) {
            return -1;
        }

        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        // 生成next数组
        int[] next = getNext(str2);
        int index1 = 0, index2 = 0;
        while (index1 < str1.length && index2 < str2.length) {
            // s1:aaaaab  s2:aaab
            if (str1[index1] == str2[index2]) {
                index1++;
                index2++;
            } else if (next[index2] != -1) {
                index2 = next[index2];
            } else {
                index1++;
            }
        }
        return index2 == str2.length ? index1 - index2 : -1;
    }

    private static int[] getNext(char[] str2) {
        if (str2 == null || str2.length == 0) {
            return new int[]{-1};
        }
        if (str2.length == 1) {
            return new int[]{-1, 0};
        }

        int[] next = new int[str2.length];
        next[0] = -1;
        next[1] = 0;
        int cnt = 0;
        int index = 2;
        // acdbstacdtfeacdbstacdbk
        //    |     |           ||
        //    |   cnt=9         j?  next[j]=9, next[k] = str[cnt] == str[j] ? next[j] + 1 : 往前跳到cnt,即next[cnt];
        //   cnt=3             str[j]=str[3],所以k的next[?]=4
        while (index < str2.length) {
            if (str2[index - 1] == str2[cnt]) {
                next[index++] = ++cnt;
            } else if (next[cnt] != -1) {
                cnt = next[cnt];
            } else {
                next[index++] = 0;
            }
        }

        return next;
    }

    public static void main(String[] args) {
        // "mississippi"
        // "mississippi"
        String s1 = "mississippi";
        String s2 = "mississippi";
        System.out.println(strStr(s1, s2));
    }
}
