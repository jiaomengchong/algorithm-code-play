package com.jmc.algorithm.newer.class01;

/**
 * @author jmc
 * @version 1.0
 * @date 2020/12/10 9:04
 */
public class PrintBinary {
    public static void print(int num) {
        for (int i = 31; i >= 0; i--) {
            System.out.print((num & (1 << i)) == 0 ? "0" : "1");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        /*int a = 1223333;
        int b = ~a + 1;

        System.out.println(a);
        System.out.println(b);

        print(a);
        print(b);
        print(-5);

        System.out.println("================================");
        int c = Integer.MIN_VALUE;
        print(c);
        print(c >>> 1);
        print(c >> 1);

        int x = 5;
        int y = ~x + 1;
        print(x);
        print(y);*/

        System.out.println(1 << 4);
    }
}
