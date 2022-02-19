package com.jmc.algorithm.jjb.class17;

import java.util.ArrayList;
import java.util.List;

/**
 * 打印一个字符串的全部排列
 *
 * @author jmc
 * @version 1.0
 * @date 2020/12/31 14:31
 */
public class Code03_PrintAllPermutations {
    public static List<String> permutation1(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }

        char[] str = s.toCharArray();
        List<Character> rest = new ArrayList<>();
        for (char c : str) {
            rest.add(c);
        }
        process1(rest, "", ans);

        return ans;
    }

    public static void process1(List<Character> rest, String path, List<String> ans) {
        if (rest.isEmpty()) {
            ans.add(path);
        } else {
            int N = rest.size();
            for (int i = 0; i < N; i++) {
                Character selectChar = rest.get(i);
                rest.remove(i);
                process1(rest, path + selectChar, ans);
                rest.add(i, selectChar);
            }
        }
    }

    public static List<String> permutation2(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }

        process2(s.toCharArray(), 0, ans);

        return ans;
    }

    public static void process2(char[] str, int index, List<String> ans) {
        if (index == str.length) {
            ans.add(String.valueOf(str));
        } else {
            for (int i = index; i < str.length; i++) {
                swap(str, index, i);
                process2(str, index + 1, ans);
                swap(str, index, i);
            }
        }
    }

    public static List<String> permutation3(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }

        process3(s.toCharArray(), 0, ans);

        return ans;
    }

    public static void process3(char[] str, int index, List<String> ans) {
        if (index == str.length) {
            ans.add(String.valueOf(str));
        } else {
            boolean[] isVisited = new boolean[256];
            for (int i = index; i < str.length; i++) {
                if (!isVisited[str[i]]) {
                    isVisited[str[i]] = true;
                    swap(str, index, i);
                    process2(str, index + 1, ans);
                    swap(str, index, i);
                }
            }
        }
    }

    public static void swap(char[] str, int i, int j) {
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }

    public static void main(String[] args) {
        String s = "acc";
        List<String> ans1 = permutation1(s);
        for (int i = 0; i < ans1.size(); i++) {
            System.out.println(ans1.get(i));
        }

        System.out.println("===================");
        List<String> ans2 = permutation3(s);
        for (int i = 0; i < ans2.size(); i++) {
            System.out.println(ans2.get(i));
        }
    }
}
