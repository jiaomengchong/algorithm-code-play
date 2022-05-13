package com.jmc.algorithm.dailyChallenge;

import java.util.*;

/**
 * 测试链接：https://leetcode.cn/problems/combination-sum/
 */
public class Problem_0039_CombinationSum {

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(candidates, target, 0, path, ans);
        return ans;
    }

    private static void dfs(int[] candidates, int target, int index, List<Integer> path, List<List<Integer>> ans) {
        if (index == candidates.length) {
            return;
        }
        if (target == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }

        dfs(candidates, target, index + 1, path, ans);

        if (target >= candidates[index]) {
            path.add(candidates[index]);
            dfs(candidates, target - candidates[index], index, path, ans);
            // 恢复现场
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] candidates = new int[]{2,3,5};
        int target = 8;
        List<List<Integer>> ans = combinationSum(candidates, target);
        System.out.println(ans);
    }
}
