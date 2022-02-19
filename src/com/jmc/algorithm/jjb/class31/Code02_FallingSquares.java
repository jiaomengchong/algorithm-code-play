package com.jmc.algorithm.jjb.class31;

import java.util.*;

/**
 * https://leetcode.com/problems/falling-squares/
 *
 * @author jmc
 * @version 1.0
 * @date 2021/2/27 10:26
 */
public class Code02_FallingSquares {
    public static class SegmentTree {
        private int MAXN;
        private int[] max;
        private int[] change;
        private boolean[] update;

        public SegmentTree(int size) {
            MAXN = size + 1;
            max = new int[MAXN << 2];
            change = new int[MAXN << 2];
            update = new boolean[MAXN << 2];
        }

        public void pushUp(int rt) {
            max[rt] = Math.max(max[rt << 1], max[rt << 1 | 1]);
        }

        public void pushDown(int ln, int rn, int rt) {
            if (update[rt]) {
                update[rt << 1] = true;
                max[rt << 1] = change[rt];
                change[rt << 1] = change[rt];
                update[rt << 1 | 1] = true;
                max[rt << 1 | 1] = change[rt];
                change[rt << 1 | 1] = change[rt];
                update[rt] = false;
            }
        }

        public void update(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && r <= R) {
                max[rt] = C;
                update[rt] = true;
                change[rt] = C;
                return;
            }

            int mid = (l + r) >> 1;
            pushDown(mid - l + 1, r - mid, rt);
            if (L <= mid) {
                update(L, R, C, l, mid, rt << 1);
            }
            if (mid < R) {
                update(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            pushUp(rt);
        }

        public int query(int L, int R, int l, int r, int rt) {
            if (L <= l && r <= R) {
                return max[rt];
            }
            int mid = (l + r) >> 1;
            pushDown(mid - l + 1, r - mid, rt);
            int left = 0;
            int right = 0;
            if (L <= mid) {
                left = query(L, R, l, mid, rt << 1);
            }
            if (mid < R) {
                right = query(L, R, mid + 1, r, rt << 1 | 1);
            }

            return Math.max(left, right);
        }
    }

    public static List<Integer> fallingSquares(int[][] positions) {
        Map<Integer, Integer> map = index(positions);
        int N = map.size();
        SegmentTree segmentTree = new SegmentTree(N);
        List<Integer> ans = new ArrayList<>();
        int max = 0;
        for (int[] pos : positions) {
            int L = map.get(pos[0]);
            int R = map.get(pos[0] + pos[1] - 1);
            int height = segmentTree.query(L, R, 1, N, 1) + pos[1];
            max = Math.max(max, height);
            segmentTree.update(L, R, height, 1, N, 1);
            ans.add(max);
        }
        return ans;
    }

    private static Map<Integer, Integer> index(int[][] positions) {
        TreeSet<Integer> map = new TreeSet();
        for (int[] pos : positions) {
            map.add(pos[0]);
            map.add(pos[0] + pos[1] - 1);
        }

        int count = 0;
        Map<Integer, Integer> ans = new HashMap<>();
        for (Integer pos : map) {
            ans.put(pos, ++count);
        }
        return ans;
    }

    public static void main(String[] args) {
//        int[][] positions = {{1, 2}, {2, 3}, {6, 1}};
//        int[][] positions = {{100, 100}, {200, 100}};
        int[][] positions = {{2, 1}, {2, 9}, {1, 8}};
        System.out.println(fallingSquares(positions));
    }
}
