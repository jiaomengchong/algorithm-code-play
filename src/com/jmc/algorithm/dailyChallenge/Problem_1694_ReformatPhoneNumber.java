package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/reformat-phone-number/
 */
public class Problem_1694_ReformatPhoneNumber {
    public static String reformatNumber(String number) {
        StringBuffer sb = new StringBuffer();
        number = number.replace("-", "").replace(" ", "");
        int N = number.length();
        int rest = N;
        int start = 0;
        while (rest > 4) {
            sb.append(number.substring(start, start + 3));
            start += 3;
            rest -= 3;
            if (rest > 0) {
                sb.append("-");
            }
        }

        if (rest == 4) {
            sb.append(number, N - 4, N - 2).append("-");
            sb.append(number, N - 2, N);
        } else {
            sb.append(number, N - rest, N);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String number = "12";
        System.out.println(reformatNumber(number));
    }
}
