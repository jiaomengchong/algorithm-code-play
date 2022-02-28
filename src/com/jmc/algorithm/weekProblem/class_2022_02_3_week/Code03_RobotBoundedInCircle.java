package com.jmc.algorithm.weekProblem.class_2022_02_3_week;

/**
 * 测试链接：https://leetcode.com/problems/robot-bounded-in-circle/
 */
public class Code03_RobotBoundedInCircle {
    public static class Info {
        private int direct;
        private int row;
        private int col;

        public Info() {
            this.direct = 0;
            this.row = 0;
            this.col = 0;
        }
    }

    public static boolean isRobotBounded(String instructions) {
        if (instructions == null || instructions.length() == 0) {
            return true;
        }

        // 0 1 2 3表示四个方向
        Info info = new Info();
        char[] chars = instructions.toCharArray();
        for (char ch : chars) {
            if (ch == 'R') {
                right(info);
            } else if (ch == 'L') {
                left(info);
            } else {
                row(info);
                col(info);
            }
        }

        return (info.row == 0 && info.col == 0) || info.direct != 0 ? true : false;
    }

    private static void col(Info info) {
        info.col = info.direct == 0 || info.direct == 2 ? info.col : info.direct > 1 ? info.col - 1 : info.col + 1;
    }

    private static void row(Info info) {
        info.row = info.direct == 1 || info.direct == 3 ? info.row : info.direct > 0 ? info.row - 1 : info.row + 1;
    }

    private static void right(Info info) {
        info.direct = info.direct == 3 ? 0 : info.direct + 1;
    }

    private static void left(Info info) {
        info.direct = info.direct == 0 ? 3 : info.direct - 1;
    }

    public static void main(String[] args) {
        String instruction = "GGLLGG";
        System.out.println(isRobotBounded(instruction));
    }
}
