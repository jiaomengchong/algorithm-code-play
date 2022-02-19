package com.jmc.algorithm.greatOffer.class20;

import java.util.HashMap;

/**
 * 按公因数计算最大组件大小
 * 测试链接：https://leetcode-cn.com/problems/largest-component-size-by-common-factor/
 *
 * @author jmc
 * @version 1.0
 * @date 2021/7/6 10:52
 */
public class Code02_LargestComponetSizeByCommonFactors {
    public static int largestComponentSize(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int N = nums.length;
        UnionFind unionFind = new UnionFind(N);
        HashMap<Integer, Integer> factorMap = new HashMap<>();
        int num, limit;
        for (int i = 0; i < N; i++) {
            num = nums[i];
            limit = (int) Math.sqrt(num);
            for (int j = 1; j <= limit; j++) {
                if (num % j == 0) {
                    if (j != 1) {
                        if (!factorMap.containsKey(j)) {
                            factorMap.put(j, i);
                        } else {
                            unionFind.union(factorMap.get(j), i);
                        }
                    }
                    int other = num / j;
                    if (!factorMap.containsKey(other)) {
                        factorMap.put(other, i);
                    } else {
                        unionFind.union(factorMap.get(other), i);
                    }
                }
            }
        }

        return unionFind.maxSize(N);
    }

    public static class UnionFind {
        private int[] helps;
        private int[] parents;
        private int[] sizes;

        public UnionFind(int N) {
            helps = new int[N];
            parents = new int[N];
            sizes = new int[N];
            for (int i = 0; i < N; i++) {
                parents[i] = i;
                sizes[i] = 1;
            }
        }

        public int maxSize(int N) {
            int ans = 0;
            for (int i = 0; i < N; i++) {
                ans = Math.max(ans, sizes[i]);
            }
            return ans;
        }

        public int find(int index) {
            int hi = 0;
            while (index != parents[index]) {
                helps[hi++] = index;
                index = parents[index];
            }

            for (--hi; hi >= 0; hi--) {
                parents[helps[hi]] = index;
            }

            return index;
        }

        public void union(int i, int j) {
            int a = find(i);
            int b = find(j);
            if (a != b) {
                int big = a >= b ? a : b;
                int small = a == big ? b : a;
                parents[small] = big;
                sizes[big] = sizes[big] + sizes[small];
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{99, 38, 73, 74, 44, 19, 25, 24, 57};
        System.out.println(largestComponentSize(nums));

    }
}
