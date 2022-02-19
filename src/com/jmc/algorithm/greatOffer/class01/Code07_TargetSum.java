package com.jmc.algorithm.greatOffer.class01;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个数组arr，你可以在每个数字之前决定+或者-
 * 但是必须所有数字都参与
 * 再给定一个数target，请问最后算出target的方法数是多少？
 *
 * @author jmc
 * @version 1.0
 * @date 2021/4/25 22:12
 */
public class Code07_TargetSum {
    public static int findTargetSumWays(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        return process1(nums, 0, target);

    }

    private static int process1(int[] arr, int i, int rest) {
        if (i == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        return process1(arr, i + 1, rest - arr[i]) + process1(arr, i + 1, rest + arr[i]);
    }

    public static int findTargetSumWaysDp1(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Map<Integer, Map<Integer, Integer>> dp = new HashMap<>();
        return process2(nums, 0, target, dp);
    }

    private static int process2(int[] nums, int index, int rest, Map<Integer, Map<Integer, Integer>> dp) {
        if (dp.containsKey(index) && dp.get(index).containsKey(rest)) {
            return dp.get(index).get(rest);
        }

        int ans;
        if (index == nums.length) {
            ans = rest == 0 ? 1 : 0;
        } else {
            ans = process2(nums, index + 1, rest - nums[index], dp) + process2(nums, index + 1, rest + nums[index], dp);
        }

        if (dp.containsKey(index)) {
            dp.get(index).put(rest, ans);
        } else {
            Map<Integer, Integer> value = new HashMap<>();
            value.put(rest, ans);
            dp.put(index, value);
        }
        return ans;
    }

    public static int findTargetSumWaysDp2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        return (target > sum) || (((sum & 1) ^ (target & 1)) != 0) ? 0 : process3(nums, 0, (sum + target) >> 1);
    }

    private static int process3(int[] nums, int index, int rest) {
        if (index == nums.length) {
            return rest == 0 ? 1 : 0;
        }

        int p1 = process3(nums, index + 1, rest);
        int p2 = 0;
        if (rest - nums[index] >= 0) {
            p2 = process3(nums, index + 1, rest - nums[index]);
        }
        return p1 + p2;
    }

    public static int findTargetSumWaysDp3(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int sum = 0;
        int N = nums.length;
        for (int i = 0; i < N; i++) {
            sum += nums[i];
        }
        return (target > sum) || (((target & 1) ^ (sum & 1)) != 0) ? 0 : process4(nums, (sum + target) >> 1);
    }

    private static int process4(int[] nums, int rest) {
        int N = nums.length;
        int[][] dp = new int[N + 1][rest + 1];
        dp[N][0] = 1;
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= rest; j++) {
                int p1 = dp[i + 1][j];
                int p2 = 0;
                if (j - nums[i] >= 0) {
                    p2 = dp[i + 1][j - nums[i]];
                }
                dp[i][j] = p1 + p2;
            }
        }
        return dp[0][rest];
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 1, 1, 1, 1};
        int target = 3;
        System.out.println(findTargetSumWays(arr, target));
        System.out.println(findTargetSumWaysDp1(arr, target));
        System.out.println(findTargetSumWaysDp2(arr, target));
        System.out.println(findTargetSumWaysDp3(arr, target));
    }
}
