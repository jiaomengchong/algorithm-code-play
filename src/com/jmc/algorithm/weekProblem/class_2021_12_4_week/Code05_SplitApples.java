package com.jmc.algorithm.weekProblem.class_2021_12_4_week;

/**
 * 测试链接 : https://www.nowcoder.com/practice/bfd8234bb5e84be0b493656e390bdebf
 *
 * @Author jmc
 * @Description
 * @Date 2022/1/4 21:32
 **/
public class Code05_SplitApples {
    public static int ways1(int apples, int plates) {
        return process1(1, apples, plates);
    }

    private static int process1(int pre, int apples, int plates) {
        if (apples == 0) {
            return 1;
        }
        if (pre > apples) {
            // 1 1 3 可以
            // 1 2 2 可以
            // 1 4   可以
            // 2 3   可以
            // 5     可以
            // 1 3 1 不可以
            return 0;
        }
        if (plates == 0) {
            return 0;
        }
        int ans = 0;
        for (int i = pre; i <= apples; i++) {
            ans += process1(i, apples - i, plates - 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        int apple = 15;
        int plates = 3;
        System.out.println(ways1(apple, plates));
    }
}
