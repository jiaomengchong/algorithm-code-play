package com.jmc.algorithm.dailyChallenge;

import java.util.*;

/**
 * 测试链接：https://leetcode.cn/problems/check-array-formation-through-concatenation/
 */
public class Problem_1640_CheckArrayFormationThroughConcatenation {
    public static boolean canFormArray(int[] arr, int[][] pieces) {
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            hashMap.put(arr[i], i);
        }

        for (int[] piece : pieces) {
            if (piece.length == 1 && !hashMap.containsKey(piece[0])) {
                return false;
            } else {
                int preIndex = hashMap.getOrDefault(piece[0], -1);
                if (preIndex < 0) {
                    return false;
                }
                for (int i = 1; i < piece.length; i++) {
                    Integer index = hashMap.getOrDefault(piece[i], -1);
                    if (index < 0 || index != preIndex + 1) {
                        return false;
                    }
                    preIndex = index;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // [37,69,3,74,46]
        // [[37,69,3,74,46]]
        int[] arr = new int[]{37, 69, 3, 74, 46};
        int[][] pieces = new int[][]{{37, 69, 3, 74, 46}};
        System.out.println(canFormArray(arr, pieces));
    }
}
