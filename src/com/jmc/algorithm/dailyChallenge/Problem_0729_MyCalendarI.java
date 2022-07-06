package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/my-calendar-i/
 */
public class Problem_0729_MyCalendarI {
    static class MyCalendar {
        private int N = (int) 1e9 + 7;
        private int size;
        private Node root;

        private static class Node {
            private int sum;
            private int lazy;
            private int update;
            private boolean change;
            private Node left;
            private Node right;
        }

        public MyCalendar() {
            this.size = N;
            root = new Node();
        }

        private void pushUp(Node c) {
            c.sum = c.left.sum + c.right.sum;
        }

        private void pushDown(Node p, int ln, int rn) {
            if (p.left == null) {
                p.left = new Node();
            }
            if (p.right == null) {
                p.right = new Node();
            }

            if (p.change) {
                p.left.change = true;
                p.right.change = true;
                p.left.lazy = 0;
                p.right.lazy = 0;
                p.left.update = p.update;
                p.right.update = p.update;
                p.left.sum = p.update * ln;
                p.right.sum = p.update * rn;
                p.change = false;
            }

            if (p.lazy != 0) {
                p.left.lazy += p.lazy;
                p.right.lazy += p.lazy;
                p.left.sum += p.lazy * ln;
                p.right.sum += p.lazy * rn;
                p.lazy = 0;
            }
        }

        public void update(int s, int e, int v) {
            update(root, 1, size, s, e, v);
        }

        private void update(Node c, int l, int r, int s, int e, int v) {
            if (l >= s && e >= r) {
                // 全包
                c.sum = v * (r - l + 1);
                c.lazy = 0;
                c.update = v;
                c.change = true;
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

        public void add(int s, int e, int v) {
            add(root, 1, size, s, e, v);
        }

        private void add(Node c, int l, int r, int s, int e, int v) {
            if (s <= l && r <= e) {
                // 全包
                c.lazy += v;
                c.sum += v * (r - l + 1);
                return;
            }

            int mid = (l + r) >> 1;
            pushDown(c, mid - l + 1, r - mid);

            if (mid >= s) {
                add(c.left, l, mid, s, e, v);
            }

            if (e > mid) {
                add(c.right, mid + 1, r, s, e, v);
            }
            pushUp(c);
        }

        public int query(int s, int e) {
            return query(root, 1, size, s, e);
        }

        private int query(Node c, int l, int r, int s, int e) {
            if (s <= l && r <= e) {
                // 全包
                return c.sum;
            }
            int mid = (l + r) >> 1;
            pushDown(c, mid - l + 1, r - mid);

            int ans = 0;
            if (s <= mid) {
                ans += query(c.left, l, mid, s, e);
            }

            if (e > mid) {
                ans += query(c.right, mid + 1, r, s, e);
            }
            return ans;
        }

        public boolean book(int start, int end) {
            int query = query(start + 1, end);
            if (query > 0) {
                return false;
            }
            add(start + 1, end, 1);
            return true;
        }
    }

    public static void main(String[] args) {
        // ["MyCalendar","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book"]
        //[[],[69,70],[3,4],[39,40],[35,36],[3,4],[55,56],[61,62],[97,98],[79,80],[76,77],[46,47],[78,79],[47,48],[38,39],[83,84],[90,91],[90,91],[49,50],[49,50],[77,78],[23,24],[89,90],[8,9],[3,4],[2,3],[48,49],[96,97],[4,5],[54,55],[30,31],[97,98],[65,66],[93,94],[49,50],[24,25],[17,18],[53,54],[45,46],[53,54],[32,33],[37,38],[5,6],[50,51],[48,49],[14,15],[91,92],[79,80],[73,74],[28,29],[31,32],[98,99],[37,38],[19,20],[49,50],[54,55],[37,38],[98,99],[12,13],[24,25],[46,47],[74,75],[87,88],[64,65],[61,62],[68,69],[28,29],[43,44],[89,90],[64,65],[72,73],[69,70],[88,89],[68,69],[28,29],[20,21],[64,65],[17,18],[40,41],[88,89],[22,23],[8,9],[33,34],[13,14],[19,20],[53,54],[99,100],[24,25],[82,83],[77,78],[90,91],[72,73],[33,34],[73,74],[0,1],[25,26],[69,70],[73,74],[12,13],[33,34],[47,48],[26,27],[77,78],[95,96],[28,29],[77,78],[28,29],[87,88],[16,17],[42,43],[51,52],[44,45],[63,64],[24,25],[18,19],[0,1],[45,46],[65,66],[21,22],[37,38],[77,78],[97,98],[24,25],[83,84],[20,21],[29,30],[66,67],[29,30],[37,38],[63,64],[15,16],[85,86],[61,62],[0,1],[23,24],[96,97],[91,92],[90,91],[80,81],[18,19],[69,70],[3,4],[59,60],[21,22],[75,76],[54,55],[65,66],[34,35],[19,20],[79,80],[6,7],[24,25],[29,30],[35,36],[9,10],[0,1],[73,74],[65,66],[78,79],[32,33],[58,59],[25,26],[3,4],[78,79],[92,93],[37,38],[91,92],[5,6],[79,80],[94,95],[78,79],[38,39],[16,17],[81,82],[34,35],[16,17],[33,34],[42,43],[34,35],[89,90],[88,89],[33,34],[68,69],[92,93],[73,74],[64,65],[91,92],[44,45],[13,14],[97,98],[64,65],[31,32],[91,92],[1,2],[57,58],[21,22],[38,39],[70,71],[84,85],[50,51],[58,59]]
        MyCalendar myCalendar = new MyCalendar();
        System.out.println(myCalendar.book(10, 20));
        System.out.println(myCalendar.book(15, 25));
        System.out.println(myCalendar.book(20, 30));
    }
}
