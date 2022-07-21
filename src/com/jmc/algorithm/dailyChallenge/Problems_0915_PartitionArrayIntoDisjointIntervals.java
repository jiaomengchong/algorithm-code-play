package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/partition-array-into-disjoint-intervals
 *
 * @Author jmc
 * @Description
 * @Date 2022/7/21 15:33
 **/
public class Problems_0915_PartitionArrayIntoDisjointIntervals {
    public static int partitionDisjoint(int[] nums) {
        if (nums.length == 2) {
            return 1;
        }

        int N = nums.length;
        int[] leftMax = new int[N];
        int[] rightMin = new int[N];
        leftMax[0] = nums[0];
        rightMin[N - 1] = nums[N - 1];
        for (int i = 1; i < N; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], nums[i]);
        }

        for (int i = N - 2; i >= 0; i--) {
            rightMin[i] = Math.min(rightMin[i + 1], nums[i]);
        }

        int ans = 1;
        for (int i = 0; i < N - 1; i++) {
            if (leftMax[i] > rightMin[i + 1]) {
                ans++;
            } else if (leftMax[i] <= rightMin[i + 1]) {
                break;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // [29,33,6,4,42,0,10,22,62,16,46,75,100,67,70,74,87,69,73,88] [1,1,1,0,6,12]
        int[] nums = new int[]{1, 1, 1, 0, 6, 12};
        System.out.println(partitionDisjoint(nums));
    }
}
