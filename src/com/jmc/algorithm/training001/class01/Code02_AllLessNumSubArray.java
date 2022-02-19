package com.jmc.algorithm.training001.class01;

import java.util.LinkedList;

/**
 * 给定一个整型数组arr，和一个整数num
 * 某个arr中的子数组sub，如果想达标，必须满足：
 * sub中最大值 – sub中最小值 <= num，
 * 返回arr中达标子数组的数量
 * <p>
 * arr = [3,2,4,1,5,6,8,3]
 * 下标   0 1 2 3 4 5 6 7
 * num =4
 *
 * @author jmc
 * @version 1.0
 * @date 2020/12/15 17:41
 */
public class Code02_AllLessNumSubArray {

    public static int process2(int[] arr, int num) {
        if (arr == null || arr.length == 0 || num < 0) {
            return 0;
        }

        int res = 0;
        int R = 0;
        int L = 0;
        LinkedList<Integer> qmax = new LinkedList();
        LinkedList<Integer> qmin = new LinkedList();

        while (L < arr.length) {
            while (R < arr.length) {
                if (!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[R]) {
                    qmin.pollLast();
                }
                qmin.addLast(R);

                if (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[R]) {
                    qmax.pollLast();
                }
                qmax.addLast(R);

                if (arr[qmax.peekFirst()] - arr[qmin.peekFirst()] > num) {
                    break;
                }

                R++;
            }

            res += R - L;

            if (L == qmin.peekFirst()) {
                qmin.pollFirst();
            }

            if (R == qmax.peekFirst()) {
                qmax.pollFirst();
            }

            L++;
        }

        return res;
    }

    public static int getNum(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        LinkedList<Integer> qmin = new LinkedList<Integer>();
        LinkedList<Integer> qmax = new LinkedList<Integer>();
        int L = 0;
        int R = 0;
        // [L..R) -> [0,0) 窗口内无数 [1,1)
        // [0,1) -> [0~0]
        int res = 0;
        while (L < arr.length) { // L是开头位置，尝试每一个开头

            // 如果此时窗口的开头是L,下面的while工作:R向右扩到违规为止

            while (R < arr.length) { // R是最后一个达标位置的再下一个
                while (!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[R]) {
                    qmin.pollLast();
                }
                qmin.addLast(R);
                // R -> arr[R],
                while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[R]) {
                    qmax.pollLast();
                }
                qmax.addLast(R);

                if (arr[qmax.getFirst()] - arr[qmin.getFirst()] > num) {
                    break;
                }
                R++;
            }

            // R是最后一个达标位置的再下一个，第一个违规的位置
            res += R - L;

            if (qmin.peekFirst() == L) {
                qmin.pollFirst();
            }
            if (qmax.peekFirst() == L) {
                qmax.pollFirst();
            }

            L++;

        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {5, 20};
        int num = 4;
        System.out.println(process2(arr, num));
        System.out.println(getNum(arr, num));
    }
}
