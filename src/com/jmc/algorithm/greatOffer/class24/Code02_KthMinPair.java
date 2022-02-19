package com.jmc.algorithm.greatOffer.class24;

import java.util.Arrays;

/**
 * 长度为N数组的arr，一定可以组成N^2数字对
 * 例如arr=[3,1,2]，数字对(3,3) (3,1) (3,2) (1,3) (1,1) (1,2) (2,3) (2,1) (2,2)
 * 任意两个数都可以，而且自己和自己也算数字对，数字对怎么排序？
 * 第一维数据从小到大，第一维数据一样的，第二维数据从小到大
 * 所以上面的数字对排序结果：(1,1) (1,2) (1,3) (2,1) (2,2) (2,3) (3,1) (3,2) (3,3)
 * 给定一个数组arr和正数k，返回第k小的数值对
 *
 * @author jmc
 * @version 1.0
 * @date 2021/7/22 11:39
 */
public class Code02_KthMinPair {

    public static int[] findKthMinPair(int[] arr, int k) {
        int N = arr.length;
        if (arr == null || N == 0 || N * N < k) {
            return null;
        }

        int firstNum = findKth(arr, (k - 1) / N);
        // 小于第一维数字的个数
        int lessFirstNumCount = 0;
        // 等于第一维数字的个数
        int equalFirstNumCount = 0;
        for (int i = 0; i < N; i++) {
            if (arr[i] < firstNum) {
                lessFirstNumCount++;
            } else if (arr[i] == firstNum) {
                equalFirstNumCount++;
            }
        }

        // arr长度为10，去掉第一维组合的个数，
        // 比如第82小，firstNum=4，比firstNum小的有7个，rest=82-7*10=12
        int rest = k - lessFirstNumCount * N;
        int secondNum = findKth(arr, (rest - 1) / equalFirstNumCount);
        return new int[]{firstNum, secondNum};
    }

    public static int findKth(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return Integer.MAX_VALUE;
        }

        int N = arr.length;
        int ans = process(arr, 0, N - 1, k);

        return ans;
    }

    public static int findKth2(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return Integer.MAX_VALUE;
        }

        int N = arr.length;
        int L = 0;
        int R = N - 1;
        while (L < R) {
            int pivot = arr[L + (int) (Math.random() * (R - L + 1))];
            int[] range = netherlands2(arr, L, R, pivot);
            if (range[0] <= k && k <= range[1]) {
                return pivot;
            } else if (k < range[0]) {
                R = range[0] - 1;
            } else {
                L = range[1] + 1;
            }
        }
        return arr[L];
    }

    private static int[] netherlands2(int[] arr, int L, int R, int pivot) {
        int less = L - 1;
        int more = R + 1;
        int cur = L;
        while (cur != more) {
            if (arr[cur] < pivot) {
                swap(arr, ++less, cur++);
            } else if (arr[cur] > pivot) {
                swap(arr, --more, cur);
            } else {
                cur++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    private static int process(int[] arr, int L, int R, int k) {
        if (L == R) {
            return arr[L];
        }
        int pivot = arr[L + (int) (Math.random() * (R - L + 1))];
        int[] pivotScope = netherlands(arr, L, R, pivot);
        if (k >= pivotScope[0] && k <= pivotScope[1]) {
            return pivot;
        } else if (k < pivotScope[0]) {
            return process(arr, L, pivotScope[0] - 1, k);
        } else {
            return process(arr, pivotScope[1] + 1, R, k);
        }
    }

    private static int[] netherlands(int[] arr, int L, int R, int pivot) {
        int less = L - 1;
        int more = R + 1;
        int cur = L;
        while (cur != more) {
            if (arr[cur] < pivot) {
                swap(arr, ++less, cur++);
            } else if (arr[cur] > pivot) {
                swap(arr, cur, --more);
            } else {
                cur++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{7, 3, 4, 4, 4, 8, 9};
        int[] arr2 = new int[]{7, 3, 4, 4, 4, 8, 9};
        int[] arr3 = new int[]{7, 3, 4, 4, 4, 8, 9};
        int k = 6;
        System.out.println(findKth(arr1, k - 1));
        System.out.println(findKth2(arr2, k - 1));
        System.out.println(findKth3(arr3, k - 1));

        int[] arr = new int[]{1, 1, 1, 2, 2, 3, 3, 3, 3, 5, 5, 6, 7, 7, 6, 9, 9, 23, 12, 10};
        int index = 382;
        System.out.println(Arrays.toString(findKthMinPair(arr, index)));
        System.out.println(Arrays.toString(findKthMinPair2(arr, index)));

    }

    public static int[] findKthMinPair2(int[] arr, int k) {
        int N = arr.length;
        if (arr == null || N * N < k) {
            return null;
        }

        int firstNum = findKth3(arr, (k - 1) / N);
        int lessFirstNumSize = 0;
        int equalFirstNumSize = 0;
        for (int i = 0; i < N; i++) {
            if (arr[i] == firstNum) {
                equalFirstNumSize++;
            } else if (arr[i] < firstNum) {
                lessFirstNumSize++;
            }
        }
        int rest = k - lessFirstNumSize * N;
        int secondNum = findKth3(arr, (rest - 1) / equalFirstNumSize);
        return new int[]{firstNum, secondNum};
    }

    public static int findKth3(int[] arr, int k) {
        if (arr == null || arr.length < k) {
            return Integer.MAX_VALUE;
        }

        int N = arr.length;
        int L = 0;
        int R = N - 1;
        while (L < R) {
            int pivot = arr[L + (int) (Math.random() * (R - L + 1))];
            int[] range = netherlands3(arr, L, R, pivot);
            if (range[0] > k) {
                R = range[0] - 1;
            } else if (range[1] < k) {
                L = range[1] + 1;
            } else {
                return pivot;
            }
        }
        return arr[L];
    }

    private static int[] netherlands3(int[] arr, int L, int R, int pivot) {
        int less = L - 1;
        int more = R + 1;
        int cur = L;
        while (cur != more) {
            if (arr[cur] < pivot) {
                swap(arr, ++less, cur++);
            } else if (arr[cur] > pivot) {
                swap(arr, --more, cur);
            } else {
                cur++;
            }
        }
        return new int[]{less + 1, more - 1};
    }
}
