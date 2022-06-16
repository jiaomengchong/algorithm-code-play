package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/problems/diagonal-traverse/
 *
 * @Author jmc
 * @Description
 * @Date 2022/6/14 15:23
 **/
public class Problem_0498_DiagonalTraverse {
    public static int[] findDiagonalOrder(int[][] mat) {
        // 输入：mat = [[1,2,3],
        //             [4,5,6],
        //             [7,8,9]]
        // 输出：[1,2,4,7,5,3,6,8,9]
        int M = mat.length;
        int N = mat[0].length;
        int total = M * N;
        int index = 0;
        int direction = 0;
        int curRow = 0, curCol = 0;
        int[] ans = new int[total];
        while (index < total) {
            if (direction % 2 == 0) {
                // 右上走:row-1 col+1
                while (check(curRow, curCol, M, N)) {
                    ans[index++] = mat[curRow--][curCol++];
                }
                if (curRow < 0 && curCol < N) {
                    curRow++;
                } else if (curCol >= N) {
                    curRow += 2;
                    curCol--;
                }
                direction++;
            } else {
                // 左下走:row+1 col-1
                while (check(curRow, curCol, M, N)) {
                    ans[index++] = mat[curRow++][curCol--];
                }
                if (curCol < 0 && curRow < M) {
                    curCol++;
                } else if (curRow >= M) {
                    curRow--;
                    curCol += 2;
                }
                direction++;
            }
        }
        return ans;
    }

    private static boolean check(int row, int col, int totalRow, int totalCol) {
        if (row >= 0 && row < totalRow && col >= 0 && col < totalCol) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] mat = new int[][]{{1,2},{3,4}};
        System.out.println(Arrays.toString(findDiagonalOrder(mat)));
    }
}
