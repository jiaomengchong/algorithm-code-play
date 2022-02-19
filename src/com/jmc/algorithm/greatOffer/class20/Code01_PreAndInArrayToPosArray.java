package com.jmc.algorithm.greatOffer.class20;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 如果只给定一个二叉树前序遍历数组pre和中序遍历数组in，
 * 能否不重建树，而直接生成这个二叉树的后序数组并返回
 * 已知二叉树中没有重复值
 *
 * @author jmc
 * @version 1.0
 * @date 2021/7/5 11:01
 */
public class Code01_PreAndInArrayToPosArray {
    public static int[] ways1(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length != in.length) {
            return null;
        }

        int N = pre.length;
        HashMap<Integer, Integer> inMap = new HashMap<>();
        int[] pos = new int[N];
        for (int i = 0; i < N; i++) {
            inMap.put(in[i], i);
        }
        process_1(pre, 0, N - 1, in, 0, N - 1, pos, 0, N - 1, inMap);
        return pos;
    }

    private static void process_1(int[] pre, int L1, int R1, int[] in, int L2, int R2, int[] pos, int L3, int R3, HashMap<Integer, Integer> inMap) {
        if (L1 > R1) {
            return;
        }
        if (L1 == R1) {
            pos[L3] = pre[L1];
        } else {
            int index = 0;
            pos[R3] = pre[L1];
            for (int i = 0; i < in.length; i++) {
                if (pre[L1] == in[i]) {
                    index = i;
                    break;
                }
            }
            process_1(pre, L1 + 1, L1 + index - L2, in, L2, index - 1, pos, L3, L3 + index - L2 - 1, inMap);
            process_1(pre, L1 + index - L2 + 1, R1, in, index + 1, R2, pos, L3 + index - L2, R3 - 1, inMap);
        }
    }

    public static void main(String[] args) {
        int[] pre = new int[]{4, 5, 1};
        int[] in = new int[]{5, 4, 1};
        System.out.println(Arrays.toString(ways1(pre, in)));
    }
}
