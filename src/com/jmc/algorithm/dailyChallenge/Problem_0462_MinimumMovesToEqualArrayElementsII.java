package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/problems/minimum-moves-to-equal-array-elements-ii/
 */
public class Problem_0462_MinimumMovesToEqualArrayElementsII {
    // 暴力递归
    public static int minMoves2(int[] nums) {
        // 输入：nums = [1,10,2,9]
        // 输出：16
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            ans = Math.min(ans, process(nums, 0, nums[i]));
        }
        return ans;
    }

    private static int process(int[] nums, int index, int base) {
        if (index == nums.length) {
            return 0;
        }
        int ans = 0;
        ans += process(nums, index + 1, base) + Math.abs(nums[index] - base);

        return ans;
    }

    // 排序 O(n*logn)
    public static int minMoves2Sort(int[] nums) {
        int ans = 0;
        Arrays.sort(nums);
        int mid = nums.length / 2;
        for (int i = 0; i < nums.length; i++) {
            ans += Math.abs(nums[i] - nums[mid]);
        }

        return ans;
    }

    // TODO 最优解 bfprt O(n) 快排
    public static int minMoves2QuickSort(int[] nums) {
        int ans = 0;

        // 获取第k小
        int mid = getMinKth(nums, nums.length >> 1);
        for (int i = 0; i < nums.length; i++) {
            ans += Math.abs(nums[i] - mid);
        }

        return ans;
    }

    private static int getMinKth(int[] nums, int k) {
        int[] arr = copy(nums);
        return process2(arr, 0, arr.length, k);
    }

    private static int process2(int[] arr, int L, int R, int k) {
        if (L == R) {
            return arr[L];
        }
        // 不止一个数
        int pivot = L + (int)(Math.random() * (R - L));
        int[] ranges = new int[2];
        try {
            ranges = partition(arr, L, R, arr[pivot]);
        } catch (Exception e) {
            System.out.println(e);
        }
        if (k >= ranges[0] && k <= ranges[1]) {
            return arr[ranges[0]];
        } else if (k < ranges[0]) {
            return process2(arr, L, R - 1, k);
        } else {
            return process2(arr, L + 1, R, k);
        }
    }

    private static int[] partition(int[] arr, int l, int r, int pivot) {
        if (l > r) {
            return new int[]{-1, -1};
        }
        if (l == r) {
            return new int[]{l, r};
        }
        int less = l - 1;
        int more = r;
        int cur = l;
        while (cur < more) {
            // 2 1 4 5 3
            if (arr[cur] < pivot) {
                swap(arr, ++less, cur++);
            } else if (arr[cur] > pivot) {
                swap(arr, --more, cur);
            } else {
                cur++;
            }
        }

        return new int[]{less + 1, more - 1};
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static int[] copy(int[] nums) {
        int[] copy = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            copy[i] = nums[i];
        }
        return copy;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 6, 3, 2, 2, 2, 3};
        System.out.println(minMoves2(nums));
        System.out.println(minMoves2Sort(nums));
        System.out.println(minMoves2QuickSort(nums));

        int[] ranges = partition(nums, 0, nums.length, 2);
        System.out.println(Arrays.toString(ranges));
    }
}
