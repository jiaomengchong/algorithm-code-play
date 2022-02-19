package com.jmc.algorithm.weekProblem.class_2021_12_2_week;

/**
 * 来自美团
 * 所有黑洞的中心点记录在holes数组里
 * 比如[[3,5] [6,9]]表示，第一个黑洞在(3,5)，第二个黑洞在(6,9)
 * 并且所有黑洞的中心点都在左下角(0,0)，右上角(x,y)的区域里
 * 飞船一旦开始进入黑洞，就会被吸进黑洞里
 * 返回：
 * 如果统一所有黑洞的半径，最大半径是多少，依然能保证飞船从(0,0)能到达(x,y)
 * 1000  1000*1000  10^6 * 二分
 *
 * @Author jmc
 * @Description
 * @Date 2021/12/13 17:43
 **/
public class Code02_AwayFromBlackHole {
    public static int maxRadius(int[][] holes, int x, int y) {
        if (holes == null || holes.length == 0 || holes[0].length == 0 || x <= 0 || y <= 0) {
            return -1;
        }

        int ans = 0;
        int L = 1;
        int R = Math.max(x, y);
        while (R >= L) {
            int mid = (L + R) / 2;
            if (ok(holes, mid, x, y)) {
                ans = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }

        return ans;
    }

    private static boolean ok(int[][] holes, int r, int x, int y) {
        int n = holes.length;
        UnionFind unionFind = new UnionFind(holes, r);
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (touch(holes[i][0], holes[i][1], holes[j][0], holes[j][1], r)) {
                    unionFind.union(i, j);
                }
                if (unionFind.black(i, x, y)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean touch(int x1, int y1, int x2, int y2, int r) {
        return Math.pow((r << 1), 2) >= Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2);
    }

    public static class UnionFind {
        private int[] parents;
        private int[] sizes;
        private int[] help;
        private int[] xMin;
        private int[] xMax;
        private int[] yMin;
        private int[] yMax;

        public UnionFind(int[][] holes, int r) {
            int n = holes.length;
            parents = new int[n];
            sizes = new int[n];
            help = new int[n];
            xMin = new int[n];
            xMax = new int[n];
            yMin = new int[n];
            yMax = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
                sizes[i] = 1;
                xMin[i] = holes[i][0] - r;
                xMax[i] = holes[i][0] + r;
                yMin[i] = holes[i][1] - r;
                yMax[i] = holes[i][1] + r;
            }
        }

        private boolean isSameSet(int i, int j) {
            return findFather(i) == findFather(j);
        }

        private int findFather(int i) {
            int cur = i;
            int size = 0;
            while (parents[cur] != cur) {
                help[size++] = cur;
                cur = parents[cur];
            }
            while (size > 0) {
                parents[help[--size]] = cur;
            }
            return cur;
        }

        private void union(int i, int j) {
            int f1 = findFather(i);
            int f2 = findFather(j);
            if (f1 != f2) {
                int big = sizes[f1] < sizes[f2] ? f2 : f1;
                int small = big == f1 ? f2 : f1;
                sizes[big] += sizes[small];
                xMin[big] = Math.min(xMin[big], xMin[small]);
                xMax[big] = Math.max(xMax[big], xMax[small]);
                yMin[big] = Math.min(yMin[big], yMin[small]);
                yMax[big] = Math.max(yMax[big], yMax[small]);
            }
        }

        private boolean black(int i, int x, int y) {
            return (xMin[findFather(i)] <= 0 && xMax[findFather(i)] >= x) ||
                    (xMin[findFather(i)] <= 0 && yMin[findFather(i)] <= 0) ||
                    (xMax[findFather(i)] >= x && yMax[findFather(i)] >= y) ||
                    (yMin[findFather(i)] <= 0 && yMax[findFather(i)] >= y);
        }
    }

    public static void main(String[] args) {
        int[][] holes = { { 1, 2 }, { 4, 4 }, { 3, 0 }, { 5, 2 } };
        int x = 4;
        int y = 6;
        System.out.println(maxRadius(holes, x, y));
    }
}
