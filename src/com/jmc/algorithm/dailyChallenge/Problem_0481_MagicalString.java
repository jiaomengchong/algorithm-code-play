package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/magical-string/
 */
public class Problem_0481_MagicalString {
    public static int magicalString(int n) {
        int ans = 0;
        char[] str = new char[n + 2];
        str[0] = 1;
        str[1] = str[2] = 2;
        char curChars = 2;
        for (int i = 2, j = 3; j < n; i++) {
            curChars ^= 3;
            str[j++] = curChars;
            if (str[i] == 2) {
                str[j++] = curChars;
            }
        }
        for (int i = 0; i < n; i++) {
            ans += 2 - str[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        //    |
        // 1221121221221121122……
        //   |
        System.out.println(magicalString(6));
    }
}
