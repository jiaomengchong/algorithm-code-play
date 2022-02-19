package com.jmc.algorithm.jjb.class32;

/**
 * @author jmc
 * @version 1.0
 * @date 2021/3/3 13:53
 */
public class Code01_IndexTree {
    public static class IndexTree {
        private int N;
        private int[] tree;

        public IndexTree(int size) {
            N = size + 1;
            tree = new int[N];
        }

        public void add(int index, int d) {
            while (index < N) {
                tree[index] += d;
                index += index & (-index);
            }
        }

        public int sum(int index) {
            int sum = 0;
            while (index > 0) {
                sum += tree[index];
                index -= index & (-index);
            }
            return sum;
        }
    }

    public static class Right {
        private int N;
        private int[] nums;

        public Right(int size) {
            N = size + 1;
            nums = new int[N];
        }

        public void add(int index, int d) {
            nums[index] += d;
        }

        public int sum(int index) {
            int ans = 0;
            for (int i = 1; i <= index; i++) {
                ans += nums[i];
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4, 3, 7, 1};
        int size = arr.length;
        IndexTree indexTree = new IndexTree(size);
        Right right = new Right(size);
        for (int i = 0; i < size; i++) {
            int index = (int) (Math.random() * size) + 1;
            indexTree.add(index, arr[i]);
            right.add(index, arr[i]);
        }
        System.out.println(indexTree.sum(4));
        System.out.println(right.sum(4));
    }
}
