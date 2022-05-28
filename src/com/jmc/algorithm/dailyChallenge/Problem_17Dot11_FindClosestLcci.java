package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/find-closest-lcci/
 */
public class Problem_17Dot11_FindClosestLcci {
    public static int findClosest(String[] words, String word1, String word2) {
        if (words == null || words.length == 0) {
            return -1;
        }

        int N = words.length;
        int index1 = -1, index2 = -1;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            if (word1.equals(words[i])) {
                index1 = i;
            }
            if (word2.equals(words[i])) {
                index2 = i;
            }
            if (index1 != -1 && index2 != -1) {
                ans = Math.min(ans, Math.abs(index1 - index2));
            }
        }
        return ans != Integer.MAX_VALUE ? ans : -1;
    }

    public static void main(String[] args) {
        // ["I","am","a","student","from","a","university","in","a","city"]
        // "a"
        // "student"
        String[] words = new String[]{"I","am","a","student","from","a","university","in","a","city"};
        String word1 = "a", word2 = "student";
        System.out.println(findClosest(words, word1, word2));
    }
}
