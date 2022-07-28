package com.jmc.algorithm.dailyChallenge;

import java.util.Map;
import java.util.TreeMap;

/**
 * 测试链接：https://leetcode.cn/problems/rank-transform-of-an-array/submissions/
 *
 * @Author jmc
 * @Description
 * @Date 2022/7/28 21:17
 **/
public class Problem_1331_Submissions {
    public static int[] arrayRankTransform(int[] arr) {
        int N = arr.length;
        int[] ans = new int[N];
        if (N == 0) {
            return ans;
        }

        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int num : arr) {
            treeMap.put(num, treeMap.getOrDefault(num, 0) + 1);
        }

        TreeMap<Integer, Integer[]> sortedMap = new TreeMap<>();
        int index = 1;
        for (Map.Entry<Integer, Integer> entry : treeMap.entrySet()) {
            sortedMap.put(entry.getKey(), new Integer[]{index++, entry.getValue()});
        }

        int posIndex = 0;
        for (int num : arr) {
            Integer[] pos = sortedMap.get(num);
            ans[posIndex++] = pos[0];
        }

        return ans;
    }
}
