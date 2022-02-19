package com.jmc.algorithm.greatOffer.class45;

import java.util.HashSet;

/**
 * 单词规律II
 * 给你一种规律pattern和一个字符串str，请你判断str是否遵循其相同的规律。
 * 这里我们指的是完全遵循，例如pattern里的每个字母和字符串str中每个非空单词之间，
 * 存在着双射的对应规律。双射意味着映射双方一一对应，不会存在两个字符映射到同一个字符串，
 * 也不会存在一个字符分别映射到两个不同的字符串。
 * <p>
 * 示例 1:
 * 输入：pattern="abab", s="redblueredblue"
 * 输出：true
 * 解释：一种可能的映射如下：
 * 'a'->"red"
 * 'b'->"blue
 * <p>
 * 示例 2：
 * 输入：pattern="aaaa", s="asdasdasdasd"
 * 输出：true
 * 解释：一种可能的映射如下：
 * 'a' -> "asd"
 *
 * @Author jmc
 * @Description
 * @Date 2021/11/22 16:18
 **/
public class Problem_0291_WordPatternII {
    public static boolean wordPatternMatch(String pattern, String s) {
        if (pattern.length() > s.length()) {
            return false;
        }

        return process(pattern, s, 0, 0, new String[26], new HashSet<>());
    }

    private static boolean process(String pattern, String s, int pi, int si, String[] map, HashSet<Object> sets) {
        if (pi == pattern.length() && si == s.length()) {
            return true;
        }

        if (pi == pattern.length() || si == s.length()) {
            return false;
        }

        char cha = pattern.charAt(pi);
        String cur = map[cha - 'a'];
        if (cur != null) {
            return cur.length() + si <= s.length()
                    && cur.equals(s.substring(si, si + cur.length()))
                    && process(pattern, s, pi + 1, si + cur.length(), map, sets);
        }

        int end = s.length();
        for (int i = si; i < end; i++) {
            cur = s.substring(si, i + 1);
            if (!sets.contains(cur)) {
                sets.add(cur);
                map[cha - 'a'] = cur;
                if (process(pattern, s, pi + 1, i + 1, map, sets)) {
                    return true;
                }
                sets.remove(cur);
                map[cha - 'a'] = null;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        String pattern = "aabb";
        String s = "xyzabcxzyabc";
        System.out.println(wordPatternMatch(pattern, s));
    }
}
