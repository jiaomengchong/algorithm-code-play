package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/range-module/
 *
 * @Author jmc
 * @Description
 * @Date 2022/6/20 18:55
 **/
public class Problem_0715_RangeModule {
    static class RangeModule {
        public class Node {
            public Node left;
            public Node right;
            public int sum;
            public int lazy;
            public int change;
            public boolean update;
        }

        public Node root;
        public int size;

        public RangeModule() {
            root = new Node();
            size = (int) 1e9 + 10;
        }

        private void pushUp(Node node) {
            node.sum = node.left.sum + node.right.sum;
        }

        private void pushDown(Node root, int ln, int rn) {
            if (root.left == null) {
                root.left = new Node();
            }

            if (root.right == null) {
                root.right = new Node();
            }

            if (root.update) {
                root.left.update = true;
                root.right.update = true;
                root.left.change = root.change;
                root.right.change = root.change;
                root.left.lazy = 0;
                root.right.lazy = 0;
                root.left.sum = root.change == -1 ? 0 : ln * root.change;
                root.right.sum = root.change == -1 ? 0 : rn * root.change;
                root.update = false;
            }

            if (root.lazy != 0) {
                root.left.lazy += root.lazy;
                root.right.lazy += root.lazy;
                root.left.sum += root.lazy * ln;
                root.right.sum += root.lazy * rn;
                root.lazy = 0;
            }
        }

        public void addRange(int left, int right) {
            update(root, 1, size - 1, left, right - 1, 1);
        }

        private void add(Node root, int l, int r, int start, int end, int value) {
            if (start <= l && r <= end) {
                // 全包
                root.sum += (r - l + 1) * value;
                root.lazy += value;
                return;
            }
            int mid = (l + r) >> 1;
            pushDown(root, mid - l + 1, r - mid);
            if (start <= mid) {
                add(root.left, l, mid, start, end, value);
            }
            if (end > mid) {
                add(root.right, mid + 1, r, start, end, value);
            }
            pushUp(root);
        }

        public boolean queryRange(int left, int right) {
            return query(root, 1, size - 1, left, right - 1) == right - left;
        }

        private int query(Node root, int l, int r, int start, int end) {
            if (start <= l && r <= end) {
                // 全包
                return root.sum;
            }
            int mid = (l + r) >> 1;
            pushDown(root, mid - l + 1, r - mid);
            int ans = 0;
            if (start <= mid) {
                ans += query(root.left, l, mid, start, end);
            }
            if (end > mid) {
                ans += query(root.right, mid + 1, r, start, end);
            }
            return ans;
        }

        public void removeRange(int left, int right) {
            update(left, right - 1);
        }

        private void update(int left, int right) {
            update(root, 1, size - 1, left, right, -1);
        }

        private void update(Node root, int l, int r, int start, int end, int value) {
            if (start <= l && r <= end) {
                root.change = value;
                root.update = true;
                root.sum = value > 0 ? value * (r - l + 1) : 0;
                root.lazy = 0;
                return;
            }
            int mid = (l + r) >> 1;
            pushDown(root, mid - l + 1, r - mid);
            if (start <= mid) {
                update(root.left, l, mid, start, end, value);
            }
            if (end > mid) {
                update(root.right, mid + 1, r, start, end, value);
            }
            pushUp(root);
        }
    }

    public static void main(String[] args) {
        RangeModule rangeModule = new RangeModule();
        rangeModule.addRange(6, 8); // [6, 7]
        rangeModule.removeRange(7, 8); // [6]
        rangeModule.removeRange(8, 9); // [6]
        rangeModule.addRange(8, 9); // [6] [8]
        rangeModule.removeRange(1, 3); // [6] [8]
        rangeModule.addRange(1, 8); // [1, 8]
        boolean q1 = rangeModule.queryRange(2, 4);
        System.out.println(q1);
        boolean q2 = rangeModule.queryRange(2, 9);
        System.out.println(q2);
        boolean q3 = rangeModule.queryRange(4, 6);
        System.out.println(q3);

    }
}
