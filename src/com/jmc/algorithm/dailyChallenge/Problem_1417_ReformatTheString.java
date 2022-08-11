package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 测试链接：https://leetcode.cn/problems/reformat-the-string/
 *
 * @Author jmc
 * @Description
 * @Date 2022/8/11 10:58
 **/
public class Problem_1417_ReformatTheString {
    public static String reformat(String s) {
        StringBuffer ans = new StringBuffer();
        Queue<Character> s1 = new ArrayDeque<>();
        Queue<Character> s2 = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                s1.offer(s.charAt(i));
            } else {
                s2.offer(s.charAt(i));
            }
        }

        if (Math.abs(s1.size() - s2.size()) > 1) {
            return ans.toString();
        }

        if (s1.size() > s2.size()) {
            while (!s2.isEmpty()) {
                ans.append(s1.poll()).append(s2.poll());
            }
            ans.append(s1.poll());
        } else if (s1.size() < s2.size()) {
            while (!s1.isEmpty()) {
                ans.append(s2.poll()).append(s1.poll());
            }
            ans.append(s2.poll());
        } else {
            while (!s2.isEmpty()) {
                ans.append(s1.poll()).append(s2.poll());
            }
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        String s = "ab123";
        System.out.println(reformat(s));
    }
}
