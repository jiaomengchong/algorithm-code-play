package com.jmc.algorithm.weekProblem.class_2021_12_1_week;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * int n, int[][] roads, int x, int y
 * n表示城市数量，城市编号0~n-1
 * roads[i][j] == distance，表示城市i到城市j距离为distance(无向图)
 * 求城市x到城市y的最短距离
 *
 * @Author jmc
 * @Description
 * @Date 2021/12/7 10:42
 **/
public class Code01_XToYMinDistance {
    public static int ways1(int n, int[][] roads, int x, int y) {
        // 生成邻接矩阵
        int[][] map = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                map[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int[] road : roads) {
            map[road[0]][road[1]] = Math.min(map[road[0]][road[1]], road[2]);
            map[road[1]][road[0]] = Math.min(map[road[1]][road[0]], road[2]);
        }
        boolean[] visited = new boolean[n + 1];
        return process1(x, y, n, map, visited);
    }

    private static int process1(int cur, int target, int n, int[][] map, boolean[] visited) {
        if (visited[cur]) {
            return Integer.MAX_VALUE;
        }
        if (cur == target) {
            return 0;
        }

        int ans = Integer.MAX_VALUE;
        visited[cur] = true;
        for (int next = 1; next <= n; next++) {
            if (!visited[next] && map[cur][next] != Integer.MAX_VALUE) {
                int cost = process1(next, target, n, map, visited);
                if (cost != Integer.MAX_VALUE) {
                    ans = Math.min(ans, cost + map[cur][next]);
                }
            }
        }
        visited[cur] = false;

        return ans;
    }

    public static int ways2(int n, int[][] roads, int x, int y) {
        int[][] map = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int[] road : roads) {
            map[road[1]][road[0]] = Math.min(map[road[1]][road[0]], road[2]);
            map[road[0]][road[1]] = Math.min(map[road[0]][road[1]], road[2]);
        }
        boolean[] visited = new boolean[n + 1];
        PriorityQueue<Node> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a.pathSum));
        heap.add(new Node(x, 0));
        while (!heap.isEmpty()) {
            Node cur = heap.poll();
            if (visited[cur.city]) {
                continue;
            }
            if (cur.city == y) {
                return cur.pathSum;
            }
            for (int next = 1; next <= n; next++) {
                if (next != cur.city && !visited[next] && map[cur.city][next] != Integer.MAX_VALUE) {
                    heap.add(new Node(next, map[cur.city][next] + cur.pathSum));
                }
            }
        }

        return Integer.MAX_VALUE;
    }

    public static class Node {
        // 城市编号
        private int city;
        // 从源出发点到该城市的路径和
        private int pathSum;

        public Node(int city, int pathSum) {
            this.city = city;
            this.pathSum = pathSum;
        }
    }

    public static void main(String[] args) {
        // 城市数量n，下标从1开始，不从0开始
        int n = 4;
        // 边的数量m，m的值不能大于n * (n-1) / 2
        int m = 4;
        // 所的路有m条
        // [a,b,c]表示a和b之间有路，距离为3，根据题意，本题中的边都是无向边
        // 假设有两条路
        // [1,3,7]，这条路是从1到3，距离是7
        // [1,3,4]，这条路是从1到3，距离是4
        // 那么应该忽略[1,3,7]，因为[1,3,4]比它好
        int[][] roads = new int[m][3];
        roads[0] = new int[]{1, 2, 4};
        roads[1] = new int[]{1, 3, 1};
        roads[2] = new int[]{1, 4, 1};
        roads[3] = new int[]{2, 3, 1};
        // 求从x到y的最短距离是多少，x和y应该在[1,n]之间
        int x = 2;
        int y = 4;

        // 暴力方法的解
        System.out.println(ways1(n, roads, x, y));
        System.out.println(ways2(n, roads, x, y));
    }
}
