package com.jmc.algorithm.dailyChallenge;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 测试链接：https://leetcode-cn.com/problems/valid-parentheses/
 *
 * @Author jmc
 * @Description
 * @Date 2022/5/5 20:28
 **/
public class Problem_0020_ValidParentheses {
    static Map<Character, Character> map = new HashMap<>();
    static {
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
    }

    public static boolean isValid(String s) {
        // ()()
        if (s.length() <= 1 || (s.length() & 1) != 0) {
            return false;
        }

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if ('(' == s.charAt(i) || '[' == s.charAt(i) || '{' == s.charAt(i)) {
                stack.push(s.charAt(i));
            } else {
                if (stack.isEmpty() || map.get(stack.pop()) != s.charAt(i)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s = "){";
        System.out.println(isValid(s));
    }
}
