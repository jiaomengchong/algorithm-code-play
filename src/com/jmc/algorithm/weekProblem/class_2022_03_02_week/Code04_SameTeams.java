package com.jmc.algorithm.weekProblem.class_2022_03_02_week;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

// 来自字节飞书团队
// 假设数组a和数组b为两组信号
// 1) length(b) <= length(a)
// 2) 对于任意0<=i<length(b), 有b[i+1] - b[i] == a[i+1] - a[i]
// 那么就称信号b和信号a一致，记为b==a
// 给你好多b数组，假设有m个: b0数组、b1数组...
// 给你好多a数组，假设有n个: a0数组、a1数组...
// 返回一个长度为m的结果数组ans，ans[i]表示 : bi数组和多少个a数组一致
public class Code04_SameTeams {
    public static class TrieNode {
        private List<Integer> indices;
        private HashMap<Integer, TrieNode> nexts;

        public TrieNode() {
            indices = new ArrayList<>();
            nexts = new HashMap<>();
        }
    }

    public static int[] sameTeams(int[][] as, int[][] bs) {
        int m = bs.length;
        int[] ans = new int[m];
        TrieNode root = new TrieNode();
        TrieNode cur;
        for (int i = 0; i < m; i++) {
            int[] b = bs[i];
            cur = root;
            for (int j = 1; j < b.length; j++) {
                int diff = b[j] - b[j - 1];
                if (!cur.nexts.containsKey(diff)) {
                    cur.nexts.put(diff, new TrieNode());
                }
                cur = cur.nexts.get(diff);
            }
            cur.indices.add(i);
        }

        int n = as.length;
        for (int i = 0; i < n; i++) {
            int[] a = as[i];
            cur = root;
            for (int j = 1; j < a.length; j++) {
                int diff = a[j] - a[j - 1];
                if (!cur.nexts.containsKey(diff)) {
                    break;
                }
                cur = cur.nexts.get(diff);
                for (int index : cur.indices) {
                    ans[index]++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] bs = new int[][]{
                {1, 3, 7, 15},
                {1, 3, 7, 15}
        };

        int[][] as = new int[][]{
                {12, 14, 18, 26, 30},
                {4, 7, 11, 15},
                {2, 4, 8, 16, 18}
        };

        int[] ans = sameTeams(as, bs);
        System.out.println(Arrays.toString(ans));
    }
}
