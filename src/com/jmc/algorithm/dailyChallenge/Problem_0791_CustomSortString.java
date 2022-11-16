package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/problems/custom-sort-string/description/
 */
public class Problem_0791_CustomSortString {
    public static String customSortString(String order, String s) {
        StringBuffer ans = new StringBuffer();
        int[] ordArray = new int[27];
        int index = 1;
        for (char ch : order.toCharArray()) {
            ordArray[ch - 'a' + 1] = index++;
        }

        Character[] str = new Character[s.length()];
        for (int i = 0; i < s.length(); i++) {
            str[i] = s.charAt(i);
        }
        Arrays.sort(str, (a, b) -> ordArray[a - 'a' + 1] - ordArray[b - 'a' + 1]);
        for (Character ch : str) {
            ans.append(ch);
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        String oder = "disqyr";
        String s = "iwyrysqrdj";
        System.out.println(customSortString(oder, s));
    }
}
