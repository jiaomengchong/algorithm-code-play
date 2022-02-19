package com.jmc.algorithm.greatOffer.class13;

/**
 * @author jmc
 * @version 1.0
 * @date 2021/6/8 10:03
 */
public class Code01_NCardsABWin {
    // 谷歌面试题
    // 面值为1~10的牌组成一组，
    // 每次你从组里等概率的抽出1~10中的一张
    // 下次抽会换一个新的组，有无限组
    // 当累加和<17时，你将一直抽牌
    // 当累加和>=17且<21时，你将获胜
    // 当累加和>=21时，你将失败
    // 返回获胜的概率

    public static double f1() {
        return p1(0);
    }

    private static double p1(int cur) {
        if (cur >= 17 && cur < 21) {
            return 1.0;
        }
        if (cur >= 21) {
            return 0.0;
        }
        // cur < 17，一直抽牌
        double w = 0;
        for (int i = 1; i <= 10; i++) {
            w += p1(cur + i);
        }

        return w / 10;
    }

    // 谷歌面试题扩展版
    // 面值为1~N的牌组成一组，
    // 每次你从组里等概率的抽出1~N中的一张
    // 下次抽会换一个新的组，有无限组
    // 当累加和<a时，你将一直抽牌
    // 当累加和>=a且<b时，你将获胜
    // 当累加和>=b时，你将失败
    // 返回获胜的概率，给定的参数为N，a，b

    public static double f2(int n, int a, int b) {
        if (n < 1 || a < 1 || b < 1 || a >= b) {
            return 0.0;
        }

        if (b - a >= n) {
            return 1.0;
        }

        return p2(n, a, b, 0);
    }

    private static double p2(int n, int a, int b, int cur) {
        if (cur >= a && cur < b) {
            return 1.0;
        }
        if (cur >= b) {
            return 0.0;
        }

        double w = 0.0;
        for (int i = 1; i <= n; i++) {
            w += p2(n, a, b, cur + i);
        }

        return w / n;
    }

    public static double f3(int n, int a, int b) {
        if (n < 1 || a <= 0 || b <= 0 || a >= b) {
            return 0.0;
        }
        if (b - a >= n) {
            return 1.0;
        }

        return p3(n, a, b, 0);
    }

    private static double p3(int n, int a, int b, int cur) {
        if (cur >= b) {
            return 0.0;
        }
        if (cur >= a && cur < b) {
            return 1.0;
        }
        // a的前一个位置
        if (cur == a - 1) {
            return (double) (b - a) / n;
        }
        // 通用公式 f(i) = [f(i+1)+f(i+1)*n]/n
        double w = p3(n, a, b, cur + 1) + n * p3(n, a, b, cur + 1);
        // 判断要不要减掉小尾巴
        if (cur + n + 1 < b) {
            w -= p3(n, a, b, cur + n + 1);
        }

        return w / n;
    }

    public static double f4(int n, int a, int b) {
        if (n < 1 || a <= 0 || b <= 0 || a >= b) {
            return 0.0;
        }

        return p4(n, a, b);
    }

    private static double p4(int n, int a, int b) {
        if (b - a >= n) {
            return 1.0;
        }
        double[] dp = new double[b + 1];
        for (int i = a; i < b; i++) {
            dp[i] = 1.0;
        }
        dp[a - 1] = (double) (b - a) / n;
        for (int i = a - 2; i >= 0; i--) {
            dp[i] = dp[i + 1] + n * dp[i + 1];
            if (i + n + 1 < b) {
                dp[i] -= dp[i + n + 1];
            }
            dp[i] /= n;
        }

        return dp[0];
    }

    public static void main(String[] args) {
        System.out.println(f1());
        System.out.println(f2(10, 17, 21));
        System.out.println(f3(10, 17, 21));
        System.out.println(f4(10, 17, 21));

        int N, a, b;
        N = 10000;
        a = 67834;
        b = 72315;
        System.out.println("N = " + N + ", a = " + a + ", b = " + b + "时, 除了方法4外都超时");
        System.out.print("方法4答案: ");
        System.out.println(f4(N, a, b));
    }
}
