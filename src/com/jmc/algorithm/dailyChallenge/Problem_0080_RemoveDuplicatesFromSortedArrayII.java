package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/remove-duplicates-from-sorted-array-ii/
 */
public class Problem_0080_RemoveDuplicatesFromSortedArrayII {
    public static int removeDuplicates(int[] nums) {
        int N = nums.length;
        if (N <= 2) {
            return N;
        }
        int slow = 2;
        int fast = 2;
        while (fast < N) {
            if (nums[slow - 2] != nums[fast]) {
                nums[slow++] = nums[fast];
            }
            fast++;
        }
        return slow;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,0,1,1,1,1,2,3,3};
        System.out.println(removeDuplicates(nums));
    }
}
