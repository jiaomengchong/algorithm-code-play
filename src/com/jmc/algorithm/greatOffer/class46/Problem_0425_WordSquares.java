package com.jmc.algorithm.greatOffer.class46;

import java.util.*;

/**
 * 单词方块
 * 给定一个单词集合 （没有重复），找出其中所有的 单词方块 。
 * 一个单词序列形成了一个有效的单词方块的意思是指从第 k 行和第 k 列 (0 ≤ k < max(行数, 列数)) 来看都是相同的字符串。
 * 例如，单词序列 ["ball","area","lead","lady"] 形成了一个单词方块，因为每个单词从水平方向看和从竖直方向看都是相同的。
 *
 * @Author jmc
 * @Description
 * @Date 2021/11/26 11:01
 **/
public class Problem_0425_WordSquares {
    public static List<List<String>> wordSquares(String[] words) {
        List<List<String>> ans = new ArrayList<>();
        if (words == null || words.length == 0 || words[0].length() == 0) {
            return ans;
        }

        int N = words.length;
        Map<String, List<String>> map = new HashMap<>();
        for (String word : words) {
            for (int end = 0; end < N; end++) {
                String pre = word.substring(0, end);
                if (!map.containsKey(pre)) {
                    map.put(pre, new ArrayList<>());
                }
                map.get(pre).add(word);
            }
        }

        process(0, N, map, new LinkedList<String>(), ans);

        return ans;
    }

    private static void process(int index, int n, Map<String, List<String>> map, LinkedList<String> path, List<List<String>> ans) {
        if (index == n) {
            ans.add(new ArrayList<>(path));
        } else {
            StringBuffer sb = new StringBuffer();
            for (String pre : path) {
                sb.append(pre.charAt(index));
            }
            if (map.containsKey(sb.toString())) {
                for (String word : map.get(sb.toString())) {
                    path.addLast(word);
                    process(index + 1, n, map, path, ans);
                    path.pollLast();
                }
            }
        }
    }

    public static void main(String[] args) {
        String[] words = new String[]{"abat", "baba", "atan", "atal"};
        System.out.println(wordSquares(words));
        words = new String[]{"ball", "area", "lead", "lady"};
        System.out.println(wordSquares(words));
    }
}
