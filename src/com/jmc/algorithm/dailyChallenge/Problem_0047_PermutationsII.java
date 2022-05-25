package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 测试链接：https://leetcode.cn/problems/permutations-ii/
 */
public class Problem_0047_PermutationsII {
    public static List<List<Integer>> permuteUnique(int[] nums) {
        // 输入：nums = [1,1,2]
        // 输出：
        // [[1,1,2],
        // [1,2,1],
        // [2,1,1]]
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        dfs(nums, ans, path, visited, 0);
        return ans;
    }

    private static void dfs(int[] nums, List<List<Integer>> ans, List<Integer> path, boolean[] visited, int idx) {
        if (idx == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int index = 0; index < nums.length; index++) {
            if (visited[index] || (index > 0 && nums[index] == nums[index - 1] && !visited[index - 1])) {
                continue;
            }
            visited[index] = true;
            path.add(nums[index]);
            dfs(nums, ans, path, visited, idx + 1);
            visited[index] = false;
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,1};
        System.out.println(permuteUnique(nums));
    }
}
