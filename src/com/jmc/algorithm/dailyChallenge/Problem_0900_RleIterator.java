package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

public class Problem_0900_RleIterator {
    //["RLEIterator","next","next","next","next"]
    //[[[3,8,0,9,2,5]],[2],[1],[1],[2]]
    //[null,8,8,5,-1]
    public static class RLEIterator {
        private int[] decoding;

        public RLEIterator(int[] encoding) {
            int N = encoding.length;
            int total = 0;
            for (int i = 0; i < N; i += 2) {
                total += encoding[i];
            }
            decoding = new int[total];
            int index = 0;
            int i = 0;
            while (i < N) {
                for (int j = 0; j < encoding[i]; j++) {
                    decoding[index++] = encoding[i + 1];
                }
                i += 2;
            }
        }

        public int next(int n) {
            int ans = 0;
            if (n >= decoding.length) {
                ans = -1;
            } else {
                ans = decoding[n - 1];
                decoding = Arrays.copyOfRange(decoding, n, decoding.length);
            }
            return ans;
        }
    }

    public static class RLEIterator1 {
        private int[] decoding;
        private int index;
        private int delIndex;

        public RLEIterator1(int[] encoding) {
            decoding = encoding;
            index = 0;
            delIndex = 0;
        }

        public int next(int n) {
            while (index < decoding.length) {
                if (delIndex + n <= decoding[index]) {
                    delIndex += n;
                    return decoding[index + 1];
                } else {
                    n -= decoding[index] - delIndex;
                    delIndex = 0;
                    index += 2;
                }
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        // [8,8,8,5,5]
        int[] encoding = new int[]{3, 8, 0, 9, 2, 5};
        RLEIterator1 iterator = new RLEIterator1(encoding);
        System.out.println(iterator.next(2));
        System.out.println(iterator.next(1));
        System.out.println(iterator.next(1));
        System.out.println(iterator.next(2));
    }
}