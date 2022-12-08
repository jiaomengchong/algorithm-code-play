package com.jmc.algorithm.dailyChallenge;

public class Problem_1752_CheckIfArrayIsSortedAndRotated {
    public static boolean check(int[] nums) {
        int decrease = 0, N = nums.length;
        for (int i = 1; i < N; i++) {
            if (decrease > 1) {
                return false;
            }
            if (nums[i - 1] > nums[i]) {
                decrease++;
            }
        }
        return decrease == 0 || (decrease == 1 && nums[0] >= nums[N - 1]);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{6,10,6};
        System.out.println(check(nums));
    }
}