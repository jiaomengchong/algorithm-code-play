package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试链接：https://leetcode.cn/problems/ambiguous-coordinates/description/
 */
public class Problems_0816_AmbiguousCoordinates {
    public static List<String> ambiguousCoordinates(String s) {
        List<String> ans = new ArrayList<>();
        // (123)
        s = s.substring(1, s.length() - 1);
        String xs, ys;
        for (int i = 1; i < s.length(); i++) {
            xs = s.substring(0, i);
            ys = s.substring(i);
            for (String x : getNums(xs)) {
                for (String y : getNums(ys)) {
                    ans.add(String.format("(%s, %s)", x, y));
                }
            }
        }
        return ans;
    }

    private static List<String> getNums(String str) {
        List<String> nums = new ArrayList<>();
        String left, right;
        for (int i = 1; i <= str.length(); i++) {
            left = str.substring(0, i);
            right = str.substring(i);
            if ((!left.equals("0") && left.charAt(0) == '0') || right.length() > 0 && right.charAt(right.length() - 1) == '0') {
                continue;
            }
            if (right.length() == 0) {
                nums.add(left);
            } else {
                nums.add(String.format("%s.%s", left, right));
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        String s = "(0123)";
        System.out.println(ambiguousCoordinates(s));
    }
}
