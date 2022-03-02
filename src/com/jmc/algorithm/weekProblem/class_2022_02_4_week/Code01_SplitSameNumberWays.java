package com.jmc.algorithm.weekProblem.class_2022_02_4_week;

/**
 * 来自微软
 * 比如，str = "ayxbx"
 * 有以下4种切法 : a | yxbx、ay | xbx、ayx | bx、ayxb | x
 * 其中第1、3、4种切法符合：x和y的个数，至少在左右两块中的一块里有相同的数量
 * 所以返回3
 * 给定一个字符串str，长度为N
 * 你有N-1种划分方法，把str切成左右两半，返回有几种切法满足：
 * x和y的个数，至少在左右两块中的一块里有相同的数量
 *
 * @Author jmc
 * @Description
 * @Date 2022/3/1 21:11
 **/
public class Code01_SplitSameNumberWays {
    public static int ways1(char[] str) {
        if (str == null || str.length == 0) {
            return 0;
        }

        int xTotal = 0;
        int yTotal = 0;
        for (char ch : str) {
            if (ch == 'x') {
                xTotal++;
            }
            if (ch == 'y') {
                yTotal++;
            }
        }

        int leftX = str[0] == 'x' ? 1 : 0;
        int leftY = str[0] == 'y' ? 1 : 0;
        int ans = 0;
        for (int i = 1; i < str.length; i++) {
            if (leftX == leftY || (xTotal - leftX) == (yTotal - leftY)) {
                ans++;
            }
            leftX += str[i] == 'x' ? 1 : 0;
            leftY += str[i] == 'y' ? 1 : 0;
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "ayxbxannxnnnnnxhkskskxyxksnxkyxxkxkyyysndghhhdx";
        char[] str = s.toCharArray();
        System.out.println(ways1(str));
    }
}
