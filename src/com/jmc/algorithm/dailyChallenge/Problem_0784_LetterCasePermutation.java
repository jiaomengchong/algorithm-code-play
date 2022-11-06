package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试链接：https://leetcode.cn/problems/letter-case-permutation/
 */
public class Problem_0784_LetterCasePermutation {
    public static List<String> letterCasePermutation(String s) {
        List<String> ans = new ArrayList<>();
        char[] str = s.toCharArray();
        dfs(str, 0, ans);
        return ans;
    }

    private static void dfs(char[] str, int index, List<String> ans) {
        while (index < str.length && Character.isDigit(str[index])) {
            index++;
        }
        if (index == str.length) {
            ans.add(new String(str));
            return;
        }
        str[index] ^= 32;
        dfs(str, index + 1, ans);
        str[index] ^= 32;
        dfs(str, index + 1, ans);
    }

    public static void main(String[] args) {
        String s = "3z4";
        System.out.println(letterCasePermutation(s));
    }
}
