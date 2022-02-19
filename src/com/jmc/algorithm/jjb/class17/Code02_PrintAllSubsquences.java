package com.jmc.algorithm.jjb.class17;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 1.打印一个字符串的全部子序列
 * <p>
 * 2.打印一个字符串的全部子序列，要求不要出现重复字面值的子序列
 *
 * @author jmc
 * @version 1.0
 * @date 2020/12/31 11:07
 */
public class Code02_PrintAllSubsquences {
    public static List<String> subs(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }

        String path = "";
        process1(s.toCharArray(), 0, path, ans);

        return ans;
    }

    public static void process1(char[] str, int index, String path, List<String> ans) {
        if (index == str.length) {
            ans.add(path);
            return;
        }

        process1(str, index + 1, path, ans);
        process1(str, index + 1, path + str[index], ans);
    }

    public static Set<String> subsNoRepeat(String s) {
        Set<String> ans = new HashSet<>();
        if (s == null || s.length() == 0) {
            return ans;
        }

        process2(s.toCharArray(), 0, "", ans);
        return ans;
    }

    public static void process2(char[] str, int index, String path, Set<String> ans) {
        if (index == str.length) {
            ans.add(path);
            return;
        }

        process2(str, index + 1, path, ans);
        process2(str, index + 1, path + str[index], ans);
    }

    public static void main(String[] args) {
        String s = "acc";
        List<String> ans = subs(s);
        for (String str : ans) {
            System.out.println(str);
        }

        System.out.println("====================");
        Set<String> ansNoRepeat = subsNoRepeat(s);
        for (String str : ansNoRepeat) {
            System.out.println(str);
        }
    }
}
