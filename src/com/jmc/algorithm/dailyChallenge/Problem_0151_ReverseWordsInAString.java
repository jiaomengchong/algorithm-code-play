package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/reverse-words-in-a-string/
 */
public class Problem_0151_ReverseWordsInAString {
    public static String reverseWords(String s) {
        // 输入：s = "the sky is blue"
        // 输出："blue is sky the"
        String[] str = s.trim().split(" ");
        StringBuffer sb = new StringBuffer();
        for (int i = str.length - 1; i >= 0; i--) {
            if (!str[i].trim().equals("")) {
                sb.append(str[i]);
                if (i != 0) {
                    sb.append(" ");
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "a good   example";
        System.out.println(reverseWords(s));
    }
}
