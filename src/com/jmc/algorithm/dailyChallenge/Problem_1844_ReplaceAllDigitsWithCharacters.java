package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/replace-all-digits-with-characters/
 *
 * @Author jmc
 * @Description
 * @Date 2022/8/1 14:10
 **/
public class Problem_1844_ReplaceAllDigitsWithCharacters {
    public static String replaceDigits(String s) {
        int N = s.length();
        StringBuffer ans = new StringBuffer();
        for (int i = 0; i < N; i++) {
            if ((i & 1) != 0) {
                ans.append(s.charAt(i - 1));
                ans.append((char) (s.charAt(i - 1) + (s.charAt(i) - '0')));
            }
        }
        if ((N & 1) != 0) {
            ans.append(s.charAt(N - 1));
        }
        return ans.toString();
    }

    public static String replaceDigits2(String s) {
        StringBuffer ans = new StringBuffer(s);
        for (int i = 1; i < s.length(); i += 2) {
            ans.setCharAt(i, (char) (s.charAt(i - 1) + s.charAt(i) - '0'));
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        System.out.println(replaceDigits("a1b2c3d4e"));
        System.out.println(replaceDigits2("a1b2c3d4e"));
    }
}
