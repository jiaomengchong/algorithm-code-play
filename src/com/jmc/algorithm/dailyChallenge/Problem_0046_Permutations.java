package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试链接：https://leetcode.cn/problems/permutations/
 *
 * @Author jmc
 * @Description
 * @Date 2022/5/16 20:17
 **/
public class Problem_0046_Permutations {
    public static List<List<Integer>> permute(int[] nums) {
        // 1,2,3
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        dfs(nums, 0, visited, ans, path);
        return ans;
    }

    private static void dfs(int[] nums, int index, boolean[] visited, List<List<Integer>> ans, List<Integer> path) {
        if (path.size() == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }
        if (index == nums.length) {
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                path.add(nums[i]);
                visited[i] = true;
                dfs(nums, index + 1, visited, ans, path);
                path.remove(path.size() - 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4};
        System.out.println(permute(nums));
    }
}
