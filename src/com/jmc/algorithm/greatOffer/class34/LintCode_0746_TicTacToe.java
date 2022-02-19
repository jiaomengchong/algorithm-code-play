package com.jmc.algorithm.greatOffer.class34;

import java.util.Arrays;

/**
 * 设计井字游戏
 * 测试链接：https://www.lintcode.com/problem/746
 *
 * @Author jmc
 * @Description
 * @Date 2021/9/2 18:10
 **/
public class LintCode_0746_TicTacToe {
    public static class TicTacToe {
        private int[] rows;
        private int[] cols;
        private int leftUp;
        private int rightUp;
        private boolean currentIsX;
        private boolean gameEnd;
        private int total;
        private char[][] board;

        public TicTacToe() {
            rows = new int[3];
            cols = new int[3];
            leftUp = 0;
            rightUp = 0;
            currentIsX = true;
            gameEnd = false;
            total = 0;
            board = new char[3][3];
            for (char[] chars : board) {
                Arrays.fill(chars, '-');
            }
        }

        public boolean move(int row, int col) throws AlreadyTakenException, GameEndException {
            if (gameEnd) {
                throw new GameEndException();
            }

            if (board[row][col] != '-') {
                throw new AlreadyTakenException();
            }

            int add = currentIsX ? 1 : -1;
            char chessman = currentIsX ? 'X' : 'O';
            currentIsX = !currentIsX;
            rows[row] += add;
            cols[col] += add;
            if (row == col) {
                leftUp += add;
            }
            if (row + col == 2) {
                rightUp += add;
            }
            board[row][col] = chessman;
            total++;
            if (rows[row] == 3 || cols[col] == 3 || leftUp == 3 || rightUp == 3) {
                gameEnd = true;
                System.out.println(chessman + " player wins!");
                return true;
            }
            if (rows[row] == -3 || cols[col] == -3 || leftUp == -3 || rightUp == -3) {
                gameEnd = true;
                System.out.println(chessman + " player wins!");
                return true;
            }
            if (total == 9) {
                System.out.println("it's a draw");
            }

            return false;
        }
    }

    public static class AlreadyTakenException extends Exception {
        public AlreadyTakenException() {
            super("throw AlreadyTakenException");
        }
    }

    public static class GameEndException extends Exception {
        public GameEndException() {
            super("throw GameEndException");
        }
    }

    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe();
        try {
            ticTacToe.move(0, 0); // X 的回合
            ticTacToe.move(1, 0); // O 的回合
            ticTacToe.move(1, 1); // X 的回合
            ticTacToe.move(2, 0); // O 的回合
            ticTacToe.move(2, 2); // X 的回合并获得胜利
            ticTacToe.move(0, 0); //抛出 GameEndException
            ticTacToe.move(0, 0); // X 的回合
            ticTacToe.move(0, 0); // 抛出 AlreadyTakenException
            ticTacToe.move(1, 0); // O 的回合
            ticTacToe.move(1, 1); // X 的回合
            ticTacToe.move(2, 0); // o 的回合
            ticTacToe.move(2, 2); // X 的回合并获得胜利
        } catch (AlreadyTakenException e) {
            e.printStackTrace();
        } catch (GameEndException e) {
            e.printStackTrace();
        }
    }
}
