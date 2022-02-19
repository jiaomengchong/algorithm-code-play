package com.jmc.algorithm.greatOffer.class24;

import java.util.HashMap;
import java.util.Map;

/**
 * 去除重复字母
 * 测试链接：https://leetcode-cn.com/problems/remove-duplicate-letters/
 *
 * @author jmc
 * @version 1.0
 * @date 2021/7/31 17:25
 */
public class Code06_RemoveDuplicateLetters {
    public static String removeDuplicateLetters(String s) {
        if (s.length() < 2) {
            return s;
        }
        char[] str = s.toCharArray();
        int[] map = new int[256];
        for (int i = 0; i < str.length; i++) {
            map[str[i]]++;
        }

        int minLexiIndex = 0;
        for (int i = 0; i < str.length; i++) {
            map[str[i]]--;
            minLexiIndex = str[i] < str[minLexiIndex] ? i : minLexiIndex;
            if (map[str[i]] == 0) {
                break;
            }
        }

        return str[minLexiIndex] + removeDuplicateLetters(s.substring(minLexiIndex + 1, str.length).replaceAll(String.valueOf(str[minLexiIndex]), ""));
    }

    public static void main(String[] args) {
        String s = "cbacdcbc";
        System.out.println(removeDuplicateLetters(s));
    }
}
