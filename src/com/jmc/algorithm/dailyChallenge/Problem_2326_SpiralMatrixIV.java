package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/problems/spiral-matrix-iv/
 */
public class Problem_2326_SpiralMatrixIV {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static int[][] spiralMatrix(int m, int n, ListNode head) {
        int[][] matrix = new int[m][n];
        for (int[] ma : matrix) {
            Arrays.fill(ma, -1);
        }

        int[][] direction = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int direct = 0;
        int row = 0, col = 0;
        ListNode cur = head;
        while (cur != null) {
            matrix[row][col] = cur.val;
            int nextRow = row + direction[direct][0];
            int nextCol = col + direction[direct][1];
            if (nextRow < 0 || nextRow >= m || nextCol < 0 || nextCol >= n || matrix[nextRow][nextCol] != -1) {
                direct = (direct + 1) % 4;
            }
            row += direction[direct][0];
            col += direction[direct][1];
            cur = cur.next;
        }
        return matrix;
    }

    public static void main(String[] args) {

    }
}
