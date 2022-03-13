package com.jmc.algorithm.weekProblem.class_2022_02_4_week;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 返回一个数组中，所有降序三元组的数量
 * 比如 : {5, 3, 4, 2, 1}
 * 所有降序三元组为 :
 * {5, 3, 2}、{5, 3, 1}、{5, 4, 2}、{5, 4, 1}
 * {5, 2, 1}、{3, 2, 1}、{4, 2, 1}
 * 所以返回数量7
 *
 * @Author jmc
 * @Description
 * @Date 2022/3/8 18:50
 **/
public class Code04_NumberOfDescendingTriples {
    public static int ways1(int[] arr) {
        int ans = 0;
        int N = arr.length;
//        discreteByHashMap(arr);
        discreteByBinarySearch(arr);
        IndexTreeStruct indexTree2 = new IndexTreeStruct(N);
        IndexTreeStruct indexTree3 = new IndexTreeStruct(N);
        for (int i = N - 1; i >= 0; i--) {
           ans += arr[i] == 1 ? 0 : indexTree3.sum(arr[i] - 1);
           indexTree2.add(arr[i], 1);
           indexTree3.add(arr[i], indexTree2.sum(arr[i] - 1));
        }
        return ans;
    }

    private static void discreteByBinarySearch(int[] arr) {
        int[] sorted = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sorted);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rank(arr[i], sorted);
        }
    }

    private static int rank(int num, int[] sorted) {
        int ans = 0;
        int L = 0;
        int R = sorted.length - 1;
        int mid;
        while (L <= R) {
            mid = (L + R) / 2;
            if (sorted[mid] >= num) {
                ans = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return ans + 1;
    }

    private static void discreteByHashMap(int[] arr) {
        int[] sorted = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sorted);
        Map<Integer, Integer> discreteMap = new HashMap<>();
        for (int i = 0; i < sorted.length; i++) {
            discreteMap.put(sorted[i], i + 1);
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = discreteMap.get(arr[i]);
        }
    }

    public static class IndexTreeStruct {
        private int[] tree;
        private int N;

        public IndexTreeStruct(int n) {
            this.N = n;
            tree = new int[N + 1];
        }

        public int sum(int index) {
            int ret = 0;
            // 12的前缀和:1100 -> 1000 + 1100 -> 表示8的前缀和+12的前缀和
            // 8的前缀和:1-8  [抹去最右侧的1，再加1],即0001 - 1000
            // 12的前缀和:9-12[抹去最右侧的1，再加1],即1001 - 1100
            while (index > 0) {
                ret += tree[index];
                index -= index & -index;
            }
            return ret;
        }

        public void add(int index, int value) {
            //  1010 -> 10 变了影响哪些位置
            //  1010 -> 取最右侧的1 -> 0010
            //+ 0010
            //  1100 -> 12
            while (index <= N) {
                tree[index] += value;
                index += index & -index;
            }
        }
    }

    public static void main(String[] args) {
        // 测试IndexTree功能
        int n = 17;
        //  10110
        //& 01010
        //  00010
        IndexTreeStruct indexTree = new IndexTreeStruct(n);
        indexTree.add(8, 2);
        indexTree.add(9, 1);
        int sum = indexTree.sum(17);
        System.out.println(sum);

        int[] arr = new int[]{5, 3, 4, 2, 1};
        System.out.println(ways1(arr));
    }
}
