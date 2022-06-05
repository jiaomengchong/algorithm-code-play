package com.jmc.algorithm.weeklyContest.contest_0296;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试链接：https://leetcode.cn/contest/weekly-contest-296/problems/replace-elements-in-an-array/
 */
public class Contest_0296_03 {
    // 输入：nums = [1,2,4,6], operations = [[1,3],[4,7],[6,1]]
    // 输出：[3,2,7,1]
    public static int[] arrayChange(int[] nums, int[][] operations) {
        int N = nums.length;
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            indexMap.put(nums[i], i);
        }

        int M = operations.length;
        for (int i = 0; i < M; i++) {
            if (indexMap.containsKey(operations[i][0]) && !indexMap.containsKey(operations[i][1])) {
                Integer index = indexMap.get(operations[i][0]);
                nums[index] = operations[i][1];
                indexMap.remove(operations[i][0]);
                indexMap.put(operations[i][1], index);
            }
        }

        return nums;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2};
        int[][] operations = new int[][]{{1,3},{2,1},{3,2}};
        System.out.println(arrayChange(nums, operations));
    }
}
