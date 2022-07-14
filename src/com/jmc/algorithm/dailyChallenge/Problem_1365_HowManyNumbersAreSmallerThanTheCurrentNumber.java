package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/problems/how-many-numbers-are-smaller-than-the-current-number/
 *
 * @Author jmc
 * @Description
 * @Date 2022/7/14 10:24
 **/
public class Problem_1365_HowManyNumbersAreSmallerThanTheCurrentNumber {
    public static int[] smallerNumbersThanCurrent(int[] nums) {
        int N = nums.length;
        int[] ans = new int[N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i != j && nums[i] > nums[j]) {
                    ans[i]++;
                }
            }
        }
        return ans;
    }

    public static int[] smallerNumbersThanCurrent2(int[] nums) {
        int N = nums.length;
        int[] ans = new int[N];
        int[][] sortedNums = new int[N][2];
        for (int i = 0; i < N; i++) {
            sortedNums[i][0] = nums[i];
            sortedNums[i][1] = i;
        }

        Arrays.sort(sortedNums, (a, b) -> a[0] - b[0]);
        ans[sortedNums[0][1]] = 0;
        int preIndex = -1;
        for (int i = 1; i < N; i++) {
            if (sortedNums[i][0] != sortedNums[i - 1][0]) {
                preIndex = i;
            }
            ans[sortedNums[i][1]] = preIndex < 0 ? 0 : preIndex;
        }
        return ans;
    }

    public static int[] smallerNumbersThanCurrent3(int[] nums) {
        int[] counts = new int[101];
        int N = nums.length;
        int[] ans = new int[N];
        for (int num : nums) {
            counts[num]++;
        }
        for (int i = 1; i < 101; i++) {
            counts[i] += counts[i - 1];
        }
        for (int i = 0; i < N; i++) {
            ans[i] = nums[i] == 0 ? 0 : counts[nums[i] - 1];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{8, 1, 2, 2, 3};
        System.out.println(Arrays.toString(smallerNumbersThanCurrent(nums)));
        System.out.println(Arrays.toString(smallerNumbersThanCurrent2(nums)));
        System.out.println(Arrays.toString(smallerNumbersThanCurrent3(nums)));
    }
}
