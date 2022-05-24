package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/add-strings/
 */
public class Problem_0415_AddStrings {
    public static String addStrings(String num1, String num2) {
        // 输入：num1 = "11", num2 = "123"
        // 输出："134"
        int len1 = num1.length() - 1;
        int len2 = num2.length() - 1;
        int carry = 0;
        StringBuffer sb = new StringBuffer();
        while (len1 >= 0 || len2 >= 0 || carry != 0) {
            int n1 = len1 >= 0 ? num1.charAt(len1) - '0' : 0;
            int n2 = len2 >= 0 ? num2.charAt(len2) - '0' : 0;
            int res = n1 + n2 + carry;
            sb.append(res % 10);
            carry = res / 10;
            len1--;
            len2--;
        }
        sb.reverse();
        return sb.toString();
    }

    public static void main(String[] args) {
        String num1 = "11";
        String num2 = "123";
        System.out.println(addStrings(num1, num2));
    }
}
