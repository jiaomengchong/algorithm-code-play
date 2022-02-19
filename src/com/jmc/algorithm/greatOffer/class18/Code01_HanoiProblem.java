package com.jmc.algorithm.greatOffer.class18;

/**
 * 汉诺塔问题：
 * 给定一个数组arr，长度为N，arr中的值只有1，2，3三种
 * arr[i] == 1，代表汉诺塔问题中，从上往下第i个圆盘目前在左
 * arr[i] == 2，代表汉诺塔问题中，从上往下第i个圆盘目前在中
 * arr[i] == 3，代表汉诺塔问题中，从上往下第i个圆盘目前在右
 * 那么arr整体就代表汉诺塔游戏过程中的一个状况
 * 如果这个状况不是汉诺塔最优解运动过程中的状况，返回-1
 * 如果这个状况是汉诺塔最优解运动过程中的状况，返回它是第几个状况
 *
 * @author jmc
 * @version 1.0
 * @date 2021/6/24 10:57
 */
public class Code01_HanoiProblem {
    public static int ways1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        return process(arr, arr.length - 1, 1, 2, 3);
    }

    /**
     * 分三大步：
     *      1、0~index-1到other
     *      2、index到to
     *      3、0~index-1到to
     * @param arr
     * @param index
     * @param from
     * @param other
     * @param to
     * @return
     */
    private static int process(int[] arr, int index, int from, int other, int to) {
        if (index == -1) {
            return 0;
        }
        if (arr[index] == other) {
            return -1;
        }
        if (arr[index] == from) {
            // 第一大步还没走完
            return process(arr, index - 1, from, to, other);
        } else {
            int p1 = (1 << index) - 1;
            int p2 = 1;
            int p3 = process(arr, index - 1, other, from, to);
            if (p3 == -1) {
                return -1;
            }
            return p1 + p2 + p3;
        }
    }

    public static void main(String[] args) {
        int[] arr = { 3, 3, 2, 1 };
        System.out.println(ways1(arr));
    }
}
