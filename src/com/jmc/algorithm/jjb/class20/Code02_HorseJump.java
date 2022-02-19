package com.jmc.algorithm.jjb.class20;

/**
 * 请同学们自行搜索或者想象一个象棋的棋盘，
 * 然后把整个棋盘放入第一象限，棋盘的最左下角是(0,0)位置
 * 那么整个棋盘就是横坐标上9条线、纵坐标上10条线的区域
 * 给你三个 参数 x，y，k
 * 返回“马”从(0,0)位置出发，必须走k步
 * 最后落在(x,y)上的方法数有多少种?
 *
 * @author jmc
 * @version 1.0
 * @date 2021/1/11 16:36
 */
public class Code02_HorseJump {
    public static int jump(int x, int y, int k) {
        return process1(x, y, 0, 0, k);
    }

    private static int process1(int aimX, int aimY, int x, int y, int rest) {
        if (x < 0 || x > 8 || y < 0 || y > 9 || rest < 0) {
            return 0;
        }

        if (rest == 0) {
            return x == aimX && y == aimY ? 1 : 0;
        }

        return process1(x + 1, y + 2, aimX, aimY, rest - 1) +
                process1(x + 2, y + 1, aimX, aimY, rest - 1) +
                process1(x + 2, y - 1, aimX, aimY, rest - 1) +
                process1(x + 1, y - 2, aimX, aimY, rest - 1) +
                process1(x - 1, y - 2, aimX, aimY, rest - 1) +
                process1(x - 2, y - 1, aimX, aimY, rest - 1) +
                process1(x - 2, y + 1, aimX, aimY, rest - 1) +
                process1(x - 1, y + 2, aimX, aimY, rest - 1);
    }

    public static int dpWays(int X, int Y, int k) {
        if (X < 0 || X > 8 || Y < 0 || Y > 9 || k < 0) {
            return 0;
        }

        int[][][] dp = new int[10][9][k + 1];
        dp[X][Y][0] = 1;
        for (int rest = 1; rest <= k; rest++) {
            for (int x = 0; x < 10; x++) {
                for (int y = 0; y < 9; y++) {
                    dp[x][y][rest] = getValue(x + 1, y + 2, rest - 1, dp) +
                            getValue(x + 2, y + 1, rest - 1, dp) +
                            getValue(x + 2, y - 1, rest - 1, dp) +
                            getValue(x + 1, y - 2, rest - 1, dp) +
                            getValue(x - 1, y - 2, rest - 1, dp) +
                            getValue(x - 2, y - 1, rest - 1, dp) +
                            getValue(x - 2, y + 1, rest - 1, dp) +
                            getValue(x - 1, y + 2, rest - 1, dp);
                }
            }
        }

        return dp[0][0][k];
    }

    public static int getValue(int x, int y, int k, int[][][] dp) {
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            return 0;
        }

        return dp[x][y][k];
    }

    public static void main(String[] args) {
        int x = 7;
        int y = 7;
        int step = 10;

        System.out.println(jump(x, y, step));
        System.out.println(dpWays(x, y, step));
    }
}
