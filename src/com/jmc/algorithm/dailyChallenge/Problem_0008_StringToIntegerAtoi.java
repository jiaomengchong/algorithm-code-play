package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode-cn.com/problems/string-to-integer-atoi/
 */
public class Problem_0008_StringToIntegerAtoi {
    public static int myAtoi(String s) {
        s = s.trim();
        if (s == null || s.length() == 0) {
            return 0;
        }

        boolean sign = s.charAt(0) == '-' || s.charAt(0) == '+';
        boolean negative = sign ? (s.charAt(0) == '-') : false;
        int start = sign ? 1 : 0;
        if ((sign && s.length() == 1) || !check(s.charAt(start))) {
            return 0;
        }

        StringBuffer sb = new StringBuffer();
        for (int i = start; i < s.length(); i++) {
            if (!check(s.charAt(i))) {
                break;
            }
            if (sb.length() == 0 && s.charAt(i) == '0') {
                continue;
            }
            sb.append(s.charAt(i));
        }

        if (sb.length() == 0) {
            return 0;
        }

        if (sb.length() >= 19) {
            return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }

        long temp = negative ? -Long.parseLong(sb.toString()) : Long.parseLong(sb.toString());
        int res = temp > Integer.MAX_VALUE || temp < Integer.MIN_VALUE ? (negative ? Integer.MIN_VALUE : Integer.MAX_VALUE) : (int) temp;

        return res;
    }

    public static boolean check(char ch) {
        return ch >= '0' && ch <= '9';
    }

    public static void main(String[] args) {
        String s = "  0000000000012345678";
        s = "00000-42a1234";
        System.out.println(myAtoi(s));
    }
}
