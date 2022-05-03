package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode-cn.com/problems/longest-common-prefix/
 */
public class Problem_0014_LongestCommonPrefix {
    private TrieNode root = new TrieNode();

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        StringBuffer sb = new StringBuffer();
        int N = strs.length;
        for (int i = 0; i < N; i++) {
            insert(strs[i]);
        }

        TrieNode cur = root;
        for (int i = 0; i < strs[0].length(); i++) {
            if (cur.trieNodes[strs[0].charAt(i) - 'a'] != null && cur.trieNodes[strs[0].charAt(i) - 'a'].count == N) {
                sb.append(strs[0].charAt(i));
                cur = cur.trieNodes[strs[0].charAt(i) - 'a'];
            } else {
                break;
            }
        }
        return sb.toString();
    }

    public void insert(String str) {
        TrieNode cur = root;
        char[] chars = str.toCharArray();
        for (char ch : chars) {
            if (cur.trieNodes[ch - 'a'] == null) {
                cur.trieNodes[ch - 'a'] = new TrieNode();
            }
            cur = cur.trieNodes[ch - 'a'];
            cur.count++;
        }
    }

    public static class TrieNode {
        private TrieNode[] trieNodes;
        private int count;

        public TrieNode() {
            trieNodes = new TrieNode[26];
            count = 0;
        }
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"ab", "a"};
        Problem_0014_LongestCommonPrefix test = new Problem_0014_LongestCommonPrefix();
        System.out.println(test.longestCommonPrefix(strs));
    }
}
