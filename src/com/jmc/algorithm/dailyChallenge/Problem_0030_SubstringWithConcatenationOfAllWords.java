package com.jmc.algorithm.dailyChallenge;

import java.util.*;

/**
 * 测试链接：https://leetcode.cn/problems/substring-with-concatenation-of-all-words/
 */
public class Problem_0030_SubstringWithConcatenationOfAllWords {
    public static List<Integer> findSubstring(String s, String[] words) {
        int N = s.length();
        int size = words.length;
        int len = words[0].length();
        List<Integer> ans = new ArrayList<>();
        if (N < size * len) {
            return ans;
        }

        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        for (int i = 0; i <= size; i++) {
            Map<String, Integer> curMap = new HashMap<>();
            for (int j = i * len; j < N; j += len) {
                String subs = s.substring(j, j + len);
                curMap.put(subs, curMap.getOrDefault(subs, 0) + 1);
                if (j >= size * len) {
                    if (!curMap.equals(map)) {
                        break;
                    }
                    // 移除curMap最开始位置的key
                    int start = j - size * len + len;
                    String startStr = s.substring(start, start + len);
                    if (curMap.getOrDefault(startStr, 0) == 1) {
                        curMap.remove(startStr);
                    } else {
                        curMap.put(startStr, curMap.getOrDefault(startStr, 0) - 1);
                    }
                    ans.add(start);
                }
            }
        }

        return ans;
    }

    public static List<Integer> findSubstring1(String s, String[] words) {
        int N = s.length();
        int size = words.length;
        int len = words[0].length();
        List<Integer> ans = new ArrayList<>();

        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        for (int i = 0; i + size * len <= N; i++) {
            Map<String, Integer> curMap = new HashMap<>();
            String subs = s.substring(i, i + len * size);
            for (int j = 0; j < subs.length(); j += len) {
                String curWord = subs.substring(j, j + len);
                if (!map.containsKey(curWord)) {
                    break;
                }
                curMap.put(curWord, curMap.getOrDefault(curWord, 0) + 1);
            }
            if (map.equals(curMap)) {
                ans.add(i);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        // "wordgoodgoodgoodbestword"
        // ["word","good","best","good"]
        String s = "lingmindraboofooowingdingbarrwingmonkeypoundcake";
        String[] words = new String[]{"fooo","barr","wing","ding","wing"};
        System.out.println(findSubstring(s, words));
        System.out.println(findSubstring1(s, words));
    }
}
