package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/problems/partition-to-k-equal-sum-subsets/
 */
public class Problem_0698_PartitionToKEqualSumSubsets {
    public static boolean canPartitionKSubsets(int[] nums, int k) {
        int N = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum % k != 0) {
            return false;
        }

        Arrays.sort(nums);
        for (int i = 0, j = N - 1; i < j; i++, j--) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }

        int[] edges = new int[k];
        boolean[] visited = new boolean[N];
        return dfs(nums, 0, edges, sum / k, k, visited);
    }

    private static boolean dfs(int[] nums, int index, int[] edges, int len, int k, boolean[] visited) {
        if (index == nums.length) {
            return true;
        }

        for (int i = 0; i < k; i++) {
            if (visited[index] && edges[i] + nums[i] > len) {
                continue;
            }
            edges[i] += nums[index];
            if (edges[i] <= len && dfs(nums, index + 1, edges, len, k, visited)) {
                visited[index] = true;
                return true;
            }
            edges[i] -= nums[index];
            
            if (edges[i] == 0) {
                return false;
            }
        }

        visited[index] = false;
        return false;
    }

    public static void main(String[] args) {
        // [4,4,4,6,1,2,2,9,4,6]
        // 3
        int[] nums = new int[]{4, 4, 4, 6, 1, 2, 2, 9, 4, 6};
        int k = 3;
        System.out.println(canPartitionKSubsets(nums, k));
    }
}
