package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 测试链接：https://leetcode.cn/problems/score-of-parentheses/
 */
public class Problem_0856_ScoreOfParentheses {
    public static int scoreOfParentheses(String s) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.push(0);
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                queue.push(0);
            } else {
                Integer top = queue.pop();
                int value = queue.pop() + Math.max(top * 2, 1);
                queue.push(value);
            }
        }
        return queue.peek();
    }

    public static void main(String[] args) {
        String s = "(()(()))";
        System.out.println(scoreOfParentheses(s));
    }
}
