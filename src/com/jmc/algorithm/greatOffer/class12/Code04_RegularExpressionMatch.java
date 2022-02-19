package com.jmc.algorithm.greatOffer.class12;

/**
 * 正则表达式匹配
 * 测试链接：https://leetcode-cn.com/problems/regular-expression-matching
 *
 * @author jmc
 * @version 1.0
 * @date 2021/6/9 16:14
 */
public class Code04_RegularExpressionMatch {
    public static boolean isMatch1(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        return isValid(s, p) && process1(s.toCharArray(), p.toCharArray(), 0, 0);
    }

    private static boolean process1(char[] str, char[] exp, int si, int ei) {
        if (ei == exp.length) {
            return si == str.length ? true : false;
        }
        // ei不是*，包含两部分：ei+1是exp.length, ei+1不是*
        if ((ei + 1 == exp.length) || (exp[ei + 1] != '*')) {
            return si != str.length && (exp[ei] == str[si] || exp[ei] == '.') && process1(str, exp, si + 1, ei + 1);
        }
        // ei+1是*
        // str aaaab
        // exp a*b
        while (si != str.length && (str[si] == exp[ei] || exp[ei] == '.')) {
            if (process1(str, exp, si, ei + 2)) {
                return true;
            }
            si++;
        }
        return process1(str, exp, si, ei + 2);
    }

    public static boolean isMatch2(String s, String p) {
        if (s == null || p == null) {
            return false;
        }

        int[][] dp = new int[s.length() + 1][p.length() + 1];
        return isValid(s, p) && process2(s.toCharArray(), p.toCharArray(), 0, 0, dp);
    }

    private static boolean process2(char[] str, char[] exp, int si, int ei, int[][] dp) {
        if (dp[si][ei] != 0) {
            return dp[si][ei] != -1 ? true : false;
        }

        boolean ans;
        if (ei == exp.length) {
            ans = si == str.length ? true : false;
            dp[si][ei] = ans ? 1 : -1;
            return ans;
        }

        // ei+1不是*, 1.exp.length 2.不是*
        if (ei + 1 == exp.length || (exp[ei + 1] != '*')) {
            ans = si != str.length && (str[si] == exp[ei] || exp[ei] == '.') && process2(str, exp, si + 1, ei + 1, dp);
            dp[si][ei] = ans ? 1 : -1;
            return ans;
        }

        // ei+1是*
        while (si != str.length && (str[si] == exp[ei] || exp[ei] == '.')) {
            if (process2(str, exp, si, ei + 2, dp)) {
                dp[si][ei] = 1;
                return true;
            }
            si++;
        }
        ans = process2(str, exp, si, ei + 2, dp);
        dp[si][ei] = ans ? 1 : -1;
        return ans;
    }

    public static boolean isMatch3(String s, String p) {
        if (s == null || p == null) {
            return false;
        }

        int[][] dp = new int[s.length() + 1][p.length() + 1];
        return isValid(s, p) && process3(s.toCharArray(), p.toCharArray(), 0, 0, dp);
    }

    private static boolean process3(char[] str, char[] exp, int si, int ei, int [][] dp) {
        if (dp[si][ei] != 0) {
            return dp[si][ei] == 1 ? true : false;
        }
        boolean ans;
        if (ei == exp.length) {
            ans = si == str.length ? true : false;
            dp[si][ei] = ans ? 1 : -1;
            return ans;
        }

        // ei+1没有*
        if (ei + 1 == exp.length || exp[ei + 1] != '*') {
            ans = si != str.length && (str[si] == exp[ei] || exp[ei] == '.') && process3(str, exp, si + 1, ei + 1, dp);
            dp[si][ei] = ans ? 1 : -1;
            return ans;
        }
        // ei+1有*
        // ... a  a  a  a  a  b ...
        //     12 13 14 15 16 17
        // ... a  *  b
        // ... 19 20 21 ...
        // (13,19)=(13,21)+(14,21)+(15,21)+(16,21)+(17,21)
        // (12,19)=(12,21)+(13,19)
        if (si != str.length && (str[si] == exp[ei] || exp[ei] == '.')) {
            ans = process3(str, exp, si, ei + 2, dp) || process3(str, exp, si + 1, ei, dp);
            dp[si][ei] = ans ? 1 : -1;
            return ans;
        } else {
            ans = process3(str, exp, si, ei + 2, dp);
            dp[si][ei] = ans ? 1 : -1;
            return ans;
        }
    }

    public static boolean isValid(String s, String p) {
        // 字符串s不能有*和.
        if (s.contains("*") || s.contains(".")) {
            return false;
        }

        char[] chars = p.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '*' && (i == 0 || chars[i - 1] == '*')) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "mississippi";
        String p = "mis*is*.p*.";
        System.out.println(isMatch1(s, p));
        System.out.println(isMatch2(s, p));
        System.out.println(isMatch3(s, p));
    }
}
