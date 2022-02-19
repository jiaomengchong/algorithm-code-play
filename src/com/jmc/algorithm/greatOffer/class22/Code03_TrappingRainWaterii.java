package com.jmc.algorithm.greatOffer.class22;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 接雨水2[二维]
 * 测试链接：https://leetcode-cn.com/problems/trapping-rain-water-ii/
 *
 * @author jmc
 * @version 1.0
 * @date 2021/7/15 11:58
 */
public class Code03_TrappingRainWaterii {
    public static class Info {
        private int value;
        private int row;
        private int col;

        public Info(int value, int row, int col) {
            this.value = value;
            this.row = row;
            this.col = col;
        }
    }

    public static class MyComparator implements Comparator<Info> {

        @Override
        public int compare(Info o1, Info o2) {
            return o1.value - o2.value;
        }
    }

    public static int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0 || heightMap[0].length == 0) {
            return 0;
        }

        int N = heightMap.length;
        int M = heightMap[0].length;
        boolean[][] isEnter = new boolean[N][M];
        PriorityQueue<Info> heap = new PriorityQueue<>(new MyComparator());
        // 将二维表最外圈加入小根堆中
        // 第一行
        for (int col = 0; col < M; col++) {
            isEnter[0][col] = true;
            heap.add(new Info(heightMap[0][col], 0, col));
        }
        // 第N行
        for (int col = 0; col < M; col++) {
            isEnter[N - 1][col] = true;
            heap.add(new Info(heightMap[N - 1][col], N - 1, col));
        }
        // 第0列
        for (int row = 1; row < N - 1; row++) {
            isEnter[row][0] = true;
            heap.add(new Info(heightMap[row][0], row, 0));
        }
        // 第M列
        for (int row = 1; row < N - 1; row++) {
            isEnter[row][M - 1] = true;
            heap.add(new Info(heightMap[row][M - 1], row, M - 1));
        }

        int water = 0;
        int max = 0;
        int row, col;
        while (!heap.isEmpty()) {
            Info cur = heap.poll();
            row = cur.row;
            col = cur.col;
            max = Math.max(max, cur.value);
            water += Math.max(0, max - cur.value);
            if (row > 0 && !isEnter[row - 1][col]) {
                isEnter[row - 1][col] = true;
                heap.add(new Info(heightMap[row - 1][col], row - 1, col));
            }
            if (row < N - 1 && !isEnter[row + 1][col]) {
                isEnter[row + 1][col] = true;
                heap.add(new Info(heightMap[row + 1][col], row + 1, col));
            }
            if (col > 0 && !isEnter[row][col - 1]) {
                isEnter[row][col - 1] = true;
                heap.add(new Info(heightMap[row][col - 1], row, col - 1));
            }
            if (col < M - 1 && !isEnter[row][col + 1]) {
                isEnter[row][col + 1] = true;
                heap.add(new Info(heightMap[row][col + 1], row, col + 1));
            }
        }

        return water;
    }

    public static void main(String[] args) {
        int[][] heightMap = new int[][]{
                {1, 4, 3, 1, 3, 2},
                {3, 2, 1, 3, 2, 4},
                {2, 3, 3, 2, 3, 1}};
        System.out.println(trapRainWater(heightMap));
    }
}
