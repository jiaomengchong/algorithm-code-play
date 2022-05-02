package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode-cn.com/problems/distribute-candies-to-people/
 */
public class Problem_1103_DistributeCandiesToPeople {
    public static int[] distributeCandies(int candies, int num_people) {
        if (num_people <= 0) {
            return null;
        }

        // 输入：candies = 7, num_people = 4
        // 输出：[1,2,3,1]
        int[] ans = new int[num_people];

        int pre = 0;
        while (candies > 0) {
            for (int i = 0; i < num_people; i++) {
                if (candies <= 0) {
                    break;
                }
                ans[i] += Math.min(pre + 1, candies);
                candies -= Math.min(pre + 1, candies);
                pre = Math.min(pre + 1, candies);
            }
        }

        return ans;
    }

    private static int getSum(int num_people) {
        return num_people * (num_people + 1) / 2;
    }

    public static void main(String[] args) {
        int candies = 30;
        int num_people = 4;
        System.out.println(Arrays.toString(distributeCandies(candies, num_people)));
    }
}
