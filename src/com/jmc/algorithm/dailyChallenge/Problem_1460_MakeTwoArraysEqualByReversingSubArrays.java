package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/problems/make-two-arrays-equal-by-reversing-sub-arrays/
 */
public class Problem_1460_MakeTwoArraysEqualByReversingSubArrays {
    public boolean canBeEqual(int[] target, int[] arr) {
        int N = target.length;
        Arrays.sort(target);
        Arrays.sort(arr);
        for (int i = 0; i < N; i++) {
            if (arr[i] != target[i]) {
                return false;
            }
        }
        return true;
    }
}
