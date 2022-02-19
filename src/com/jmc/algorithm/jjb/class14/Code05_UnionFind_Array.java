package com.jmc.algorithm.jjb.class14;

/**
 * 并查集 - 数组
 *
 * @author jmc
 * @version 1.0
 * @date 2020/12/23 18:49
 */

public class Code05_UnionFind_Array {

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
}
