package com.jmc.algorithm.greatOffer.class07;

import java.util.HashSet;

/**
 * 假设所有字符都是小写字母
 * 大字符串是str
 * arr是去重的单词表, 每个单词都不是空字符串且可以使用任意次.
 * 使用arr中的单词有多少种拼接str的方式. 返回方法数.
 *
 * @author jmc
 * @version 1.0
 * @date 2021/5/19 20:59
 */
public class Code05_WordBreak {
    public static int _ways1(String str, String[] arr) {
        if (str == null || str.length() == 0 || arr == null || arr.length == 0) {
            return 0;
        }

        HashSet sets = new HashSet();
        for (int i = 0; i < arr.length; i++) {
            sets.add(arr[i]);
        }

        return _process1(str, 0, sets);
    }

    private static int _process1(String str, int index, HashSet sets) {
        if (index == str.length()) {
            return 1;
        }

        int ways = 0;
        for (int end = index; end < str.length(); end++) {
            String endString = str.substring(index, end + 1);
            if (sets.contains(endString)) {
                ways += _process1(str, end + 1, sets);
            }
        }
        return ways;
    }

    public static int ways2(String str, String[] arr) {
        if (str == null || str.length() == 0 || arr == null || arr.length == 0) {
            return 0;
        }

        int N = str.length();
        int[] dp = new int[N + 1];

        HashSet<String> sets = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            sets.add(arr[i]);
        }

        dp[N] = 1;
        for (int i = N - 1; i >= 0; i--) {
            for (int end = i; end < N; end++) {
                String endStr = str.substring(i, end + 1);
                if (sets.contains(endStr)) {
                    dp[i] += dp[end + 1];
                }
            }
        }
        return dp[0];
    }

    public static class Node {
        private boolean end;
        private Node[] nexts;

        public Node() {
            this.end = false;
            this.nexts = new Node[26];
        }
    }

    public static int ways3_(String str, String[] arr) {
        if (str == null || str.length() == 0 || arr == null || arr.length == 0) {
            return 0;
        }

        // 构建前缀树
        Node root = new Node();
        for (String s : arr) {
            char[] chars = s.toCharArray();
            Node node = root;
            int index = 0;
            for (char ch : chars) {
                index = ch - 'a';
                if (node.nexts[index] == null) {
                    node.nexts[index] = new Node();
                }
                node = node.nexts[index];
            }
            node.end = true;
        }

        return process2_(str.toCharArray(), 0, root);
    }

    private static int process2_(char[] chars, int index, Node root) {
        if (index == chars.length) {
            return 1;
        }
        Node cur = root;
        int ways = 0;
        for (int end = index; end < chars.length; end++) {
            int i = chars[end] - 'a';
            if (cur.nexts[i] == null) {
                break;
            }
            cur = cur.nexts[i];
            if (cur.end) {
                ways += process2_(chars, end + 1, root);
            }
        }
        return ways;
    }

    public static int ways4(String str, String[] arr) {
        if (str == null || str.length() == 0 || arr == null || arr.length == 0) {
            return 0;
        }

        int N = str.length();
        char[] strs = str.toCharArray();
        int[] dp = new int[N + 1];
        dp[N] = 1;

        // 构建前缀树
        Node root = new Node();
        for (String s : arr) {
            char[] chars = s.toCharArray();
            int index = 0;
            Node cur = root;
            for (char ch : chars) {
                index = ch - 'a';
                if (cur.nexts[index] == null) {
                    cur.nexts[index] = new Node();
                }
                cur = cur.nexts[index];
            }
            cur.end = true;
        }

        for (int index = N - 1; index >= 0; index--) {
            Node cur = root;
            for (int end = index; end < N; end++) {
                int i = strs[end] - 'a';
                if (cur.nexts[i] == null) {
                    break;
                }
                cur = cur.nexts[i];
                if (cur.end) {
                    dp[index] += dp[end + 1];
                }
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        String str = "aabbbaabbaaaaba";
        String[] arr = new String[]{"aa", "bb", "aaa", "ab", "aba", "b", "bab", "baa", "abaa", "aaab", "aabb", "bbaa", "abab", "ba"};
        System.out.println(_ways1(str, arr));
        System.out.println(ways2(str, arr));
        System.out.println(ways3_(str, arr));
        System.out.println(ways4(str, arr));
    }
}
