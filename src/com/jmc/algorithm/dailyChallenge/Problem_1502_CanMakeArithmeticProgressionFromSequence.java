package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/problems/can-make-arithmetic-progression-from-sequence/
 *
 * @Author jmc
 * @Description
 * @Date 2022/7/14 15:02
 **/
public class Problem_1502_CanMakeArithmeticProgressionFromSequence {
    public static boolean canMakeArithmeticProgression(int[] arr) {
        int N = arr.length;
        Arrays.sort(arr);
        int diff = Math.abs(arr[1] - arr[0]);
        for (int i = 2; i < N; i++) {
            if (diff != Math.abs(arr[i] - arr[i - 1])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 4};
        System.out.println(canMakeArithmeticProgression(nums));
    }
}
