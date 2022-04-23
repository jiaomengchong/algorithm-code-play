package com.jmc.algorithm.weekProblem.class_2022_03_02_week;

import java.util.PriorityQueue;

// 来自谷歌
// 给定一个数组arr，长度为n
// 表示n个服务员，每个人服务一个人的时间
// 给定一个正数m，表示有m个人等位
// 如果你是刚来的人，请问你需要等多久？
// 假设：m远远大于n，比如n<=1000, m <= 10的9次方，该怎么做？
public class Code07_MinWaitingTime {
    public static int ways1(int[] arr, int m) {
        PriorityQueue<int[]> serviceQueue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < arr.length; i++) {
            serviceQueue.add(new int[]{0, arr[i]});
        }
        for (int i = 0; i < m; i++) {
            int[] cur = serviceQueue.poll();
            serviceQueue.add(new int[]{cur[0] + cur[1], cur[1]});
        }
        return serviceQueue.peek()[0];
    }

    // TODO 二分答案

    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 5};
        int m = 16;
        System.out.println(ways1(arr, m));
    }
}
