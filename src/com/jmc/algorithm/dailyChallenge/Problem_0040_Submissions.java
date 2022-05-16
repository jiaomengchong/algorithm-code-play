package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 测试链接：https://leetcode.cn/problems/combination-sum-ii/submissions/
 * @Author jmc
 * @Description
 * @Date 2022/5/16 19:34
 **/
public class Problem_0040_Submissions {
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        List<int[]> freq = new ArrayList<>();
        Arrays.sort(candidates);
        freq.add(new int[]{candidates[0], 1});
        for (int i = 1; i < candidates.length; i++) {
            if (candidates[i] != candidates[i - 1]) {
                freq.add(new int[]{candidates[i], 1});
            } else {
                freq.get(freq.size() - 1)[1]++;
            }
        }
        dfs(candidates, target, 0, ans, path, freq);
        return ans;
    }

    private static void dfs(int[] candidates, int target, int index, List<List<Integer>> ans, List<Integer> path, List<int[]> freq) {
        if (target == 0) {
            ans.add(new ArrayList<>(path));
        }

        if (index == freq.size() || target < freq.get(index)[0]) {
            return;
        }

        dfs(candidates, target, index + 1, ans, path, freq);
        int cycle = Math.min(target / freq.get(index)[0], freq.get(index)[1]);
        for (int i = 1; i <= cycle; i++) {
            path.add(freq.get(index)[0]);
            dfs(candidates, target - i * freq.get(index)[0], index + 1, ans, path, freq);
        }
        for (int i = 1; i <= cycle; i++) {
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        // [2,5,2,1,2]
        // 5
        int[] candidates = new int[]{2,5,2,1,2};
        int target = 5;
        System.out.println(combinationSum2(candidates, target));
    }
}
