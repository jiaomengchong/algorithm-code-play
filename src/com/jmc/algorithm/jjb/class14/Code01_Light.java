package com.jmc.algorithm.jjb.class14;

import java.util.HashSet;

/**
 * 给定一个字符串str，只由‘X’和‘.’两种字符构成。
 * ‘X’表示墙，不能放灯，也不需要点亮
 * ‘.’表示居民点，可以放灯，需要点亮
 * 如果灯放在i位置，可以让i-1，i和i+1三个位置被点亮
 * 返回如果点亮str中所有需要点亮的位置，至少需要几盏灯
 *
 * @author jmc
 * @version 1.0
 * @date 2020/12/18 15:59
 */
public class Code01_Light {

    public static int lessLight1(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        return process(str.toCharArray(), 0, new HashSet<>());
    }

    private static int process(char[] str, int index, HashSet<Integer> lights) {
        if (index == str.length) {
            for (int i = 0; i < str.length; i++) {
                if (str[i] != 'X') {
                    if (!lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i + 1)) {
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return lights.size();
        } else {
            int no = process(str, index + 1, lights);
            int yes = Integer.MAX_VALUE;
            if (str[index] == '.') {
                lights.add(index);
                yes = process(str, index + 1, lights);
                lights.remove(index);
            }

            return Math.min(no, yes);

        }
    }


    public static int lessLight2(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        int ans = 0;
        int i = 0;
        char[] charArray = str.toCharArray();
        while (i < charArray.length) {
            if (charArray[i] == 'X') {
                i++;
            } else {
                ans++;
                if (i + 1 == charArray.length) {
                    break;
                } else {
                    if (charArray[i + 1] == 'X') {
                        i = i + 2;
                    } else {
                        i = i + 3;
                    }
                }
            }
        }

        return ans;
    }


    public static void main(String[] args) {
        String str = "..X....................................";
        System.out.println(lessLight2(str));
        System.out.println(lessLight1(str));
    }
}
