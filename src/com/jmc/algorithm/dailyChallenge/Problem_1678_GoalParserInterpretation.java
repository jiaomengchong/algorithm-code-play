package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/goal-parser-interpretation/
 */
public class Problem_1678_GoalParserInterpretation {
    public static String interpret(String command) {
        return command.replace("()", "o").replace("(al)", "al");
    }

    public static void main(String[] args) {
        String command = "(al)G(al)()()G";
        System.out.println(interpret((command)));
    }
}
