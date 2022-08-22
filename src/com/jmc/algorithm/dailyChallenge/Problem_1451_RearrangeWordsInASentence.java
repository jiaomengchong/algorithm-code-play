package com.jmc.algorithm.dailyChallenge;

import java.util.PriorityQueue;

/**
 * 测试链接：https://leetcode.cn/problems/rearrange-words-in-a-sentence/
 */
public class Problem_1451_RearrangeWordsInASentence {
    public static class Info {
        private int index;
        private int len;
        private String str;

        public Info(int index, int len, String str) {
            this.index = index;
            this.len = len;
            this.str = str;
        }
    }

    public static String arrangeWords(String text) {
        String[] arr = text.split(" ");
        int N = arr.length;
        StringBuffer sb = new StringBuffer();
        PriorityQueue<Info> pq = new PriorityQueue<>((a, b) -> a.len != b.len ? a.len - b.len : a.index - b.index);
        for (int i = 0; i < N; i++) {
            pq.offer(new Info(i, arr[i].length(), arr[i].toLowerCase()));
        }

        while (!pq.isEmpty()) {
            sb.append(pq.poll().str);
            if (pq.size() != 0) {
                sb.append(" ");
            }
        }
        String ans = sb.toString();
        ans = ans.length() == 1 ? ans.toUpperCase() : ans.substring(0, 1).toUpperCase() + ans.substring(1);
        return ans;
    }

    public static void main(String[] args) {
        String text = "Keep calm and code on";
        System.out.println(arrangeWords(text));
    }
}
