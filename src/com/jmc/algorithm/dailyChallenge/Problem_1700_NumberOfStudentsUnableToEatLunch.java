package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/problems/number-of-students-unable-to-eat-lunch/
 */
public class Problem_1700_NumberOfStudentsUnableToEatLunch {
    public static int test(int[] students, int[] sandwiches) {
        int N = students.length;
        int s1 = Arrays.stream(students).sum();
        int s0 = N - s1;
        for (int san : sandwiches) {
            if (san == 1 && s1 > 0) {
                s1--;
            } else if (san == 0 && s0 > 0) {
                s0--;
            } else {
                break;
            }
        }
        return s0 + s1;
    }
}
