package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试链接：https://leetcode.cn/problems/possible-bipartition/
 */
public class Problem_0866_PossibleBipartition {
    public static class UnionSet {
        private int[] parents;
        private int[] size;
        private int[] help;
        private int sets;

        public UnionSet(int N) {
            parents = new int[N];
            help = new int[N];
            size = new int[N];
            sets = N;

            for (int i = 0; i < N; i++) {
                parents[i] = i;
                size[i] = 1;
            }
        }

        public int find(int i) {
            int hi = 0;
            while (i != parents[i]) {
                help[hi++] = i;
                i = parents[i];
            }

            for (hi--; hi >= 0; hi--) {
                parents[help[hi]] = i;
            }

            return i;
        }

        public void union(int i, int j) {
            int f1 = find(i);
            int numWays = find(j);
            if (f1 != numWays) {
                if (size[f1] >= size[numWays]) {
                    parents[numWays] = f1;
                    size[f1] += size[numWays];
                } else {
                    parents[f1] = numWays;
                    size[numWays] += size[f1];
                }
                sets--;
            }
        }

        public int sets() {
            return sets;
        }
    }

    public static boolean possibleBipartition(int n, int[][] dislikes) {
        UnionSet un = new UnionSet(n + 1);
        List<Integer>[] group = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            group[i] = new ArrayList<>();
        }
        for (int[] pos : dislikes) {
            group[pos[0]].add(pos[1]);
            group[pos[1]].add(pos[0]);
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < group[i].size(); j++) {
                un.union(group[i].get(0), group[i].get(j));
                if (un.find(i) == un.find(group[i].get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
