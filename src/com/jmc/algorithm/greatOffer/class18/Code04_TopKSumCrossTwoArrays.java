package com.jmc.algorithm.greatOffer.class18;

import java.util.*;

/**
 * 两个有序数组间相加和的Topk问题
 * 测试链接：https://www.nowcoder.com/practice/7201cacf73e7495aa5f88b223bbbf6d1
 *
 * @author jmc
 * @version 1.0
 * @date 2021/6/29 11:48
 */
public class Code04_TopKSumCrossTwoArrays {
    private static class Node {
        private int index1;
        private int index2;
        private int sum;

        public Node(int index1, int index2, int sum) {
            this.index1 = index1;
            this.index2 = index2;
            this.sum = sum;
        }
    }

    private static class TopSumComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o2.sum - o1.sum;
        }
    }

    public static int[] topKSum(int[] arr1, int[] arr2, int K) {
        if (arr1 == null || arr2 == null || K < 1) {
            return null;
        }

        int N = arr1.length;
        int M = arr2.length;
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(new TopSumComparator());
        int index1 = N - 1;
        int index2 = M - 1;
        K = Math.min(K, N * M);
        // 一维坐标
        long index = getIndex(index1, index2, M);
        priorityQueue.add(new Node(index1, index2, arr1[index1] + arr2[index2]));
        Set<Long> set = new HashSet<>();
        set.add(index);
        int[] ans = new int[K];
        int ansIndex = 0;

        while (!priorityQueue.isEmpty()) {
            Node poll = priorityQueue.poll();
            ans[ansIndex++] = poll.sum;
            if (ansIndex == K) {
                break;
            }

            index1 = poll.index1;
            index2 = poll.index2;
            // 左边
            index = getIndex(index1, index2 - 1, M);
            if (index2 - 1 >= 0 && !set.contains(index)) {
                set.add(index);
                priorityQueue.add(new Node(index1, index2 - 1, arr1[index1] + arr2[index2 - 1]));
            }
            // 上边
            index = getIndex(index1 - 1, index2, M);
            if (index1 - 1 >= 0 && !set.contains(index)) {
                set.add(index);
                priorityQueue.add(new Node(index1 - 1, index2, arr1[index1 - 1] + arr2[index2]));
            }
        }
        return ans;
    }

    /**
     * 二维坐标转一维坐标
     *
     * @param index1
     * @param index2
     * @param col
     * @return
     */
    private static long getIndex(int index1, int index2, int col) {
        return (long) index1 * (long) col + (long) index2;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int[] arr1 = new int[N];
        int[] arr2 = new int[N];
        for (int i = 0; i < N; i++) {
            arr1[i] = sc.nextInt();
        }
        for (int i = 0; i < N; i++) {
            arr2[i] = sc.nextInt();
        }
        int[] topK = topKSum(arr1, arr2, K);
        for (int i = 0; i < K; i++) {
            System.out.print(topK[i] + " ");
        }
        System.out.println();
        sc.close();
    }
}
