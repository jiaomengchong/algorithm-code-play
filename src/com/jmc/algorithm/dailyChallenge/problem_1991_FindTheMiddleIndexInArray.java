package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/find-the-middle-index-in-array/
 *
 * @Author jmc
 * @Description
 * @Date 2022/8/8 17:00
 **/
public class problem_1991_FindTheMiddleIndexInArray {
    public static int findMiddleIndex(int[] nums) {
        int N = nums.length, ans = -1;
        int[] preSum = new int[N];
        preSum[0] = nums[0];
        for (int i = 1; i < N; i++) {
            preSum[i] = nums[i] + preSum[i - 1];
        }
        // 0 1 2 3  4
        // 2 5 4 12 16
        for (int i = 0; i < N; i++) {
            if (preSum[N - 1] - preSum[i] == preSum[i] - nums[i]) {
                ans = i;
                return ans;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1};
        System.out.println(findMiddleIndex(arr));
    }
}
