package com.jmc.algorithm.dailyChallenge;

/**
 * @Author jmc
 * @Description
 * @Date 2022/7/19 21:02
 **/
public class Problem_0731_MyCalendarII {
    static class MyCalendarTwo {
        private int N = (int) 1e9;

        private static class Node {
            private int max;
            private int lazy;
            private int change;
            private boolean update;
            private Node left;
            private Node right;
        }

        private int size;
        private Node root;

        public MyCalendarTwo() {
            this.size = N;
            this.root = new Node();
        }

        private void pushUp(Node c) {
            c.max = Math.max(c.left.max, c.right.max);
        }

        private void pushDown(Node p, int ln, int rn) {
            if (p.left == null) {
                p.left = new Node();
            }

            if (p.right == null) {
                p.right = new Node();
            }

            if (p.update) {
                p.left.update = true;
                p.right.update = true;
                p.left.lazy = 0;
                p.right.lazy = 0;
                p.left.change += p.change;
                p.right.change += p.change;
                p.left.max += p.change;
                p.right.max += p.change;
                p.update = false;
            }

            if (p.lazy == 0) {
                return;
            }

            if (p.lazy != 0) {
                p.left.lazy += p.lazy;
                p.right.lazy += p.lazy;
                p.left.max += p.lazy;
                p.right.max += p.lazy;
                p.lazy = 0;
            }
        }

        public void update(int s, int e, int v) {
            update(root, 1, size, s, e, v);
        }

        public void update(Node c, int l, int r, int s, int e, int v) {
            if (s <= l && e >= r) {
                // 全包
                c.max += v;
                c.update = true;
                c.change += v;
                c.lazy = 0;
                return;
            }

            int mid = (l + r) >> 1;
            pushDown(c, mid - l + 1, r - mid);
            if (s <= mid) {
                update(c.left, l, mid, s, e, v);
            }
            if (e > mid) {
                update(c.right, mid + 1, r, s, e, v);
            }
            pushUp(c);
        }

        public void add(Node c, int s, int e, int v) {
            add(c, 1, size, s, e, v);
        }

        private void add(Node c, int l, int r, int s, int e, int v) {
            if (s <= l && r <= e) {
                c.lazy += v;
                c.max += v;
                return;
            }

            int mid = (l + r) >> 1;
            pushDown(c, mid - l + 1, r - mid);

            if (s <= mid) {
                add(c.left, l, mid, s, e, v);
            }

            if (e > mid) {
                add(c.right, mid + 1, r, s, e, v);
            }
        }

        public int query(int s, int e) {
            return query(root, 1, size, s, e);
        }

        private int query(Node c, int l, int r, int s, int e) {
            if (s <= l && r <= e) {
                // 全包
                return c.max;
            }

            int mid = (l + r) >> 1;
            pushDown(c, mid - l + 1, r - l);

            int p1 = 0, p2 = 0;
            if (s <= mid) {
                p1 = query(c.left, l, mid, s, e);
            }
            if (e > mid) {
                p2 += query(c.right, mid + 1, r, s, e);
            }
            return Math.max(p1, p2);
        }

        public boolean book(int start, int end) {
            int query = query(start + 1, end);
            if (query >= 2) {
                return false;
            }
            update(start + 1, end, 1);
            return true;
        }
    }

    public static void main(String[] args) {
        MyCalendarTwo myCalendar = new MyCalendarTwo();
        System.out.println(myCalendar.book(10, 20));
        System.out.println(myCalendar.book(50, 60));
        System.out.println(myCalendar.book(10, 40));
        System.out.println(myCalendar.book(5, 15));
        System.out.println(myCalendar.book(5, 10));
        System.out.println(myCalendar.book(25, 55));
    }
}
