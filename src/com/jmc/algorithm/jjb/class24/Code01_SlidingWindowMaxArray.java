package com.jmc.algorithm.jjb.class24;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 假设一个固定大小为W的窗口，依次划过arr，
 * 返回每一次滑出状况的最大值
 * 例如，arr = [4,3,5,4,3,3,6,7], W = 3
 * 返回：[5,5,5,4,6,7]
 *
 * @author jmc
 * @version 1.0
 * @date 2021/1/22 15:57
 */
public class Code01_SlidingWindowMaxArray {
    public static int[] right(int[] arr, int W) {
        if (arr == null || W <= 0 || arr.length < W) {
            return null;
        }

        int N = arr.length;
        int[] ans = new int[N - W + 1];
        int count = 0;
        for (int L = 0; L < N; L++) {
            for (int R = L; R < N; R++) {
                if (R - L == W - 1) {
                    int max = 0;
                    for (int i = L; i <= R; i++) {
                        max = Math.max(max, arr[i]);
                    }
                    ans[count++] = max;
                }
            }
        }
        return ans;
    }

    public static int[] maxArray(int[] arr, int W) {
        if (arr == null || W <= 0 || arr.length < W) {
            return null;
        }

        int N = arr.length;
        int[] ans = new int[N - W + 1];
        int ansIndex = 0;
        LinkedList<Integer> qMax = new LinkedList<>();
        for (int index = 0; index < N; index++) {
            while (!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[index]) {
                qMax.pollLast();
            }
            qMax.addLast(index);
            if (qMax.peekFirst() == index - W) {
                qMax.pollFirst();
            }
            if (index >= W - 1) {
                ans[ansIndex++] = arr[qMax.peekFirst()];
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 5, 4, 3, 3, 6, 7};
        int W = 3;
        System.out.println(Arrays.toString(right(arr, W)));
        System.out.println(Arrays.toString(maxArray(arr, W)));
    }
}
