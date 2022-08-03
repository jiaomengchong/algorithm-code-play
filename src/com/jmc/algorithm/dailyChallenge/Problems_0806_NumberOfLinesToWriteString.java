package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/problems/number-of-lines-to-write-string/
 *
 * @Author jmc
 * @Description
 * @Date 2022/8/3 15:43
 **/
public class Problems_0806_NumberOfLinesToWriteString {
    public static int[] numberOfLines(int[] widths, String s) {
        int N = s.length();
        int[] ans = new int[2];
        int row = 1, col = 0;
        for (int i = 0; i < N; i++) {
            if (col + widths[s.charAt(i) - 'a'] > 100) {
                row++;
                col = widths[s.charAt(i) - 'a'];
            } else {
                col += widths[s.charAt(i) - 'a'];
            }
        }
        ans[0] = row;
        ans[1] = col;
        return ans;
    }

    public static void main(String[] args) {
        int[] widths = new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
        String s = "abcdefghijklmnopqrstuvwxyz";
        int[] ans = numberOfLines(widths, s);
        System.out.println(Arrays.toString(ans));
    }
}
