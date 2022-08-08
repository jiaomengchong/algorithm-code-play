package com.jmc.algorithm.dailyChallenge;

import java.util.*;

/**
 * 测试链接：https://leetcode.cn/problems/reduce-array-size-to-the-half/
 *
 * @Author jmc
 * @Description
 * @Date 2022/8/8 19:04
 **/
public class Problem_1338_ReduceArraySizeToTheHalf {
    public static int minSetSize(int[] arr) {
        int ans = 0, N = arr.length, count = 0;
        TreeMap<Integer, Integer> treeMap = new TreeMap<>(Comparator.reverseOrder());
        for (int num : arr) {
            treeMap.put(num, treeMap.getOrDefault(num, 0) + 1);
        }
        List<Map.Entry<Integer, Integer>> mapList = new ArrayList<>(treeMap.entrySet());
        mapList.sort(((o1, o2) -> o2.getValue() - o1.getValue()));
        for (Map.Entry<Integer, Integer> entry : mapList) {
            count += entry.getValue();
            ans++;
            if (count >= N >> 1) {
                return ans;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{7, 7, 7, 7, 7, 7};
        System.out.println(minSetSize(arr));
    }
}
