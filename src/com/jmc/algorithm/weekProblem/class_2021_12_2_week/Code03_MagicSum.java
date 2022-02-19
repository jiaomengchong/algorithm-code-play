package com.jmc.algorithm.weekProblem.class_2021_12_2_week;

import java.util.Arrays;

/**
 * 来自真实面试，同学给我的问题
 * arr数组长度为n, magic数组长度为m
 * 含义：
 * arr = { 3, 1, 4, 5, 7 }
 * 如果完全不改变arr中的值，那么收益就是累加和 = 3 + 1 + 4 + 5 + 7 =
 * magics = {
 * {0,2,5} 表示arr[0~2]中的任何一个值，都能改成5
 * {3,4,3} 表示arr[3~4]中的任何一个值，都能改成3
 * {1,3,7} 表示arr[1~3]中的任何一个值，都能改成7
 * }
 * 就是说，magics中的任何一组数据{a,b,c}，都表示一种操作，
 * 你可以选择arr[a~b]中任何一个数字，变成c。
 * 并且每一种操作，都可以执行任意次
 * 其中 0 <= a <= b < n
 * 那么经过若干次的魔法操作，你当然可能得到arr的更大的累加和
 * 返回arr尽可能大的累加和
 * n <= 10^7
 * m <= 10^6
 * arr中的值和c的范围 <= 10^12
 *
 * @Author jmc
 * @Description
 * @Date 2021/12/14 11:41
 **/
public class Code03_MagicSum {
    public static int ways1(int[] arr, int[][] magics) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int n = arr.length;
        Arrays.sort(magics, (a, b) -> a[2] - b[2]);
        SegmentTree segmentTree = new SegmentTree(n);
        for (int[] magic : magics) {
            segmentTree.update(magic[0] + 1, magic[1] + 1, magic[2], 1, n, 1);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += Math.max(segmentTree.query(i + 1, i + 1, 1, n, 1), arr[i]);
        }
        return ans;
    }

    public static class SegmentTree {
        private int[] max;
        private int[] change;
        private boolean[] update;

        public SegmentTree(int size) {
            int n = size + 1;
            max = new int[n << 2];
            change = new int[n << 2];
            update = new boolean[n << 2];
        }

        private void pushUp(int rt) {
            max[rt] = Math.max(max[rt << 1], max[rt << 1 | 1]);
        }

        private void pushDown(int ln, int rn, int rt) {
            if (update[rt]) {
                update[rt << 1] = true;
                update[rt << 1 | 1] = true;
                change[rt << 1] = change[rt];
                change[rt << 1 | 1] = change[rt];
                max[rt << 1] = change[rt];
                max[rt << 1 | 1] = change[rt];
                update[rt] = false;
            }
        }

        private void update(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && R >= r) {
                update[rt] = true;
                change[rt] = C;
                max[rt] = C;
                return;
            }
            // 没有全包，则下发
            int mid = (l + r) >> 1;
            pushDown(mid - l + 1, r - mid, rt);
            if (L <= mid) {
                update(L, R, C, l, mid, rt << 1);
            }
            if (R > mid) {
                update(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            pushUp(rt);
        }

        private int query(int L, int R, int l, int r, int rt) {
            if (L <= l && R >= r) {
                return max[rt];
            }
            int mid = (l + r) >> 1;
            pushDown(mid - l + 1, r - mid, rt);
            int left = 0;
            int right = 0;
            if (L <= mid) {
                left = query(L, R, l, mid, rt << 1);
            }
            if (R > mid) {
                right = query(L, R, mid + 1, r, rt << 1 | 1);
            }
            return Math.max(left, right);
        }
    }

    // 为了测试
    public static int[] generateRandomArray(int n, int value) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * value) + 1;
        }
        return arr;
    }

    // 为了测试
    public static int[][] generateRandomMagics(int n, int m, int value) {
        int[][] magics = new int[m][3];
        for (int[] magic : magics) {
            int a = (int) (Math.random() * n);
            int b = (int) (Math.random() * n);
            int c = (int) (Math.random() * value) + 1;
            magic[0] = Math.min(a, b);
            magic[1] = Math.max(a, b);
            magic[2] = c;
        }
        return magics;
    }

    public static int maxSum1(int[] arr, int[][] magics) {
        int[] help = Arrays.copyOf(arr, arr.length);
        for (int[] m : magics) {
            int l = m[0];
            int r = m[1];
            int c = m[2];
            for (int i = l; i <= r; i++) {
                help[i] = Math.max(help[i], c);
            }
        }
        int sum = 0;
        for (int num : help) {
            sum += num;
        }
        return sum;
    }

    public static void main(String[] args) {
        int n = 456;
        int m = 9999999;
        int v = 10;

        int[] arr = generateRandomArray(n, v);
        int[][] magics = generateRandomMagics(n, m, v);
        long start = System.currentTimeMillis();
        int ans = ways1(arr, magics);
        long end = System.currentTimeMillis();
        System.out.println("方法一的结果 : " + ans + ", 方法一的运行时间: " + (end - start) + " 毫秒");

        start = System.currentTimeMillis();
        int ans2 = maxSum1(arr, magics);
        end = System.currentTimeMillis();
        System.out.println("暴力方法的结果 : " + ans2 + ", 暴力方法的运行时间: " + (end - start) + " 毫秒");
    }
}
