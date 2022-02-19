package com.jmc.algorithm.jjb.class29;

/**
 * 在无序数组中求第K小的数
 * 1）改写快排的方法
 * 2）bfprt算法
 *
 * @author jmc
 * @version 1.0
 * @date 2021/2/24 10:20
 */
public class Code01_FindMinKth {
    public static int minKth2_jmc(int[] array, int k) {
        if (array == null || k > array.length) {
            return -1;
        }

        return process_jmc(array, 0, array.length - 1, k - 1);
    }

    public static int minKth3_jmc(int[] array, int k) {
        if (array == null || k > array.length) {
            return -1;
        }

        return bfprt_jmc(array, 0, array.length - 1, k - 1);
    }

    private static int process_jmc(int[] array, int L, int R, int k) {
        if (L == R) {
            return array[L];
        }
        swap_jmc(array, L + (int) (Math.random() * (R - L + 1)), R);
        int[] pos = netherLandsFlag_jmc(array, L, R);
        if (k >= pos[0] && k <= pos[1]) {
            return array[pos[0]];
        } else if (k < pos[0]) {
            return process_jmc(array, L, pos[0] - 1, k);
        } else {
            return process_jmc(array, pos[1] + 1, R, k);
        }
    }

    private static int bfprt_jmc(int[] array, int L, int R, int k) {
        if (L == R) {
            return array[L];
        }

        // 获取pivot
        int pivot = medianOfMedians_jmc(array, L, R);
        int[] pos = netherLandsFlag(array, L, R, pivot);

        if (k >= pos[0] && k <= pos[1]) {
            return array[pos[0]];
        } else if (k < pos[0]) {
            return bfprt_jmc(array, L, pos[0] - 1, k);
        } else {
            return bfprt_jmc(array, pos[1] + 1, R, k);
        }
    }

    private static int[] netherLandsFlag(int[] array, int L, int R, int pivot) {
        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, L};
        }

        int leftLess = L - 1;
        int rightMore = R + 1;
        int cur = L;
        int[] ans = new int[2];
        while (cur < rightMore) {
            if (array[cur] == pivot) {
                cur++;
            } else if (array[cur] < pivot) {
                swap_jmc(array, ++leftLess, cur++);
            } else {
                swap_jmc(array, --rightMore, cur);
            }
        }
        ans[0] = leftLess + 1;
        ans[1] = rightMore - 1;
        return ans;
    }

    private static int medianOfMedians_jmc(int[] array, int L, int R) {
        int size = R - L + 1;
        int offset = size % 5 == 0 ? 0 : 1;
        int[] mArr = new int[size / 5 + offset];
        for (int team = 0; team < mArr.length; team++) {
            int teamFirst = L + team * 5;
            mArr[team] = getMedian_jmc(array, teamFirst, Math.min(teamFirst + 4, R));
        }

        return bfprt_jmc(mArr, 0, mArr.length - 1, mArr.length / 2);
    }

    private static int getMedian_jmc(int[] array, int L, int R) {
        insertSort_jmc(array, L, R);
        return array[(L + R) / 2];
    }

    private static void insertSort_jmc(int[] array, int L, int R) {
        for (int i = L + 1; i <= R; i++) {
            for (int j = i - 1; j >= L && array[j] > array[j + 1]; j--) {
                swap_jmc(array, j, j + 1);
            }
        }
    }

    private static int[] netherLandsFlag_jmc(int[] array, int L, int R) {
        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, L};
        }

        int[] ans = new int[2];
        int cur = L;
        int leftLess = L - 1;
        int rightMore = R;
        while (cur < rightMore) {
            if (array[cur] == array[R]) {
                cur++;
            } else if (array[cur] < array[R]) {
                swap_jmc(array, ++leftLess, cur++);
            } else {
                swap_jmc(array, cur, --rightMore);
            }
        }
        swap_jmc(array, rightMore, R);
        ans[0] = leftLess + 1;
        ans[1] = rightMore;

        return ans;
    }

    private static void swap_jmc(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr1 = {50, 3, 8, 47, 3, 28, 43, 20, 46, 53, 57, 59, 57, 55, 66, 72, 75, 84, 99, 79};
        int[] arr2 = {50, 3, 8, 47, 3, 28, 43, 20, 46, 53, 57, 59, 57, 55, 66, 72, 75, 84, 99, 79};
        int k = 16;
        System.out.println(minKth2_jmc(arr1, k));
        System.out.println(minKth3_jmc(arr2, k));

    }
}
