package com.jmc.algorithm.jjb.class06;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 已知一个几乎有序的数组。几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离一定不超过k，并且k相对于数组长度来说是比较小的。
 * 请选择一个合适的排序策略，对这个数组进行排序。
 *
 * @author jmc
 * @version 1.0
 * @date 2021/3/8 19:09
 */
public class Code04_SortedArrayDistanceLessK {


    public static void main(String[] args) {
        int[] arr = new int[]{3, 4, 1, 2, 5};
        int k = 2;
        sortedArrayDistance(arr, k);
        System.out.println(Arrays.toString(arr));
    }

    private static void sortedArrayDistance(int[] arr, int k) {
        if (arr == null || k < 0) {
            return;
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int index = 0;
        for (; index <= Math.min(arr.length - 1, k - 1); index++) {
            queue.add(arr[index]);
        }

        int i = 0;
        for (; index < arr.length; i++, index++) {
            queue.add(arr[index]);
            arr[i] = queue.poll();
        }

        while (!queue.isEmpty()) {
            arr[i++] = queue.poll();
        }
    }
}
