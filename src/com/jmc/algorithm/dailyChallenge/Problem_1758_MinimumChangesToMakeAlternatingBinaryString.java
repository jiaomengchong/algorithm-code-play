package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/minimum-changes-to-make-alternating-binary-string/
 */
public class Problem_1758_MinimumChangesToMakeAlternatingBinaryString {
    public static int minOperations(String s) {
        int ans1 = 0;
        char pre = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            if (pre == s.charAt(i)) {
                ans1++;
                pre = pre == '1' ? '0' : '1';
            } else {
                pre = s.charAt(i);
            }
        }

        int ans2 = 1;
        pre = s.charAt(0) == '1' ? '0' : '1';
        for (int i = 1; i < s.length(); i++) {
            if (pre == s.charAt(i)) {
                ans2++;
                pre = pre == '1' ? '0' : '1';
            } else {
                pre = s.charAt(i);
            }
        }
        return Math.min(ans1, ans2);
    }
}
