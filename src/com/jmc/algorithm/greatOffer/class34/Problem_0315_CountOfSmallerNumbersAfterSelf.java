package com.jmc.algorithm.greatOffer.class34;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 计算右侧小于当前元素的个数
 *
 * @author jmc
 * @version 1.0
 * @date 2021/8/23 17:16
 */
public class Problem_0315_CountOfSmallerNumbersAfterSelf {
    public static void mergeSort1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        process1(arr, 0, arr.length - 1);
    }

    private static void process1(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }

        int mid = L + ((R - L) >> 1);
        process1(arr, L, mid);
        process1(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    private static void merge(int[] arr, int L, int mid, int R) {
        int[] help = new int[R - L + 1];
        int p1 = L;
        int p2 = mid + 1;
        int index = 0;
        while (p1 <= mid && p2 <= R) {
            help[index++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p1 <= mid) {
            help[index++] = arr[p1++];
        }

        while (p2 <= R) {
            help[index++] = arr[p2++];
        }

        for (int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
    }

    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        int N = arr.length;
        int mergeSize = 1;
        while (mergeSize < N) {
            int L = 0;
            while (L < N) {
                if (mergeSize > N - L) {
                    break;
                }
                int M = L + mergeSize - 1;
                int R = M + Math.min(mergeSize, N - M - 1);
                merge(arr, L, M, R);
                L = R + 1;
            }
            if (mergeSize > N / 2) {
                break;
            }
            mergeSize <<= 1;
        }
    }

    public static int reversePairs(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        return processReversePairs(arr, 0, arr.length - 1);
    }

    private static int processReversePairs(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }

        int mid = L + ((R - L) >> 1);
        int lCnt = processReversePairs(arr, L, mid);
        int rCnt = processReversePairs(arr, mid + 1, R);
        int mergeCnt = mergeReversePairs(arr, L, mid, R);
        return lCnt + rCnt + mergeCnt;
    }

    private static int mergeReversePairs(int[] arr, int L, int mid, int R) {
        int ans = 0;
        int p1 = mid;
        int p2 = R;
        int[] help = new int[R - L + 1];
        int index = help.length - 1;

        while (p1 >= L && p2 > mid) {
            if (arr[p1] > arr[p2]) {
                ans += p2 - mid;
                help[index--] = arr[p1--];
            } else {
                help[index--] = arr[p2--];
            }
        }

        while (p1 >= L) {
            help[index--] = arr[p1--];
        }

        while (p2 > mid) {
            help[index--] = arr[p2--];
        }

        for (int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }

        return ans;
    }

    public static class Info {
        private int value;
        private int index;

        public Info(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    public static List<Integer> countSmaller(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }

        for (int i = 0; i < nums.length; i++) {
            ans.add(0);
        }

        Info[] infos = new Info[nums.length];
        for (int i = 0; i < nums.length; i++) {
            infos[i] = new Info(nums[i], i);
        }

        processReversePairsList(infos, 0, infos.length - 1, ans);
        return ans;
    }

    private static void processReversePairsList(Info[] infos, int L, int R, List<Integer> ans) {
        if (L == R) {
            return;
        }

        int mid = L + ((R - L) >> 1);
        processReversePairsList(infos, L, mid, ans);
        processReversePairsList(infos, mid + 1, R, ans);
        mergeReversePairsList(infos, L, mid, R, ans);
    }

    private static void mergeReversePairsList(Info[] infos, int L, int mid, int R, List<Integer> ans) {
        Info[] help = new Info[R - L + 1];
        int p1 = mid;
        int p2 = R;
        int index = help.length - 1;

        while (p1 >= L && p2 > mid) {
            if (infos[p1].value > infos[p2].value) {
                ans.set(infos[p1].index, ans.get(infos[p1].index) + p2 - mid);
                help[index--] = infos[p1--];
            } else {
                help[index--] = infos[p2--];
            }
        }
        
        while (p1 >= L) {
            help[index--] = infos[p1--];
        }
        
        while (p2 > mid) {
            help[index--] = infos[p2--];
        }

        for (int i = 0; i < help.length; i++) {
            infos[L + i] = help[i];
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{9, 3, 5, 2, 1, 6};
        int[] arr1 = new int[]{9, 3, 5, 2, 1, 6};
        mergeSort1(arr);
        System.out.println(Arrays.toString(arr));
        mergeSort2(arr1);
        System.out.println(Arrays.toString(arr1));

        arr = new int[]{4, 5, 6, 7};
        System.out.println(reversePairs(arr));

        arr = new int[]{5, 2, 6, 1};
        System.out.println(countSmaller(arr));
    }
}
