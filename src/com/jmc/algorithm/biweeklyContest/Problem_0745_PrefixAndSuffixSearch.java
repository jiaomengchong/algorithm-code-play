package com.jmc.algorithm.biweeklyContest;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试链接：https://leetcode.cn/problems/prefix-and-suffix-search/
 */
public class Problem_0745_PrefixAndSuffixSearch {
    static class WordFilter {
        public Map<String, Integer> map;
        public WordFilter(String[] words) {
            map = new HashMap<>();
            int N = words.length;
            for (int i = 0; i < N; i++) {
                String word = words[i];
                for (int pre = 1; pre <= word.length(); pre++) {
                    for (int suf = 1; suf <= word.length(); suf++) {
                        String key = String.format("%s_%s", word.substring(0, pre), word.substring(word.length() - suf));
                        map.put(key, i);
                    }
                }
            }
        }

        public int f(String pref, String suff) {
            String key = String.format("%s_%s", pref, suff);
            return map.getOrDefault(key, -1);
        }
    }

    public static void main(String[] args) {
        // 输入
        //["WordFilter", "f"]
        //[[["apple"]], ["a", "e"]]
        //输出
        //[null, 0]
        String[] words = new String[]{"appale"};
        WordFilter wf = new WordFilter(words);
        int ans = wf.f("a", "e");
        System.out.println(ans);
    }
}
