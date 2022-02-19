package com.jmc.algorithm.greatOffer.class42;

/**
 * 整数转换英文
 *
 * @Author jmc
 * @Description
 * @Date 2021/11/1 17:52
 **/
public class Problem_0273_IntegerToEnglishWords {
    public static String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }

        String ans = "";
        if (num < 0) {
            ans += "Negative ";
        }

        if (num == Integer.MIN_VALUE) {
            ans += "Two Billion ";
            num %= -2000000000;
        }

        String[] units = new String[]{"Billion ", "Million ", "Thousand ", ""};
        num = Math.abs(num);
        int high = 1000000000;
        int highIndex = 0;
        while (num != 0) {
            int cur = num / high;
            num %= high;
            if (cur != 0) {
                ans += numTo999(cur);
                ans += units[highIndex];
            }
            high /= 1000;
            highIndex++;
        }

        return ans.trim();
    }

    private static String numTo999(int num) {
        if (num < 1 || num > 999) {
            return "";
        }

        if (num < 100) {
            return numTo99(num);
        }

        int high = num / 100;
        num %= 100;
        return numTo19(high) + "Hundred " + numTo99(num);
    }

    private static String numTo99(int num) {
        if (num < 1 || num > 99) {
            return "";
        }

        if (num < 20) {
            return numTo19(num);
        }

        String[] tenUnits = new String[]{"Twenty ", "Thirty ", "Forty ", "Fifty ", "Sixty ", "Seventy ", "Eighty ", "Ninety "};
        int highIndex = num / 10;
        num %= 10;

        return tenUnits[highIndex - 2] + numTo19(num);
    }

    private static String numTo19(int num) {
        if (num < 1 || num > 19) {
            return "";
        }
        String[] units = new String[]{"One ", "Two ", "Three ", "Four ", "Five ", "Six ", "Seven ", "Eight ", "Nine ", "Ten ", "Eleven ", "Twelve ", "Thirteen ",
                "Fourteen ", "Fifteen ", "Sixteen ", "Seventeen ", "Eighteen ", "Nineteen "};
        return units[num - 1];
    }

    public static void main(String[] args) {
        System.out.println(numberToWords(25001));
    }
}
