package com.jmc.algorithm.dailyChallenge;

import java.util.Stack;

/**
 * 测试链接：https://leetcode.cn/problems/remove-outermost-parentheses/
 */
public class Problem_1021_RemoveOutermostParentheses {
    public static String removeOuterParentheses(String s) {
        // 输入：s = "(()())(())"
        // 输出："()()()"
        Stack<Character> stack = new Stack<>();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.push(ch);
            }
            if (!stack.isEmpty() && stack.size() > 1) {
                buffer.append(ch);
            }
            if (ch == ')') {
                stack.pop();
            }
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        String s = "()()";
        System.out.println(removeOuterParentheses(s));
    }
}