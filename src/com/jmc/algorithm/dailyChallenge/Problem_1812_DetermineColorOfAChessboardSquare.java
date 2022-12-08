package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/determine-color-of-a-chessboard-square
 */
public class Problem_1812_DetermineColorOfAChessboardSquare {
    static boolean[][] squares = new boolean[][]{
            {false, true, false, true, false, true, false, true},
            {true, false, true, false, true, false, true, false},
            {false, true, false, true, false, true, false, true},
            {true, false, true, false, true, false, true, false},
            {false, true, false, true, false, true, false, true},
            {true, false, true, false, true, false, true, false},
            {false, true, false, true, false, true, false, true},
            {true, false, true, false, true, false, true, false}
    };

    public static boolean squareIsWhite(String coordinates) {

        char[] coords = coordinates.toCharArray();
        return squares[coords[0] - 'a'][coords[1] - '1'];
    }

    public static boolean squareIsWhite2(String coordinates) {
        char[] coords = coordinates.toCharArray();
        return (coords[0] + coords[1]) % 2 == 1;
    }

    public static void main(String[] args) {
        String coordinates = "c7";
        System.out.println(squareIsWhite(coordinates));
        System.out.println(squareIsWhite2(coordinates));
    }
}
