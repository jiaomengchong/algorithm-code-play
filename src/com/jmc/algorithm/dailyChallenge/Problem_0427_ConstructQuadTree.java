package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode-cn.com/problems/construct-quad-tree/
 */
public class Problem_0427_ConstructQuadTree {
    public static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }

    public static Node construct(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return new Node();
        }

        return dfs(grid, 0, 0, grid.length, grid[0].length);
    }

    private static Node dfs(int[][] grid, int row0, int col0, int row1, int col1) {
        boolean isSame = true;
        for (int i = row0; i < row1; i++) {
            for (int j = col0; j < col1; j++) {
                if (grid[row0][col0] != grid[i][j]) {
                    isSame = false;
                    break;
                }
            }
            if (!isSame) {
                break;
            }
        }

        if (isSame) {
            return new Node(grid[row0][col0] == 1, true);
        }

        return new Node(grid[row0][col0] == 1, false,
                dfs(grid, row0, col0, (row0 + row1) >> 1, (col0 + col1) >> 1),
                dfs(grid, row0, (col0 + col1) >> 1, (row0 + row1) >> 1, col1),
                dfs(grid, (row0 + row1) >> 1, col0, row1, (col0 + col1) >> 1),
                dfs(grid, (row0 + row1) >> 1, (col0 + col1) >> 1, row1, col1)
                );
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {0}
        };
        Node node = construct(grid);
        System.out.println(node);
    }
}
