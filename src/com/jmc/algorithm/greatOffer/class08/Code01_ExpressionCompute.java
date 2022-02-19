package com.jmc.algorithm.greatOffer.class08;

import java.util.Stack;

/**
 * 给定一个字符串str，str表示一个公式，
 * 公式里可能有整数、加减乘除符号和左右括号
 * 返回公式的计算结果，难点在于括号可能嵌套很多层
 * str="48*((70-65)-43)+8*1"，返回-1816。
 * str="3+1*4"，返回7。
 * str="3+(1*4)"，返回7。
 * 【说明】
 * 1.可以认为给定的字符串一定是正确的公式，即不需要对str做公式有效性检查
 * 2.如果是负数，就需要用括号括起来，比如“4*(-3)”但如果负数作为公式的开头或括号部分的开头，则可以没有括号，比如"-3*4"和"(-3*4)"都是合法的。
 * 3.不用考虑计算过程中会发生溢出的情况。
 *
 * @author jmc
 * @version 1.0
 * @date 2021/5/22 16:15
 */
public class Code01_ExpressionCompute {
    public static int calculate(String str) {
        return process(str.toCharArray(), 0)[0];
    }

    public static int[] process(char[] arr, int index) {
        int cur = 0;
        Stack<String> stack = new Stack<>();
        int[] step = null;
        while (index < arr.length && arr[index] != ')') {
            if (arr[index] >= '0' && arr[index] <= '9') {
                cur = cur * 10 + arr[index++] - '0';
            } else if (arr[index] != '(') {
                addNum(stack, cur);
                stack.push(String.valueOf(arr[index++]));
                cur = 0;
            } else {
                step = process(arr, index + 1);
                cur = step[0];
                index = step[1] + 1;
            }
        }
        addNum(stack, cur);
        return getNum(stack, index);
    }

    private static int[] getNum(Stack<String> stack, int index) {
        boolean add = true;
        int num = 0;
        int[] ans = new int[2];
        Stack<String> calStack = new Stack<>();
        while (!stack.isEmpty()) {
            calStack.push(stack.pop());
        }
        while (!calStack.isEmpty()) {
            String pop = calStack.pop();
            if (pop.equals("+")) {
                add = true;
            } else if (pop.equals("-")) {
                add = false;
            } else {
                num += add ? Integer.valueOf(pop) : (-Integer.valueOf(pop));
            }
        }
        ans[0] = num;
        ans[1] = index;
        return ans;
    }

    private static void addNum(Stack<String> stack, int num) {
        if (!stack.isEmpty()) {
            String peek = stack.peek();
            if (peek.equals("*") || peek.equals("/")) {
                String op = stack.pop();
                int cur = Integer.parseInt(stack.pop());
                num = op.equals("*") ? (num * cur) : (cur / num);
            }
        }
        stack.push(String.valueOf(num));
    }

    public static void main(String[] args) {
        String exp = "48*((70-65)-43)+8*1";
        System.out.println(calculate(exp));

        exp = "4*(6+78)+53-9/2+45*8";
        System.out.println(calculate(exp));

        exp = "10-5*3";
        System.out.println(calculate(exp));

        exp = "-3*4";
        System.out.println(calculate(exp));

        exp = "3+1*4";
        System.out.println(calculate(exp));

        exp = "((-9-2*10)+(4*5+1))";
        System.out.println(calculate(exp));
    }
}
