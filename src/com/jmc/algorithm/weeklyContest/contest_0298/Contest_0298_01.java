package com.jmc.algorithm.weeklyContest.contest_0298;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * 测试链接：https://leetcode.cn/contest/weekly-contest-298/problems/greatest-english-letter-in-upper-and-lower-case/
 */
public class Contest_0298_01 {
    public static String greatestLetter(String s) {
        int N = s.length();
        TreeSet<Character> set = new TreeSet<>(new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return o2 - o1;
            }
        });

        for (char ch : s.toCharArray()) {
            set.add(ch);
        }

        for (Character ch : set) {
            if (set.contains((char) (ch - 32))) {
                return String.valueOf((char) (ch - 32));
            }
        }
        return "";
    }

    public static void main(String[] args) {
        String s = "AbCdEfGhIjK";
        System.out.println(greatestLetter(s));

        System.out.println('a' - 'A');
    }
}
