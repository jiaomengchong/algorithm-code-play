package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/number-of-good-pairs
 *
 * @Author jmc
 * @Description
 * @Date 2022/7/14 17:49
 **/
public class Problems_1512_NumberOfGoodPairs {
    public static int numIdenticalPairs(int[] nums) {
        int N = nums.length;
        int[] count = new int[101];
        for (int num : nums) {
            count[num]++;
        }

        int ans = 0;
        for (int i = 1; i < 101; i++) {
            if (count[i] > 1) {
                ans += permutations(count[i]);
            }
        }
        return ans;
    }

    public static int permutations(int n) {
        return n * (n - 1) / 2;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4};
        System.out.println(numIdenticalPairs(nums));
        System.out.println(permutations(4));
    }
}
