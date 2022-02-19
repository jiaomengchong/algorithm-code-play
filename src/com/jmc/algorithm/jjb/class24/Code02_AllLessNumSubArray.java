package com.jmc.algorithm.jjb.class24;

import java.util.LinkedList;

/**
 * 给定一个整型数组arr，和一个整数num
 * 某个arr中的子数组sub，如果想达标，必须满足：
 * sub中最大值 – sub中最小值 <= num，
 * 返回arr中达标子数组的数量
 *
 * @author jmc
 * @version 1.0
 * @date 2021/1/23 23:12
 */
public class Code02_AllLessNumSubArray {
    public static int ways1(int[] arr, int num) {
        if (arr == null) {
            return 0;
        }

        int N = arr.length;
        int ans = 0;
        int R = 0;
        LinkedList<Integer> qmax = new LinkedList<>();
        LinkedList<Integer> qmin = new LinkedList<>();
        for (int L = 0; L < N; L++) {
            while (R < N) {
                while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[R]) {
                    qmax.pollLast();
                }
                qmax.addLast(R);
                while (!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[R]) {
                    qmin.pollLast();
                }
                qmin.addLast(R);
                if (arr[qmax.peekFirst()] - arr[qmin.peekFirst()] > num) {
                    break;
                } else {
                    R++;
                }
            }
            ans += R - L;
            if (L == qmax.peekFirst()) {
                qmax.pollFirst();
            }
            if (L == qmin.peekFirst()) {
                qmin.pollFirst();
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 5, 1, 0, 7, 6, 5};
        int num = 3;
        System.out.println(ways1(arr, num));
    }
}
