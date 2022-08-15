package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/get-maximum-in-generated-array/
 *
 * @Author jmc
 * @Description
 * @Date 2022/8/1 18:15
 **/
public class Problems_1646_GetMaximumInGeneratedArray {
    public static int getMaximumGenerated(int n) {
        if (n <= 2) {
            return n == 2 || n == 1 ? 1 : 0;
        }
        int ans = Integer.MIN_VALUE;
        int[] nums = new int[n + 1];
        nums[1] = 1;
        for (int i = 1; i <= roundedUp(n, 2) - 1; i++) {
            nums[i * 2] = nums[i];
            nums[i * 2 + 1] = nums[i] + nums[i + 1];
            ans = Math.max(Math.max(nums[i * 2], nums[i * 2 + 1]), ans);
        }
        return ans;
    }

    private static int roundedUp(int a, int b) {
        return (a + b - 1) / b;
    }

    public static void main(String[] args) {
        System.out.println(getMaximumGenerated(3));
    }
}
