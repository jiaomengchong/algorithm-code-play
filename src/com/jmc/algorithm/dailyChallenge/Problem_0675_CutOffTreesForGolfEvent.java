package com.jmc.algorithm.dailyChallenge;

import java.util.*;

/**
 * @Author jmc
 * @Description
 * @Date 2022/5/24 17:45
 **/
public class Problem_0675_CutOffTreesForGolfEvent {
    public static int cutOffTree(List<List<Integer>> forest) {
        // 输入：forest = [[1,2,3],[0,0,4],[7,6,5]]
        // 输出：6
        int M = forest.size();
        int N = forest.get(0).size();
        List<int[]> trees = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (forest.get(i).get(j) > 1) {
                    trees.add(new int[]{i, j});
                }
            }
        }
        Collections.sort(trees, (a, b) -> forest.get(a[0]).get(a[1]) - forest.get(b[0]).get(b[1]));

        int startRow = 0;
        int startCol = 0;
        int ans = 0;
        for (int i = 0; i < trees.size(); i++) {
            int steps = bfs(forest, M, N, startRow, startCol, trees.get(i)[0], trees.get(i)[1]);
            if (steps == -1) {
                return -1;
            }
            ans += steps;
            startRow = trees.get(i)[0];
            startCol = trees.get(i)[1];
        }
        return ans;
    }

    private static int bfs(List<List<Integer>> forest, int M, int N, int startRow, int startCol, int endRow, int endCol) {
        if (startRow == endRow && startCol == endCol) {
            return 0;
        }
        int steps = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[M][N];
        queue.offer(new int[]{startRow, startCol});
        visited[startRow][startCol] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            steps++;
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int[] direction = new int[]{-1, 0, 1, 0, -1};
                for (int j = 1; j < direction.length; j++) {
                    int newRow = cur[0] + direction[j - 1];
                    int newCol = cur[1] + direction[j];
                    if (newRow >= 0 && newRow < M && newCol >= 0 && newCol < N && !visited[newRow][newCol] && forest.get(newRow).get(newCol) > 0) {
                        if (newRow == endRow && newCol == endCol ) {
                            return steps;
                        }
                        queue.offer(new int[]{newRow, newCol});
                        visited[newRow][newCol] = true;
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        // forest = [[1,2,3],[0,0,0],[7,6,5]]
        List<List<Integer>> forest = new ArrayList<>();
        int[][] arr = new int[][]{{54581641,64080174,24346381,69107959},{86374198,61363882,68783324,79706116},{668150,92178815,89819108,94701471},{83920491,22724204,46281641,47531096},{89078499,18904913,25462145,60813308}};
//        int[][] arr = new int[][]{{1,2,3},{0,0,0},{7,6,5}};
        // 1 2 3
        // 0 0 0
        // 7 6 5
        for (int i = 0; i < arr.length; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < arr[0].length; j++) {
                list.add(arr[i][j]);
            }
            forest.add(list);
        }
        System.out.println(cutOffTree(forest));
    }
}
