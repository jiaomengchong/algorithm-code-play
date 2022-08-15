package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/problems/minimum-difference-between-highest-and-lowest-of-k-scores/
 *
 * @Author jmc
 * @Description
 * @Date 2022/8/9 20:55
 **/
public class problem_1984_MinimumDifferenceBetweenHighestAndLowestOfKScores {
    public static int minimumDifference(int[] nums, int k) {
        if (k == 1) {
            return 0;
        }

        Arrays.sort(nums);
        int ans = Integer.MAX_VALUE, N = nums.length, left = 0, right = 0;
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            left = i;
            while (left < N && right < N && right - left < k) {
                max = Math.max(nums[left], nums[right]);
                min = Math.min(nums[left], nums[right]);
                right++;
            }
            ans = Math.min(ans, max - min);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{41900, 69441, 94407, 37498, 20299, 10856, 36221, 2231, 54526, 79072, 84309, 76765, 92282, 13401, 44698, 17586, 98455, 47895, 98889, 65298, 32271, 23801, 83153, 12186, 7453, 79460, 67209, 54576, 87785, 47738, 40750, 31265, 77990, 93502, 50364, 75098, 11712, 80013, 24193, 35209, 56300, 85735, 3590, 24858, 6780, 50086, 87549, 7413, 90444, 12284, 44970, 39274, 81201, 43353, 75808, 14508, 17389, 10313, 90055, 43102, 18659, 20802, 70315, 48843, 12273, 78876, 36638, 17051, 20478};
        int k = 5;
        System.out.println(minimumDifference(arr, k));
    }
}
