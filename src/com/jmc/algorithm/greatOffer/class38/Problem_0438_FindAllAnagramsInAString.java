package com.jmc.algorithm.greatOffer.class38;

import java.util.*;

/**
 * 找到字符串中所有字母异位词
 *
 * @Author jmc
 * @Description
 * @Date 2021/9/4 16:26
 **/
public class Problem_0438_FindAllAnagramsInAString {
    public static List<Integer> findAnagrams(String s, String p) {
        if (s == null || s.length() == 0 || p == null || s.length() < p.length()) {
            return new ArrayList<>();
        }

        List<Integer> ans = new ArrayList<>();
        char[] str = s.toCharArray();
        char[] ectopic = p.toCharArray();
        int N = str.length;
        int M = ectopic.length;
        int all = M;
        Map<Character, Integer> map = new HashMap<>();
        // 初始化map，统计欠账表
        for (int i = 0; i < M; i++) {
            if (!map.containsKey(ectopic[i])) {
                map.put(ectopic[i], 1);
            } else {
                map.put(ectopic[i], map.get(ectopic[i]) + 1);
            }
        }

        for (int end = 0; end < M - 1; end++) {
            if (map.containsKey(str[end])) {
                Integer count = map.get(str[end]);
                if (count > 0) {
                    // 有效还款
                    all--;
                }
                map.put(str[end], count - 1);
            }
        }

        for (int start = 0, end = M - 1; start < N && end < N; start++, end++) {
            if (map.containsKey(str[end])) {
                Integer count = map.get(str[end]);
                if (count > 0) {
                    all--;
                }
                map.put(str[end], count - 1);
            }
            if (all == 0) {
                ans.add(start);
            }
            if (map.containsKey(str[start])) {
                if (map.get(str[start]) >= 0) {
                    all++;
                }
                map.put(str[start], map.get(str[start]) + 1);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "cbaebabacd", p = "abc";
        System.out.println((findAnagrams(s, p)));
    }
}
