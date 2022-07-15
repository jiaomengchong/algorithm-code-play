package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/final-value-of-variable-after-performing-operations/
 *
 * @Author jmc
 * @Description
 * @Date 2022/7/15 16:59
 **/
public class problem_2011_FinalValueOfVariableAfterPerformingOperations {
    public static int finalValueAfterOperations(String[] operations) {
        int N = operations.length;
        int add = 0, sub = 0;
        for (int i = 0; i < N; i++) {
            if (operations[i].equals("++X") || operations[i].equals("X++")) {
                add++;
            } else {
                sub++;
            }
        }
        return add - sub;
    }
}
