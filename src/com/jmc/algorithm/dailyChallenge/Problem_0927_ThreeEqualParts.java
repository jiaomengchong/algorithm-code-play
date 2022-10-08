package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/problems/three-equal-parts/
 */
public class Problem_0927_ThreeEqualParts {
    public static int[] threeEqualParts(int[] arr) {
        int N = arr.length;
        int oneCnt = 0;
        for (int num : arr) {
            oneCnt += num;
        }
        if (oneCnt % 3 != 0) {
            return new int[]{-1, -1};
        }
        if (oneCnt == 0) {
            return new int[]{0, N - 1};
        }
        int oneAvg = oneCnt / 3;
        // 输入：arr = [1,0,1,0,1]
        // 输出：[0,3]
        int i = findIndex(1, arr);
        int j = findIndex(oneAvg * 1 + 1, arr);
        int k = findIndex(oneAvg * 2 + 1, arr);
        for (; k < N && arr[i] == arr[j] && arr[j] == arr[k]; i++, j++, k++) {

        }
        return k == N ? new int[]{i - 1, j} : new int[]{-1, -1};
    }

    private static int findIndex(int target, int[] arr) {
        int total = 0;
        for (int i = 0; i < arr.length; i++) {
            total += arr[i];
            if (total == target) {
                return i;
            }
        }
        return arr.length - 1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,1,0,0,1};
        System.out.println(Arrays.toString(threeEqualParts(arr)));
    }
}
