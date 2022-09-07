package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试链接：https://leetcode.cn/problems/rearrange-spaces-between-words/
 */
public class Problem_1592_RearrangeSpacesBetweenWords {
    public static String reorderSpaces(String text) {
        StringBuffer ans = new StringBuffer();
        int spaceCnt = 0, wordCnt = 0, splitCnt = 0;
        for (char ch : text.toCharArray()) {
            spaceCnt += ch == ' ' ? 1 : 0;
        }

        String[] words = text.split(" ");
        List<String> wordList = new ArrayList<>();
        for (String word : words) {
            wordCnt += word.equals("") ? 0 : 1;
            if (word.length() != 0) {
                wordList.add(word);
            }
        }

        if (wordCnt == 1) {
            ans.append(text.trim());
            for (int i = 0; i < spaceCnt; i++) {
                ans.append(" ");
            }
            return ans.toString();
        }

        splitCnt = spaceCnt / (wordCnt - 1);
        int remainCnt = spaceCnt - splitCnt * (wordCnt - 1);
        for (int i = 0; i < wordList.size(); i++) {
            ans.append(wordList.get(i));
            if (i == wordList.size() - 1) {
                for (int j = 0; j < remainCnt; j++) {
                    ans.append(" ");
                }
            } else {
                for (int j = 0; j < splitCnt; j++) {
                    ans.append(" ");
                }
            }
        }
        return ans.toString();
    }
}
