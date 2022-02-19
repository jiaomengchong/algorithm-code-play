package com.jmc.algorithm.jjb.class41;

import java.util.Arrays;

/**
 * 把题目一中提到的，
 * min{左部分累加和，右部分累加和}，定义为S(N-1)，也就是说：
 * S(N-1)：在arr[0…N-1]范围上，做最优划分所得到的min{左部分累加和，右部分累加和}的最大值
 * 现在要求返回一个长度为N的s数组，
 * s[i] =在arr[0…i]范围上，做最优划分所得到的min{左部分累加和，右部分累加和}的最大值
 * 得到整个s数组的过程，做到时间复杂度O(N)
 *
 * @author jmc
 * @version 1.0
 * @date 2021/3/26 16:23
 */
public class Code02_BestSplitForEveryPosition {
    public static int[] bestSplit1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }

        int N = arr.length;
        int[] ans = new int[N];
        ans[0] = 0;
        for (int range = 1; range < N; range++) {
            for (int s = 0; s < range; s++) {
                int sumL = 0;
                int sumR = 0;
                for (int left = 0; left <= s; left++) {
                    sumL += arr[left];
                }
                for (int right = s + 1; right <= range; right++) {
                    sumR += arr[right];
                }
                ans[range] = Math.max(ans[range], Math.min(sumL, sumR));
            }
        }
        return ans;
    }

    public static int[] bestSplit2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }

        int N = arr.length;
        int[] ans = new int[N];
        ans[0] = 0;
        int[] sum = sum(arr);

        for (int range = 1; range < N; range++) {
            for (int s = 0; s < range; s++) {
                int sumL = sum(sum, 0, s);
                int sumR = sum(sum, s + 1, range);
                ans[range] = Math.max(ans[range], Math.min(sumL, sumR));
            }
        }

        return ans;
    }

    public static int[] bestSplit3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }

        int N = arr.length;
        int[] ans = new int[N];
        ans[0] = 0;
        int[] sum = sum(arr);
        int best = 0;
        for (int range = 1; range < N; range++) {
            while (best + 1 < range) {
                int before = Math.min(sum(sum, 0, best), sum(sum, best + 1, range));
                int after = Math.min(sum(sum, 0, best + 1), sum(sum, best + 2, range));
                if (after >= before) {
                    best++;
                } else {
                    break;
                }
            }
            ans[range] = Math.min(sum(sum, 0, best), sum(sum, best + 1, range));
        }

        return ans;
    }

    public static int[] sum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }

        int N = arr.length;
        int[] sum = new int[N + 1];
        for (int i = 0; i < N; i++) {
            sum[i + 1] = sum[i] + arr[i];
        }
        return sum;
    }

    public static int sum(int[] sum, int L, int R) {
        return sum[R + 1] - sum[L];
    }

    public static boolean isSameArray(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static int[] generateArray(int max, int length) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * max);
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 2, 4, 1, 5};
        int[] ans1 = bestSplit1(arr);
        int[] ans2 = bestSplit2(arr);
        int[] ans3 = bestSplit3(arr);
        System.out.println(Arrays.toString(ans1));
        System.out.println(Arrays.toString(ans2));
        System.out.println(Arrays.toString(ans3));
        System.out.println(isSameArray(ans1, ans2));

        int max = 20;
        int maxLength = 10;
        int testTime = 100000;
        System.out.println("test Begin！");
        for (int i = 0; i < testTime; i++) {
            int length = (int) (Math.random() * maxLength);
            int[] array = generateArray(max, length);
            int[] res1 = bestSplit1(array);
            int[] res2 = bestSplit2(array);
            int[] res3 = bestSplit3(array);
            if (!isSameArray(res1, res2) || !isSameArray(res1, res3)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test Finish！");
    }
}
