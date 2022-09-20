package com.jmc.algorithm.dailyChallenge;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试链接：https://leetcode.cn/problems/largest-substring-between-two-equal-characters/
 */
public class Problem_1624_LargestSubstringBetweenTwoEqualCharacters {
    public static int maxLengthBetweenEqualCharacters(String s) {
        int ans = -1, N = s.length();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            if (map.containsKey(s.charAt(i))) {
                ans = Math.max(ans, i - map.get(s.charAt(i)) - 1);
            } else {
                map.put(s.charAt(i), i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "aa";
        System.out.println(maxLengthBetweenEqualCharacters(s));
    }
}
