package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/minimum-length-of-string-after-deleting-similar-ends/
 */
public class Problems_1750_MinimumLengthOfStringAfterDeletingSimilarEnds {
    public static int minimumLength(String s) {
        int ans = -1, N = s.length();
        int left = 0, right = N - 1;
        char ch = s.charAt(0);
        while (left < right) {
            if (s.charAt(left) == ch && s.charAt(right) == ch) {
                while (left <= right - 1 && (s.charAt(left + 1) == ch || s.charAt(right - 1) == ch)) {
                    if (s.charAt(left + 1) == ch) {
                        left++;
                    } else {
                        right--;
                    }
                }
                left++;
                right--;
                if (left <= right) {
                    ch = s.charAt(left);
                }
            } else {
                ans = right == left ? 0 : right - left + 1;
                break;
            }
        }
        return ans < 0 ? right >= left ? (right - left + 1) : 0 : ans;
    }
}
