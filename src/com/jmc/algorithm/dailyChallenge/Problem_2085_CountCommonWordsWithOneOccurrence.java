package com.jmc.algorithm.dailyChallenge;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试链接：https://leetcode.cn/problems/count-common-words-with-one-occurrence/
 *
 * @Author jmc
 * @Description
 * @Date 2022/8/12 18:15
 **/
public class Problem_2085_CountCommonWordsWithOneOccurrence {
    public static int countWords(String[] words1, String[] words2) {
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        int ans = 0;
        for (String word : words1) {
            map1.put(word, map1.getOrDefault(word, 0) + 1);
        }

        for (String word : words2) {
            map2.put(word, map2.getOrDefault(word, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : map1.entrySet()) {
            if (entry.getValue() == 1 && map2.getOrDefault(entry.getKey(), 0) == 1) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] words1 = new String[]{"leetcode", "is", "amazing", "as", "is"};
        String[] words2 = new String[]{"amazing", "leetcode", "is"};
        System.out.println(countWords(words1, words2));
    }
}
