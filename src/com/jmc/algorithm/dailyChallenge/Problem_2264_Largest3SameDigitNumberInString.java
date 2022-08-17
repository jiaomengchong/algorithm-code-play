package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/largest-3-same-digit-number-in-string/
 */
public class Problem_2264_Largest3SameDigitNumberInString {
    public static String largestGoodInteger(String num) {
        int ans = Integer.MIN_VALUE;
        //输入：num = "89512244403333347431118000333600089144318733366000334093337711191111155870967022201166153829162"
        //输出："444"
        int N = num.length();
        int right = 0;
        for (int left = 0; left < N; left++) {
            right = Math.max(left, right);
            while (left + 1 < N && right < N && right - left < 3 && num.charAt(left) == num.charAt(left + 1)) {
                right++;
            }
            if (right - left == 3 && num.charAt(left) == num.charAt(left + 1) && num.charAt(left) == num.charAt(left + 2)) {
                ans = Math.max(ans, Integer.parseInt(num.substring(left, right)));
            }
        }
        return ans == Integer.MIN_VALUE ? "" : ans == 0 ? "000" : String.valueOf(ans);
    }

    public static void main(String[] args) {
        String num = "89512244403333347431118000333600089144318733366000334093337711191111155870967022201166153829162";
        System.out.println(largestGoodInteger(num));
    }
}
