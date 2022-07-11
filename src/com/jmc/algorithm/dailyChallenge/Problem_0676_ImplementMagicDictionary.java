package com.jmc.algorithm.dailyChallenge;

import java.util.HashSet;
import java.util.Set;

/**
 * 测试链接：https://leetcode.cn/problems/implement-magic-dictionary/
 *
 * @Author jmc
 * @Description
 * @Date 2022/7/11 14:35
 **/
public class Problem_0676_ImplementMagicDictionary {
    static class Node {
        private boolean end;
        private Node[] next;

        public Node() {
            end = false;
            next = new Node[26];
        }
    }

    static class MagicDictionary {
        private Node root;

        public MagicDictionary() {
            root = new Node();
        }

        public void buildDict(String[] dictionary) {
            // 构造前缀树
            for (String dic : dictionary) {
                Node cur = root;
                char[] str = dic.toCharArray();
                for (char ch : str) {
                    int index = ch - 'a';
                    if (cur.next[index] == null) {
                        cur.next[index] = new Node();
                    }
                    cur = cur.next[index];
                }
                cur.end = true;
            }
        }

        public boolean search(String searchWord) {
            return dfs(searchWord.toCharArray(), root, 0, false);
        }

        private boolean dfs(char[] sw, Node cur, int index, boolean replace) {
            if (index == sw.length) {
                return cur.end && replace;
            }

            int idx = sw[index] - 'a';
            if (cur.next[idx] != null) {
                if (dfs(sw, cur.next[idx], index + 1, replace)) {
                    return true;
                }
            }

            if (!replace) {
                for (int i = 0; i < 26; i++) {
                    if (i != idx && cur.next[i] != null && dfs(sw, cur.next[i], index + 1, true)) {
                        return true;
                    }
                }
            }

            return false;
        }
    }
}
