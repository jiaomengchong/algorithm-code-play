package com.jmc.algorithm.greatOffer.class09;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 俄罗斯信封套娃问题
 * 测试链接：https://leetcode-cn.com/problems/russian-doll-envelopes/
 *
 * @author jmc
 * @version 1.0
 * @date 2021/6/5 16:23
 */
public class Code04_EnvelopesProblem {
    public static class Envelops {
        private int width;
        private int height;

        public Envelops(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }

    public static class EnvelopsComparator implements Comparator<Envelops> {

        @Override
        public int compare(Envelops e1, Envelops e2) {
            return e1.width == e2.width ? (e2.height - e1.height) : (e1.width - e2.width);
        }
    }

    public static Envelops[] sorts(int[][] envelops) {
        Envelops[] envelopsArray = new Envelops[envelops.length];
        for (int i = 0; i < envelops.length; i++) {
            envelopsArray[i] = new Envelops(envelops[i][0], envelops[i][1]);
        }
        Arrays.sort(envelopsArray, new EnvelopsComparator());
        return envelopsArray;
    }

    public static int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes[0].length == 0) {
            return 0;
        }

        int N = envelopes.length;
        int ans = 1;
        int l = 0;
        int r = 0;
        int m = 0;
        int right = 0;
        int[] ends = new int[N];
        Envelops[] envelops = sorts(envelopes);
        ends[0] = envelops[0].height;
        for (int i = 1; i < N; i++) {
            l = 0;
            r = right;
            while (l <= r) {
                m = l + ((r - l) >> 1);
                if (ends[m] < envelops[i].height) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            right = Math.max(l, right);
            ends[l] = envelops[i].height;
            ans = Math.max(ans, l + 1);
        }

        return ans;
    }

    public static void main(String[] args) {
        int[][] envelopes = {{5, 4}, {6, 4}, {6, 7}, {2, 3}};
//        int[][] envelopes = {{1, 1}, {1, 1}, {1, 1}, {1, 1}};
        System.out.println(maxEnvelopes(envelopes));

        System.out.println(122 + 1);
        System.out.println(122 | 1);
    }
}
