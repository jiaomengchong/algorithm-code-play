package com.jmc.algorithm.jjb.class07;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定很多线段，每个线段都有两个数[start, end]，
 * 表示线段开始位置和结束位置，左右都是闭区间
 * 规定：
 * 1）线段的开始和结束位置一定都是整数值
 * 2）线段重合区域的长度必须>=1
 * 返回线段最多重合区域中，包含了几条线段
 *
 * @author jmc
 * @version 1.0
 * @date 2021/3/9 13:43
 */
public class Code01_CoverMax {
    public static class Line {
        private int start;
        private int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static class StartComparator implements Comparator<Line> {

        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }
    }

    public static class EndComparator implements Comparator<Line> {

        @Override
        public int compare(Line o1, Line o2) {
            return o1.end - o2.end;
        }
    }

    public static int coverMaxHeap(int[][] matrix) {
        if (matrix == null) {
            return 0;
        }

        Line[] lines = new Line[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            lines[i] = new Line(matrix[i][0], matrix[i][1]);
        }
        Arrays.sort(lines, new StartComparator());

        int max = 0;
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i = 0; i < lines.length; i++) {
            while (!heap.isEmpty() && heap.peek() <= lines[i].start) {
                heap.poll();
            }
            heap.add(lines[i].end);
            max = Math.max(max, heap.size());
        }

        return max;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 3}, {2, 4}, {2, 5}, {2, 3}, {7, 10}};
        System.out.println(coverMaxHeap(matrix));
    }
}
