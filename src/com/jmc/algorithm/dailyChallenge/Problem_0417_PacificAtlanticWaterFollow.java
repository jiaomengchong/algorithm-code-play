package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author jmc
 * @Description
 * @Date 2022/4/27 15:29
 **/
public class Problem_0417_PacificAtlanticWaterFollow {
    public static List<List<Integer>> pacificAtlantic(int[][] heights) {
        if (heights == null || heights.length == 0 || heights[0].length == 0) {
            return null;
        }

        int[] direction = new int[]{-1, 0, 1, 0, -1};
        int m = heights.length;
        int n = heights[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];

        // 太平洋反向找>=
        for (int i = 0; i < m; i++) {
            dfs(heights, direction, i, 0, pacific);
        }
        for (int j = 1; j < n; j++) {
            dfs(heights, direction, 0, j, pacific);
        }

        // 大西洋反向找>=
        for (int i = m - 1; i >= 0; i--) {
            dfs(heights, direction, i, n - 1, atlantic);
        }
        for (int i = 0; i < n; i++) {
            dfs(heights, direction, m - 1, i, atlantic);
        }

        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    List<Integer> pos = new ArrayList<>();
                    pos.add(i);
                    pos.add(j);
                    ans.add(pos);
                }
            }
        }

        return ans;
    }

    private static void dfs(int[][] heights, int[] direction, int row, int col, boolean[][] oceans) {
        if (oceans[row][col]) {
            return;
        }
        oceans[row][col] = true;
        for (int i = 1; i < direction.length; i++) {
            int newRow = direction[i - 1] + row;
            int newCol = direction[i] + col;
            if (newRow >= 0 && newRow < heights.length && newCol >= 0 && newCol < heights[0].length && heights[newRow][newCol] >= heights[row][col]) {
                dfs(heights, direction, newRow, newCol, oceans);
            }
        }
    }


    //输入: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
    //输出: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
    public static void main(String[] args) {
        int[][] heights = new int[][]{
                {1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}
        };

        List<List<Integer>> ans = pacificAtlantic(heights);
        System.out.println(ans);

        heights = new int[][]{
                {2, 1},
                {1, 2}
        };
        ans = pacificAtlantic(heights);
        System.out.println(ans);
    }
}
