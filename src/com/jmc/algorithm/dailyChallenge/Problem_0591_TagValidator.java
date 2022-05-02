package com.jmc.algorithm.dailyChallenge;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * 测试链接：https://leetcode-cn.com/problems/tag-validator/
 */
public class Problem_0591_TagValidator {
    public static boolean isValid(String code) {
        if (code == null || code.length() == 0) {
            return false;
        }

        // <DIV>This is the first line <![CDATA[<div>]]></DIV>
        // <![CDATA[CDATA_CONTENT]]>
        int index = 0;
        int N = code.length();
        Stack<String> stack = new Stack<>();
        while (index < N) {
            if (code.charAt(index) == '<') {
                if (index + 1 >= N) {
                    return false;
                }
                if (code.charAt(index + 1) == '/') {
                    int next = code.indexOf('>', index + 2);
                    if (next < 0) {
                        return false;
                    }
                    String tagName = code.substring(index + 2, next);
                    if (stack.isEmpty() || !stack.peek().equals(tagName)) {
                        return false;
                    }
                    stack.pop();
                    index = next + 1;
                    if (stack.empty() && index != N) {
                        return false;
                    }
                } else if (code.charAt(index + 1) == '!') {
                    if (index + 9 > N) {
                        return false;
                    }
                    String cdata = code.substring(index + 2, index + 9);
                    if (!"[CDATA[".equals(cdata)) {
                        return false;
                    }
                    int next = code.indexOf("]]>", index + 9);
                    if (next < 0) {
                        return false;
                    }
                    index = next + 1;
                } else {
                    int next = code.indexOf('>', index + 1);
                    if (next < 0) {
                        return false;
                    }
                    String tagName = code.substring(index + 1, next);
                    if (tagName.length() == 0 || tagName.length() > 9) {
                        return false;
                    }
                    for (int i = 0; i < tagName.length(); i++) {
                        if (!Character.isUpperCase(tagName.charAt(i))) {
                            return false;
                        }
                    }
                    stack.push(tagName);
                    index = next + 1;
                }
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                index++;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        //             0123456789           22
        String code = "<AAAAAAAAAA></AAAAAAAAAA>";
        System.out.println(isValid(code));
    }
}
