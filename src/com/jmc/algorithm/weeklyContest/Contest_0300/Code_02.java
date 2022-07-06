package com.jmc.algorithm.weeklyContest.Contest_0300;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/problems/spiral-matrix-iv/
 */
public class Code_02 {
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
        int[][] ans = new int[m][n];
        for (int[] arr : ans) {
            Arrays.fill(arr, -1);
        }

        ListNode cur = head;
        int row = 0;
        int col = 0;
        // 上下左右
        int[] direction = new int[]{n, m, 0, 0};
        int direct = 0;
        while (cur != null) {
            ans[row][col] = cur.val;
            if (col == direction[0]) {
                // 向下
                row++;
            } else if (row == direction[1]) {
                // 向左
            } else if (col == direction[2]) {
                // 向上
            } else if (row == direction[3]) {
                // 向右
                col++;
            }
            cur = cur.next;
        }
        return ans;
    }

    public static void main(String[] args) {
        int m = 1;
        int n = 4;
        ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(-1);
        System.out.println(spiralMatrix(m, n, head));
    }
}
