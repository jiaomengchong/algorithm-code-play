package com.jmc.algorithm.greatOffer.class07;

import java.util.HashSet;

/**
 * 给定一个有序数组arr，其中值可能为正、负、0
 * 返回arr中每个数都平方之后不同的结果有多少种？
 *
 * @author jmc
 * @version 1.0
 * @date 2021/5/19 20:20
 */
public class Code04_Power2Diffs {

    /**
     * 时间复杂度O(N)，空间复杂度O(N)
     *
     * @param arr
     * @return
     */
    public static int diff1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        HashSet<Integer> sets = new HashSet<>();
        for (int cur : arr) {
            sets.add(cur * cur);
        }
        return sets.size();
    }

    /**
     * 利用首尾指针，时间复杂度O(N)，额外空间复杂度O(1)
     *
     * @param arr
     * @return
     */
    public static int diff2_(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int ans = 0;
        int N = arr.length;
        int L = 0;
        int R = N - 1;
        int leftAbs = 0;
        int rightAbs = 0;
        while (L <= R) {
            leftAbs = Math.abs(arr[L]);
            rightAbs = Math.abs(arr[R]);
            if (leftAbs < rightAbs) {
                while (R >= 0 && Math.abs(arr[R]) == rightAbs) {
                    R--;
                }
                ans++;
            } else if (leftAbs > rightAbs) {
                while (L < N && Math.abs(arr[L]) == leftAbs) {
                    L++;
                }
                ans++;
            } else {
                while (R >= 0 && Math.abs(arr[R]) == rightAbs) {
                    R--;
                }
                while (L < N && Math.abs(arr[L]) == leftAbs) {
                    L++;
                }
                ans++;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{-8, -8, -7, -5, 0, 3, 4, 8, 8, 9};
        System.out.println(diff1(arr));
        System.out.println(diff2_(arr));
    }
}
