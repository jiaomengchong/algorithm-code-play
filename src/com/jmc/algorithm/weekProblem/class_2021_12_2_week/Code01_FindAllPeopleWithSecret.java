package com.jmc.algorithm.weekProblem.class_2021_12_2_week;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 找出知晓秘密的所有专家
 * 链接测试 : https://leetcode.com/problems/find-all-people-with-secret/
 *
 * @Author jmc
 * @Description
 * @Date 2021/12/13 10:50
 **/
public class Code01_FindAllPeopleWithSecret {
    public static List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        List<Integer> ans = new ArrayList<>();
        if (meetings == null || meetings.length == 0 || meetings[0].length == 0) {
            return ans;
        }

        UnionFind unionFind = new UnionFind(n, firstPerson);
        Arrays.sort(meetings, (a, b) -> a[2] - b[2]);
        int m = meetings.length;
        int[] help = new int[m << 1];
        help[0] = meetings[0][0];
        help[1] = meetings[0][1];
        int size = 2;
        for (int i = 1; i < m; i++) {
            if (meetings[i][2] != meetings[i - 1][2]) {
                share(unionFind, help, size);
                help[0] = meetings[i][0];
                help[1] = meetings[i][1];
                size = 2;
            } else {
                help[size++] = meetings[i][0];
                help[size++] = meetings[i][1];
            }
        }
        share(unionFind, help, size);
        for (int i = 0; i < n; i++) {
            if (unionFind.isKnow(i)) {
                ans.add(i);
            }
        }
        return ans;
    }

    private static void share(UnionFind unionFind, int[] help, int size) {
        for (int i = 0; i < size; i += 2) {
            unionFind.union(help[i], help[i + 1]);
        }

        for (int i = 0; i < size; i++) {
            if (!unionFind.isKnow(help[i])) {
                unionFind.isolate(help[i]);
            }
        }
    }

    public static class UnionFind {
        private int[] parents;
        private int[] size;
        private int[] help;
        private boolean[] secrets;

        public UnionFind(int n, int firstPerson) {
            parents = new int[n];
            size = new int[n];
            help = new int[n];
            secrets = new boolean[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
                size[i] = 1;
            }
            secrets[0] = true;
            parents[firstPerson] = 0;
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

        private boolean isSameSet(int i, int j) {
            return findFather(i) == findFather(j);
        }

        private void union(int i, int j) {
            int f1 = findFather(i);
            int f2 = findFather(j);
            if (f1 != f2) {
                int big = size[f1] < size[f2] ? f2 : f1;
                int small = big == f1 ? f2 : f1;
                parents[small] = big;
                size[big] += size[small];
                secrets[big] |= secrets[small];
            }
        }

        private void isolate(int i) {
            parents[i] = i;
        }

        private boolean isKnow(int i) {
            return secrets[findFather(i)];
        }
    }

    public static void main(String[] args) {
        int n = 6;
        int[][] meetings = {{0,2,1},{1,3,1},{4,5,1}};
        int firstPerson = 1;
        List<Integer> ans = findAllPeople(n, meetings, firstPerson);
        System.out.println(ans);
    }
}
