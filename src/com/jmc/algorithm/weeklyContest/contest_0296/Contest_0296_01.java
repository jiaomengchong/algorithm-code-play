package com.jmc.algorithm.weeklyContest.contest_0296;

/**
 * 测试链接：https://leetcode.cn/contest/weekly-contest-296/problems/min-max-game/
 */
public class Contest_0296_01 {
    public static int minMaxGame(int[] nums) {
        if (nums.length == 2) {
            return Math.min(nums[0], nums[1]);
        }
        int N = nums.length;
        return process(nums, 0, 0);
    }

    private static int process(int[] nums, int index, int pre) {
        if (nums.length == 1) {
            return nums[0];
        }

        int N = nums.length;
        int[] newNums = new int[N >> 1];
        int count = 0;
        for (int i = 0; i < N; i += 2) {
            if (count % 2 == 0) {
                newNums[count] = Math.min(nums[i], nums[i + 1]);
            } else {
                newNums[count] = Math.max(nums[i], nums[i + 1]);
            }
            count++;
        }

        return process(newNums, index, pre);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,3,5,2};
        System.out.println(minMaxGame(nums));
    }
}
