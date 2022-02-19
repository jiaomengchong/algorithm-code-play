package com.jmc.algorithm.greatOffer.class14;

/**
 * 最长有效括号
 * 测试链接：https://leetcode-cn.com/problems/longest-valid-parentheses/
 *
 * @author jmc
 * @version 1.0
 * @date 2021/6/18 20:28
 */
public class Code01_LongestValidParentheses {
    public static int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }

        char[] str = s.toCharArray();
        int N = str.length;
        // 子串问题，以i位置结尾的有效括号长度 dp[0]=0
        int[] dp = new int[N];
        int ans = 0;
        for (int i = 1; i < N; i++) {
            if (str[i] == ')') {
                // 与当前右括号配对的位置
                int pairIndex = i - dp[i - 1] - 1;
                if (pairIndex >= 0 && str[pairIndex] == '(') {
                    dp[i] = 2 + dp[i - 1] + (pairIndex - 1 >= 0 ? dp[pairIndex - 1] : 0);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = ")()())";
        System.out.println(longestValidParentheses(s));
    }
}
