package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 测试链接：https://leetcode.cn/problems/minimum-absolute-difference/submissions/
 *
 * @Author jmc
 * @Description
 * @Date 2022/7/4 17:43
 **/
public class Problem_1200_Submissions {
    public static List<List<Integer>> minimumAbsDifference(int[] arr) {
        // 输入：arr = [4,2,1,3]
        // 输出：[[1,2],[2,3],[3,4]]
        Arrays.sort(arr);
        List<List<Integer>> ans = new ArrayList<>();
        int diff = arr[1] - arr[0];
        for (int i = 2; i < arr.length; i++) {
            diff = Math.min(diff, arr[i] - arr[i - 1]);
        }

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] == diff) {
                List<Integer> ret = new ArrayList<>();
                ret.add(arr[i - 1]);
                ret.add(arr[i]);
                ans.add(ret);
            }
        }
        return ans;
    }
}
