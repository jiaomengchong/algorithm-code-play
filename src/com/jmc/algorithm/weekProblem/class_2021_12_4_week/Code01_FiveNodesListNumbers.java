package com.jmc.algorithm.weekProblem.class_2021_12_4_week;

import java.util.HashSet;
import java.util.Set;

/**
 * 来自美团
 * 给定一个无向图
 * 从任何一个点x出发，比如有一条路径: x -> a -> b -> c -> y
 * 这条路径上有5个点并且5个点都不一样的话，我们说(x,a,b,c,y)是一条合法路径
 * 这条合法路径的代表，就是x,a,b,c,y所组成的集合，我们叫做代表集合
 * 如果从b到y，还有一条路径叫(b,a,c,x,y)，那么(x,a,b,c,y)和(b,a,c,x,y)是同一个代表集合
 * 返回这个无向图中所有合法路径的代表集合数量
 * 题目给定点的数量n <= 15，边的数量m <= 60
 * 所有的点编号都是从0~n-1的
 *
 * @Author jmc
 * @Description
 * @Date 2021/12/27 13:58
 **/
public class Code01_FiveNodesListNumbers {
    public static int ways1(int[][] graph) {
        if (graph == null || graph.length == 0) {
            return 0;
        }

        // graph[i] = {a, b, c, d},表示点i的邻接矩阵分别是a b c d
        // graph[j] = {a, d},表示点j的邻接矩阵分别是a d
        // 每个节点的状态可以用位信息来表示，00001011表示点0、1、3都来过了
        Set<Integer> sets = new HashSet<>();
        for (int i = 0; i < graph.length; i++) {
            process1(0, 0, i, graph, sets);
        }
        return sets.size();
    }

    private static void process1(int status, int len, int cur, int[][] graph, Set<Integer> sets) {
        if ((status & (1 << cur)) == 0)  {
            len++;
            status |= 1 << cur;
            if (len == 5) {
                sets.add(status);
            } else {
                for (int next : graph[cur]) {
                    process1(status, len, next, graph, sets);
                }
            }
        }
    }

    public static void main(String[] args) {
        // 00001011
        int[][] graph = new int[][]{
                {5, 1, 2, 3, 4},
                {1, 2, 3, 4, 5},
                {2, 1, 7, 8, 6},
                {5, 6, 7, 8, 4, 3},
                {3, 2, 4, 7, 8},
                {3, 2, 1, 4, 5},
                {5, 4, 3, 2, 1},
                {5, 3, 1, 4, 2},
                {3, 2, 1, 4, 5},
        };
        System.out.println(ways1(graph));
    }
}
