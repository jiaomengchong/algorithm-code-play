package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/problems/missing-two-lcci/
 */
public class Problem_17Dot19_MissingTwoLcci {
    public static int[] missingTwo(int[] nums) {
        // 1 2 3 4 5 6 7 8 9 10  原数组    和：55 = (10+1)*10/2
        // 1 2 4 5 6 7 8 10      缺失数组  和：44
        int N = nums.length + 2;
        int sum = (N + 1) * N / 2;
        for (int num : nums) {
            sum -= num;
        }

        // 剩余的2个数的和为sum
        int mid = sum / 2;
        int leftSum = (mid + 1) * mid / 2;
        int curSum = 0;
        for (int num : nums) {
            if (num <= mid) {
                curSum += num;
            }
        }
        return new int[]{leftSum - curSum, sum - leftSum + curSum};
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 4, 5, 6, 7, 8, 10};
        System.out.println(Arrays.toString(missingTwo(nums)));

    }
}
