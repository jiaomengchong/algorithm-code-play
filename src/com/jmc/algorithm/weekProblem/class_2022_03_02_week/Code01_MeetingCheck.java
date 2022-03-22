package com.jmc.algorithm.weekProblem.class_2022_03_02_week;

/**
 * @Author jmc
 * @Description
 * @Date 2022/3/18 11:29
 **/
public class Code01_MeetingCheck {

    public static class SegmentTree {
        //       1
        //    2     3
        //  4   5 6   7
        private int N;
        private int[] arr;
        private int[] lazy;
        private int[] sum;
        private int[] update;
        private boolean[] change;

        public SegmentTree(int[] origin) {
            N = origin.length;
            arr = new int[N + 1];
            for (int i = 0; i < N; i++) {
                arr[i + 1] = origin[i];
            }
            lazy = new int[N << 2];
            sum = new int[N << 2];
            update = new int[N << 2];
            change = new boolean[N << 2];
        }

        private void build(int l, int r, int rt) {
            if (l == r) {
                sum[rt] = arr[l];
                return;
            }
            int mid = (l + r) >> 1;
            build(l, mid, rt << 1);
            build(mid + 1, r, rt << 1 | 1);
            pushUp(rt);
        }

        private void pushUp(int rt) {
            sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
        }

        private void add(int L, int R, int C) {
            add(L, R, C, 1, N, 1);
        }

        private void add(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && R >= r) {
                // 全包情况
                sum[rt] += (r - l + 1) * C;
                lazy[rt] += C;
                return;
            }
            int mid = (l + r) >> 1;
            pushDown(mid - l + 1, r - mid, rt);
            if (L <= mid) {
                add(L, R, C, l, mid, rt << 1);
            }
            if (R > mid) {
                add(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            pushUp(rt);
        }

        private void pushDown(int ln, int rn, int rt) {
            if (change[rt]) {
                change[rt << 1] = true;
                update[rt << 1] = update[rt];
                lazy[rt << 1] = 0;
                sum[rt << 1] = ln * update[rt];
                change[rt << 1 | 1] = true;
                update[rt << 1 | 1] = update[rt];
                lazy[rt << 1 | 1] = 0;
                sum[rt << 1 | 1] = update[rt] * rn;
                change[rt] = false;
            }

            if (lazy[rt] != 0) {
                lazy[rt << 1] += lazy[rt];
                sum[rt << 1] += lazy[rt] * ln;
                lazy[rt << 1 | 1] += lazy[rt];
                sum[rt << 1 | 1] += lazy[rt] * rn;
                lazy[rt] = 0;
            }
        }

        private void update(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && R >= r) {
                // 全包
                change[rt] = true;
                update[rt] = C;
                lazy[rt] = 0;
                sum[rt] = (r - l + 1) * C;
                return;
            }
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
                // 全包
                return sum[rt];
            }
            int mid = (l + r) >> 1;
            pushDown(mid - l + 1, r - mid, rt);
            int ans = 0;
            if (L <= mid) {
                ans += query(L, R, l, mid, rt << 1);
            }
            if (R > mid) {
                ans += query(L, R, mid + 1, r, rt << 1 | 1);
            }
            return ans;
        }
    }

    public static class Right {
        private int[] arr;

        public Right(int[] origin) {
            int N = origin.length;
            arr = new int[N + 1];
            for (int i = 0; i < N; i++) {
                arr[i + 1] = origin[i];
            }
        }

        public void add(int L, int R, int C) {
            for (int i = L; i <= R; i++) {
                arr[i] += C;
            }
        }

        public void update(int L, int R, int C) {
            for (int i = L; i <= R; i++) {
                arr[i] = C;
            }
        }

        public int query(int L, int R) {
            int ans = 0;
            for (int i = L; i <= R; i++) {
               ans += arr[i];
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        int[] origin1 = new int[]{1, 2, 3, 4, 5};
        Right right = new Right(origin1);
        right.update(1, 3, 1);
        int queryRight = right.query(1, 3);
        System.out.println(queryRight);

        int[] origin2 = new int[]{1, 2, 3, 4, 5};
        SegmentTree segmentTree = new SegmentTree(origin2);
        segmentTree.build(1, 5, 1);
        segmentTree.update(1, 3, 1, 1, 6, 1);
        int querySegment = segmentTree.query(1, 3, 1, 6, 1);
        System.out.println(querySegment);
    }
}
