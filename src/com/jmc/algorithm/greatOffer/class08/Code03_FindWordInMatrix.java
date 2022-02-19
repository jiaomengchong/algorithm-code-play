package com.jmc.algorithm.greatOffer.class08;

/**
 * 给定一个char[][] matrix，也就是char类型的二维数组，再给定一个字符串word，
 * 可以从任何一个某个位置出发，可以走上下左右，能不能找到word？
 * char[][] m = {  { 'a', 'b', 'z' }, 
 * { 'c', 'd', 'o' }, 
 * { 'f', 'e', 'o' }, 
 * 设定1：可以走重复路的情况下，返回能不能找到
 * 比如，word = "zoooz"，是可以找到的，z -> o -> o -> o -> z，因为允许走一条路径中已经走过的字符
 * 设定2：不可以走重复路的情况下，返回能不能找到
 * 比如，word = "zoooz"，是不可以找到的，因为允许走一条路径中已经走过的字符不能重复走 
 *
 * @author jmc
 * @version 1.0
 * @date 2021/5/24 14:28
 */
public class Code03_FindWordInMatrix {
    public static boolean findWord1(char[][] m, String word) {
        if (word == null || word.length() == 0) {
            return true;
        }

        int N = m.length;
        int M = m[0].length;
        if (m == null || m.length == 0 || m[0].length == 0) {
            return false;
        }

        boolean ans;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ans = canLoop(m, word.toCharArray(), i, j, 0);
                if (ans) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean canLoop(char[][] m, char[] word, int i, int j, int k) {
        if (k == word.length) {
            return true;
        }
        if (i < 0 || i >= m.length || j < 0 || j >= m[0].length || m[i][j] != word[k]) {
            return false;
        }
        boolean isFind = canLoop(m, word, i - 1, j, k + 1) ||
                canLoop(m, word, i + 1, j, k + 1) ||
                canLoop(m, word, i, j - 1, k + 1) ||
                canLoop(m, word, i, j + 1, k + 1);
        if (isFind) {
            return true;
        }
        return false;
    }

    public static boolean findWord2(char[][] m, String word) {
        if (word == null || word.length() == 0) {
            return true;
        }

        if (m == null || m.length == 0 || m[0].length == 0) {
            return false;
        }

        int N = m.length;
        int M = m[0].length;
        boolean ans;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ans = noLoop(m, word.toCharArray(), i, j, 0);
                if (ans) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean noLoop(char[][] m, char[] word, int i, int j, int k) {
        if (k == word.length) {
            return true;
        }
        if (i < 0 || i >= m.length || j < 0 || j >= m[0].length || m[i][j] != word[k]) {
            return false;
        }
        char temp = m[i][j];
        m[i][j] = 0;
        if (noLoop(m, word, i - 1, j, k + 1)
                || noLoop(m, word, i + 1, j, k + 1)
                || noLoop(m, word, i, j - 1, k + 1)
                || noLoop(m, word, i, j + 1, k + 1)
        ) {
            return true;
        }
        m[i][j] = temp;
        return false;
    }

    public static void main(String[] args) {
        char[][] matrix = new char[][]{{'a', 'b', 'z'}, {'c', 'd', 'o'}, {'f', 'e', 'o'}};
        String word1 = "zoooz";
        String word2 = "zoo";
        System.out.println(findWord1(matrix, word1));
        System.out.println(findWord1(matrix, word2));

        System.out.println(findWord2(matrix, word1));
        System.out.println(findWord2(matrix, word2));
    }
}