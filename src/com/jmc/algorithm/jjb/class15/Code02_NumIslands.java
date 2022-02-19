package com.jmc.algorithm.jjb.class15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * 岛屿数量
 * <p>
 * https://leetcode-cn.com/problems/number-of-islands/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
 *
 * @author jmc
 * @version 1.0
 * @date 2020/12/24 10:44
 */
public class Code02_NumIslands {
    public static class Node<V> {
        private V values;

        public Node(V values) {
            this.values = values;
        }
    }

    public static class UnionSetMap<V> {
        private HashMap<V, Node<V>> nodes;
        private HashMap<Node<V>, Node<V>> parents;
        private HashMap<Node<V>, Integer> sizeMap;

        public UnionSetMap(List<V> values) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizeMap = new HashMap<>();

            for (V value : values) {
                Node<V> node = new Node<>(value);
                nodes.put(value, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public Node<V> findFather(Node<V> cur) {
            Stack<Node<V>> path = new Stack<>();
            while (cur != parents.get(cur)) {
                path.push(cur);
                cur = parents.get(cur);
            }

            while (!path.isEmpty()) {
                parents.put(path.pop(), cur);
            }

            return cur;
        }

        public boolean isSameSet(V a, V b) {
            return findFather(nodes.get(a)) == findFather(nodes.get(b));
        }

        public void union(V a, V b) {
            Node<V> aHead = findFather(nodes.get(a));
            Node<V> bHead = findFather(nodes.get(b));
            if (aHead != bHead) {
                Integer aSize = sizeMap.get(nodes.get(a)) == null ? 0 : sizeMap.get(nodes.get(a));
                Integer bSize = sizeMap.get(nodes.get(b)) == null ? 0 : sizeMap.get(nodes.get(b));
                Node<V> big = aSize >= bSize ? aHead : bHead;
                Node<V> small = big == aHead ? bHead : aHead;
                parents.put(small, big);
                sizeMap.put(big, aSize + bSize);
                sizeMap.remove(small);
            }
        }
    }

    public static class Dot {

    }

    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int rowSize = grid.length;
        int colSize = grid[0].length;
        Dot[][] dots = new Dot[rowSize][colSize];
        List<Dot> dotList = new ArrayList<>();
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (grid[i][j] == '1') {
                    dots[i][j] = new Dot();
                    dotList.add(dots[i][j]);
                }
            }
        }

        UnionSetMap<Dot> unionSet = new UnionSetMap(dotList);
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (grid[i][j] == '1') {
                    if (i - 1 > 0 && grid[i - 1][j] == '1') {
                        unionSet.union(dots[i][j], dots[i - 1][j]);
                    }
                    if (i + 1 < rowSize && grid[i + 1][j] == '1') {
                        unionSet.union(dots[i][j], dots[i + 1][j]);
                    }
                    if (j - 1 > 0 && grid[i][j - 1] == '1') {
                        unionSet.union(dots[i][j], dots[i][j - 1]);
                    }
                    if (j + 1 < colSize && grid[i][j + 1] == '1') {
                        unionSet.union(dots[i][j], dots[i][j + 1]);
                    }
                }
            }
        }

        return unionSet.sizeMap.size();
    }

    public static class UnionSetArray {
        private int[] parents;
        private int[] help;
        private int[] size;
        private int sets;
        private int colSize;

        public UnionSetArray(char[][] grid) {
            int rowSize = grid.length;
            colSize = grid[0].length;
            int arraySize = rowSize * colSize;

            parents = new int[arraySize];
            help = new int[arraySize];
            size = new int[arraySize];

            for (int i = 0; i < rowSize; i++) {
                for (int j = 0; j < colSize; j++) {
                    if (grid[i][j] == '1') {
                        int index = index(i, j);
                        parents[index] = index;
                        size[index] = 1;
                        sets++;
                    }
                }
            }
        }

        public int index(int row, int col) {
            return colSize * row + col;
        }

        public int find(int cur) {
            int hi = 0;
            while (cur != parents[cur]) {
                help[hi++] = cur;
                cur = parents[cur];
            }

            for (hi--; hi >= 0; hi--) {
                parents[help[hi]] = cur;
            }

            return cur;
        }

        public void union(int row1, int col1, int row2, int col2) {
            int index1 = index(row1, col1);
            int index2 = index(row2, col2);
            int f1 = find(index1);
            int f2 = find(index2);
            if (f1 != f2) {
                if (size[f1] >= size[f2]) {
                    size[f1] += size[f2];
                    parents[f2] = f1;
                } else {
                    size[f2] += size[f1];
                    parents[f1] = f2;
                }
                sets--;
            }
        }
    }

    public static int numIslands2(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        UnionSetArray unionSetArray = new UnionSetArray(grid);
        int rowSize = grid.length;
        int colSize = grid[0].length;
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (grid[i][j] == '1') {
                    if (i - 1 >= 0 && grid[i - 1][j] == '1') {
                        unionSetArray.union(i, j, i - 1, j);
                    }
                    if (i + 1 < rowSize && grid[i + 1][j] == '1') {
                        unionSetArray.union(i, j, i + 1, j);
                    }
                    if (j - 1 >= 0 && grid[i][j - 1] == '1') {
                        unionSetArray.union(i, j, i, j - 1);
                    }
                    if (j + 1 < colSize && grid[i][j + 1] == '1') {
                        unionSetArray.union(i, j, i, j + 1);
                    }
                }
            }
        }

        return unionSetArray.sets;
    }

    public static int numIslands3(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    ans++;
                    infect(grid, i, j);
                }
            }
        }

        return ans;
    }

    private static void infect(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1') {
            return;
        }

        grid[i][j] = '0';
        infect(grid, i - 1, j);
        infect(grid, i + 1, j);
        infect(grid, i, j - 1);
        infect(grid, i, j + 1);
    }

    public static char[][] generateRandomGrid(int row, int col) {
        char[][] grid = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                grid[i][j] = Math.random() < 0.5 ? '1' : '0';
            }
        }
        return grid;
    }

    public static void printGrid(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (j == grid[0].length - 1) {
                    System.out.println();
                } else {
                    System.out.print(grid[i][j] + " ");
                }
            }
        }
    }

    public static char[][] copy(char[][] grid) {
        char[][] copyGrid = new char[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                copyGrid[i][j] = grid[i][j];
            }
        }

        return copyGrid;
    }

    public static void main(String[] args) {
        long start = 0;
        long end = 0;
        int testTime = 100_0000;
        int row = 3;
        int col = 3;
        char[][] grid1 = generateRandomGrid(row, col);
        char[][] grid2 = copy(grid1);
        char[][] grid3 = copy(grid1);

        System.out.println(String.format("随机生成二维数组的规模：%s * %s", row, col));

        start = System.currentTimeMillis();
        int numIslands = numIslands(grid1);
        end = System.currentTimeMillis();
        System.out.println(String.format("并查集使用HashMap实现，结果：%s，执行消耗时间：%s ms", numIslands, end - start));

        start = System.currentTimeMillis();
        int numIslands2 = numIslands2(grid2);
        end = System.currentTimeMillis();
        System.out.println(String.format("并查集使用数组实现，结果：%s，执行消耗时间：%s ms", numIslands2, end - start));

        start = System.currentTimeMillis();
        int numIslands3 = numIslands3(grid3);
        end = System.currentTimeMillis();
        System.out.println(String.format("感染方法，结果：%s，执行消耗时间：%s ms", numIslands3, end - start));

        printGrid(grid1);
        System.out.println("====================");
        for (char[] chars : grid1) {
            System.out.println(chars[0] + " " + chars[1]);
        }
    }
}
