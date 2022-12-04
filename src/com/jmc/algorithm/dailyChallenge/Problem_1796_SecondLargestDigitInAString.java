package com.jmc.algorithm.dailyChallenge;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 测试链接：https://leetcode.cn/problems/second-largest-digit-in-a-string/
 */
public class Problem_1796_SecondLargestDigitInAString {
    public static int secondHighest(String s) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        Set<Integer> sets = new HashSet<>();
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch) && !sets.contains(ch - '0')) {
                sets.add(ch - '0');
                pq.offer(ch - '0');
            }
        }
        if (pq.size() < 2) {
            return -1;
        }
        pq.poll();
        return pq.peek();
    }

    public static int secondHighest2(String s) {
        int first = -1, second = -1;
        int num = 0;
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                num = ch - '0';
                if (first < num) {
                    second = first;
                    first = num;
                } else if (num < first && num > second) {
                    second = num;
                }
            }
        }
        return second;
    }

    public static void main(String[] args) {
        String s = "dfa12321afd";
        System.out.println(secondHighest(s));
        System.out.println(secondHighest2(s));
    }
}
