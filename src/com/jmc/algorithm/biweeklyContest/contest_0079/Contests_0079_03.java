package com.jmc.algorithm.biweeklyContest.contest_0079;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 测试链接：https://leetcode.cn/contest/biweekly-contest-79/problems/maximum-total-importance-of-roads/
 */
public class Contests_0079_03 {
    public static class Info {
        private int in;
        private int out;

        public Info(int in, int out) {
            this.in = in;
            this.out = out;
        }
    }
    
    public static class MyComparator implements Comparator<Info> {

        @Override
        public int compare(Info o1, Info o2) {
            return o1.out != o2.out ? o2.out - o1.out : o2.in - o1.in;
        }
    }
    
    public static long maximumImportance(int n, int[][] roads) {
        // 输入：n = 5, roads = [[0,1],[1,2],[2,3],[0,2],[1,3],[2,4]]
        // 输出：43
        int M = roads.length;
        int N  = roads[0].length;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < M; i++) {
            pq.add(roads[i][0]);
            pq.add(roads[i][1]);
        }
        return 0l;
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] roads = new int[][]{{0,1},{1,2},{2,3},{0,2},{1,3},{2,4}};
        System.out.println(maximumImportance(n, roads));
    }
}
