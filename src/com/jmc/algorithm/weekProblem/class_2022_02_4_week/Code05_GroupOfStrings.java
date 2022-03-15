package com.jmc.algorithm.weekProblem.class_2022_02_4_week;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试链接：https://leetcode-cn.com/problems/groups-of-strings/
 *
 * @Author jmc
 * @Description
 * @Date 2022/3/11 21:15
 **/
public class Code05_GroupOfStrings {
    public static int[] groupStrings(String[] words) {
        int N = words.length;
        if (words == null || N == 0) {
            return new int[]{};
        }

        int[] nums = new int[N];
        Map<Integer, Integer> indexMap = new HashMap<>();
        UnionFind uf = new UnionFind(N);
        for (int i = 0; i < N; i++) {
            char[] chars = words[i].toCharArray();
            for (char ch : chars) {
                nums[i] |= 1 << (ch - 'a');
            }
            if (indexMap.containsKey(nums[i])) {
                // 合并
                uf.union(indexMap.get(nums[i]), i);
            } else {
                indexMap.put(nums[i], i);
            }
        }

        for (int i = 0; i < N; i++) {
            // 增加一个字符
            for (int j = 0; j < 26; j++) {
                int numAdd = nums[i];
                numAdd |= 1 << j;
                uf.union(indexMap.get(numAdd), i);
            }

            // 减少一个字符
            for (int j = 0; j < 26; j++) {
                int numSub = nums[i];
                if ((nums[i] & (1 << j)) != 0) {
                    numSub ^= 1 << j;
                    uf.union(indexMap.get(numSub), i);
                }
            }

            // 替换一个字符
            for (int j = 0; j < 26; j++) {
                if ((nums[i] & (1 << j)) != 0) {
                    // 000000...001110
                    // 000000...001100
                    int temp = nums[i] ^ (1 << j);
                    for (int k = 0; k < 26; k++) {
                        int num = temp | (1 << k);
                        uf.union(indexMap.get(num), i);
                    }
                }
            }
        }

        return new int[]{uf.sets(), uf.maxinum()};
    }

    public static class UnionFind {
        private int[] parent;
        private int[] size;
        private int[] help;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            help = new int[n];
            for (int i = 0; i < n; i++) {
                size[i] = 1;
                parent[i] = i;
            }
        }

        public int find(int i) {
            int hi = 0;
            while (i != parent[i]) {
                help[hi++] = i;
                i = parent[i];
            }

            while (hi > 0) {
                parent[help[--hi]] = i;
            }
            return i;
        }

        public void union(Integer i, Integer j) {
            if (i == null || j == null) {
                return;
            }

            int fi = find(i);
            int fj = find(j);
            if (fi != fj) {
                int si = size[fi];
                int sj = size[fj];
                if (si > sj) {
                    parent[fj] = fi;
                    size[fi] = si + sj;
                    size[fj] = 0;
                } else {
                    parent[fi] = fj;
                    size[fj] = si + sj;
                    size[fi] = 0;
                }
            }
        }

        public int sets() {
            int ans = 0;
            for (int i = 0; i < parent.length; i++) {
                ans += parent[i] == i ? 1 : 0;
            }
            return ans;
        }

        public int maxinum() {
            int ans = 0;
            for (int i = 0; i < size.length; i++) {
                ans = Math.max(ans, size[i]);
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        String[] words = new String[]{"b", "q"};
        int N = words.length;
        int[] num = new int[N];
        for (int i = 0; i < N; i++) {
            String str = words[i];
            char[] chars = str.toCharArray();
            for (int j = 0; j < chars.length; j++) {
                num[i] |= 1 << (chars[j] - 'a');
            }
        }

        for (int n : num) {
            System.out.println(print(n));
        }
        System.out.println("groupOfString start ...");
        System.out.println(Arrays.toString(groupStrings(words)));
    }

    public static String print(int num) {
        StringBuffer sb = new StringBuffer();
        for (int i = 31; i >= 0; i--) {
            sb.append(((1 << i) & num) != 0 ? "1" : "0");
        }
        return sb.toString();
    }
}