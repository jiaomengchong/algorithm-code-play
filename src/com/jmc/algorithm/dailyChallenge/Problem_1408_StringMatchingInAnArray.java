package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试链接：https://leetcode.cn/problems/string-matching-in-an-array/
 */
public class Problem_1408_StringMatchingInAnArray {
    public static List<String> stringMatching(String[] words) {
        int N = words.length;
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!words[i].equals(words[j]) && words[i].contains(words[j]) && !ans.contains(words[j])) {
                    ans.add(words[j]);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] words = new String[]{"blue","green","bu"};
        System.out.println(stringMatching(words));
    }
}
