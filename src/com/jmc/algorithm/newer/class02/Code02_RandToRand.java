package com.jmc.algorithm.newer.class02;

/**
 * Java中的Math.random()函数
 *
 * @author jmc
 * @version 1.0
 * @date 2020/12/17 17:30
 */
public class Code02_RandToRand {

    /**
     * 假设lib函数，不能改
     *
     * @return
     */
    public static int f1() {
        return (int) (Math.random() * 5) + 1;
    }

    public static int f2() {
        int ans;
        do {
            ans = f1();
        } while (ans == 3);

        return ans < 3 ? 0 : 1;
    }

    public static int f3() {
        return (f2() << 2) + (f2() << 1) + (f2() << 0);
    }

    public static int g() {
        int ans;

        do {
            ans = f3();
        } while (ans == 0);

        return ans;
    }

    public static int x() {
        return Math.random() < 0.84 ? 0 : 1;
    }

    public static int y() {
        int ans;
        do {
            ans = x();
        } while (ans == x());

        return ans;
    }

    public static void main(String[] args) {
        int testTimes = 100_0000;
        int count = 0;
        for (int i = 0; i < testTimes; i++) {
            if (Math.random() < 0.5) {
                count++;
            }
        }
        System.out.println((double) count / (double) testTimes);
        System.out.println("==================================");

        count = 0;
        for (int i = 0; i < testTimes; i++) {
            if (Math.random() * 8 < 5) {
                count++;
            }
        }
        System.out.println((double) count / (double) testTimes);
        System.out.println("==================================");

        int K = 9;
        int[] counts = new int[K];
        for (int i = 0; i < testTimes; i++) {
            int ans = (int) (Math.random() * K);
            counts[ans]++;
        }
        for (int i = 0; i < K; i++) {
            System.out.println(i + "出现了 " + counts[i] + " 次");
        }
        System.out.println("==================================");

        count = 0;
        double x = 0.2;
        for (int i = 0; i < testTimes; i++) {
            if (Math.max(Math.random(), Math.random()) < x) {
                count++;
            }
        }
        System.out.println((double) count / (double) testTimes);
        System.out.println("==================================");

        count = 0;
        counts = new int[8];
        for (int i = 0; i < testTimes; i++) {
            int value = g();
            counts[value]++;
        }
        for (int i = 0; i <= 7; i++) {
            count += counts[i];
            System.out.println(i + "出现了 " + counts[i] + " 次");
        }
        System.out.println("总次数：" + count);
        System.out.println("==================================");

        count = 0;
        for (int i = 0; i < testTimes; i++) {
            if (y() == 0) {
                count++;
            }
        }
        System.out.println((double) count / (double) testTimes);
        System.out.println("==================================");

    }
}
