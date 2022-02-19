package com.jmc.algorithm.jjb.class32;

import java.util.*;

/**
 * @author jmc
 * @version 1.0
 * @date 2021/3/4 11:28
 */
public class Code04_AC2 {
    public static class Node {
        private String end;
        private boolean endUse;
        private Node fail;
        private Node[] nexts;

        public Node() {
            endUse = false;
            end = null;
            fail = null;
            nexts = new Node[26];
        }
    }

    public static class ACAutomation {
        private Node root;

        public ACAutomation() {
            root = new Node();
        }

        public void insert(String str) {
            char[] chars = str.toCharArray();
            Node cur = root;
            int index;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if (cur.nexts[index] == null) {
                    cur.nexts[index] = new Node();
                }
                cur = cur.nexts[index];
            }
            cur.end = str;
        }

        public void build() {
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            Node cur;
            Node cfail;
            while (!queue.isEmpty()) {
                cur = queue.poll();
                for (int i = 0; i < 26; i++) {
                    if (cur.nexts[i] != null) {
                        cur.nexts[i].fail = root;
                        cfail = cur.fail;
                        while (cfail != null) {
                            if (cfail.nexts[i] != null) {
                                cur.nexts[i].fail = cfail.nexts[i];
                                break;
                            }
                            cfail = cfail.fail;
                        }
                        queue.add(cur.nexts[i]);
                    }
                }
            }
        }

        public List<String> containWords(String content) {
            char[] chars = content.toCharArray();
            List<String> ans = new ArrayList<>();
            Node cur = root;
            Node flow;
            int index;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                while (cur.nexts[index] == null && cur != root) {
                    cur = cur.fail;
                }
                cur = cur.nexts[index] != null ? cur.nexts[index] : root;
                flow = cur;
                while (flow != root) {
                    if (flow.endUse) {
                        break;
                    }
                    if (flow.end != null) {
                        ans.add(flow.end);
                        flow.endUse = true;
                    }
                    flow = flow.fail;
                }
            }

            return ans;
        }
    }

    public static void main(String[] args) {
        ACAutomation ac = new ACAutomation();
        ac.insert("dhe");
        ac.insert("hee");
        ac.insert("abcdhekss");
        ac.build();

        String content = "abcdhekskdjfafhasldkflskdjhwqaeruv";
        List<String> words = ac.containWords(content);
        System.out.println(words);
        System.out.println(content.contains("hee"));
    }
}
