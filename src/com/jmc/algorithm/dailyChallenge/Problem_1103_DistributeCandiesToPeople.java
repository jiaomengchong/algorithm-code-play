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

    public static int[] distributeCandies1(int candies, int num_people) {
        if (num_people <= 0) {
            return new int[]{};
        }

        int[] res = new int[num_people];
        int index = 0;
        while (candies != 0) {
            // candies = 10 num_people = 4
            res[index % num_people] += Math.min(index + 1, candies);
            candies -= Math.min(index + 1, candies);
            index++;
        }
        return res;
    }

    // 二分答案法
    public static int[] distributeCandies2(int candies, int num_people) {
        if (num_people <= 0) {
            return new int[]{};
        }

        int[] res = new int[num_people];
        int num = binarySearch(candies);
        int rounds = num / num_people;
        int remain = candies - getSum(1, rounds * num_people, rounds * num_people);

        for (int i = 1; i <= num_people; i++) {
            int total =  getSum(i, i + (rounds - 1) * num_people, rounds);
            if (remain > 0) {
                total += Math.min(remain, i + rounds * num_people);
                remain -= Math.min(remain, i + rounds * num_people);
            }
            res[i - 1] = total;
        }

        return res;
    }

    // 等差数列公式
    public static int getSum(int a1, int an, int n) {
        return (a1 + an) * n / 2;
    }

    private static int binarySearch(int candies) {
        int left = 0;
        int right = candies;
        int ans = 0;
        while (left <= right) {
            // 2147483647
            // 1000000000
            int mid = (left + right) / 2;
            long total = mid * (mid + 1l) / 2;
            if (total < candies) {
                ans = mid;
                left = mid + 1;
            } else if (total > candies) {
                right = mid - 1;
            } else {
                ans = mid;
                break;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int candies = 1000000000;
        int num_people = 4;
        System.out.println(Arrays.toString(distributeCandies(candies, num_people)));
        System.out.println(Arrays.toString(distributeCandies1(candies, num_people)));
        System.out.println(Arrays.toString(distributeCandies2(candies, num_people)));
    }
}
