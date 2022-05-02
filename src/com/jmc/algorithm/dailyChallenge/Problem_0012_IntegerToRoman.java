package com.jmc.algorithm.dailyChallenge;

import java.util.Stack;

/**
 * 测试链接：https://leetcode-cn.com/problems/integer-to-roman/
 */
public class Problem_0012_IntegerToRoman {
    public static String intToRoman(int num) {
        String[][] dictionary = new String[][]{
                {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"},
                {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"},
                {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"},
                {"", "M", "MM", "MMM"}
        };

        Stack<String> stack = new Stack<>();
        int index = 0;
        // 1994
        while (num != 0) {
            int mod = num % 10;
            stack.push(dictionary[index++][mod]);
            num /= 10;
        }

        StringBuffer sb = new StringBuffer();
        while (!stack.isEmpty()) {
          sb.append(stack.pop());
        }
        return sb.toString();
    }

    public static String intToRoman1(int num) {
        String[][] dictionary = new String[][]{
                {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"},
                {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"},
                {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"},
                {"", "M", "MM", "MMM"}
        };

        StringBuffer sb = new StringBuffer();
        sb.append(dictionary[3][num / 1000 % 10])
        .append(dictionary[2][num / 100 % 10])
        .append(dictionary[1][num / 10 % 10])
        .append(dictionary[0][num % 10]);
        return sb.toString();
    }

    public static void main(String[] args) {
        int num = 66;
        System.out.println(intToRoman(num));
        System.out.println(intToRoman1(num));
    }
}
