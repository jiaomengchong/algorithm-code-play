package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/check-if-a-word-occurs-as-a-prefix-of-any-word-in-a-sentence/
 *
 * @Author jmc
 * @Description
 * @Date 2022/7/11 16:22
 **/
public class Problem_1455_CheckIfAWordOccursAsAPrefixOfAnyWordInASentence {
    public static int isPrefixOfWord(String sentence, String searchWord) {
        String[] words = sentence.split(" ");
        int N = words.length;
        int ans = -1;
        for (int i = 0; i < N; i++) {
            if (words[i].startsWith(searchWord)) {
                ans = i + 1;
                return ans;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String sentence = "i am tired", searchWord = "you";
        System.out.println(isPrefixOfWord(sentence, searchWord));
    }
}
