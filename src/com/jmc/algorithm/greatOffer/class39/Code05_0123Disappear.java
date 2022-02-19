package com.jmc.algorithm.greatOffer.class39;

/**
 * 真实笔试，忘了哪个公司，但是绝对大厂
 * 一个子序列的消除规则如下:
 * 1) 在某一个子序列中，如果'1'的左边有'0'，那么这两个字符->"01"可以消除
 * 2) 在某一个子序列中，如果'3'的左边有'2'，那么这两个字符->"23"可以消除
 * 3) 当这个子序列的某个部分消除之后，认为其他字符会自动贴在一起，可以继续寻找消除的机会
 * 比如，某个子序列"0231"，先消除掉"23"，那么剩下的字符贴在一起变成"01"，继续消除就没有字符了
 * 如果某个子序列通过最优良的方式，可以都消掉，`那么这样的子序列叫做“全消子序列”
 * 一个只由'0'、'1'、'2'、'3'四种字符组成的字符串str，可以生成很多子序列，返回“全消子序列”的最大长度
 * 字符串str长度 <= 200
 * 体系学习班，代码46节，第2题+第3题
 *
 * @Author jmc
 * @Description
 * @Date 2021/9/22 19:52
 **/
public class Code05_0123Disappear {
    public static int ways1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        return process1(s.toCharArray(), 0, s.length() - 1);
    }

    private static int process1(char[] str, int L, int R) {
        if (L >= R) {
            return 0;
        }

        if (L == R - 1) {
            return (str[L] == '0' && str[R] == '1') || (str[L] == '2' && str[R] == '3') ? 2 : 0;
        }

        int p1 = process1(str, L + 1, R);
        char find = str[L] == '0' ? '1' : '3';
        int p2 = 0;
        for (int index = L + 1; index <= R; index++) {
            if (str[index] == find) {
                p2 = Math.max(p2, process1(str, L + 1, index - 1) + 2 + process1(str, index + 1, R));
            }
        }

        return Math.max(p1, p2);
    }

    public static void main(String[] args) {
        String s = "021331";
        System.out.println(ways1(s));
    }
}
