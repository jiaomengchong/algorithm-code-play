package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试链接：https://leetcode.cn/problems/different-ways-to-add-parentheses/
 */
public class Problem_0241_DifferentWaysToAddParentheses {
    public static List<Integer> diffWaysToCompute(String expression) {
        return dfs(expression.toCharArray(), 0, expression.length() - 1);
    }

    private static List<Integer> dfs(char[] str, int left, int right) {
        List<Integer> ans = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (str[i] >= '0' && str[i] <= '9') {
                continue;
            }
            List<Integer> leftList = dfs(str, left, i - 1);
            List<Integer> rightList = dfs(str, i + 1, right);
            for (int op1 : leftList) {
                for (int op2 : rightList) {
                    int ret = 0;
                    if (str[i] == '+') {
                        ret = op1 + op2;
                    } else if (str[i] == '-') {
                        ret = op1 - op2;
                    } else {
                        ret = op1 * op2;
                    }
                    ans.add(ret);
                }
            }
        }

        if (ans.isEmpty()) {
            int cur = 0;
            for (int i = left; i <= right; i++) {
                cur = cur * 10 + str[i] - '0';
            }
            ans.add(cur);
        }
        return ans;
    }

    public static void main(String[] args) {
        String expression = "11";
        System.out.println(diffWaysToCompute(expression));
    }
}
