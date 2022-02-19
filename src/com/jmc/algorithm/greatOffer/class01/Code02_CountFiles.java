package com.jmc.algorithm.greatOffer.class01;

import java.io.File;
import java.util.Stack;

/**
 * 给定一个文件目录的路径，
 * 写一个函数统计这个目录下所有的文件数量并返回
 * 隐藏文件也算，但是文件夹不算
 *
 * @author jmc
 * @version 1.0
 * @date 2021/4/24 11:13
 */
public class Code02_CountFiles {

    public static int countFiles(String path) {
        File root = new File(path);
        if (!root.isDirectory() && !root.isFile()) {
            return 0;
        }
        if (root.isFile()) {
            return 1;
        }

        int ans = 0;
        Stack<File> stack = new Stack();
        stack.add(root);
        while (!stack.isEmpty()) {
            File pop = stack.pop();
            for (File file : pop.listFiles()) {
                if (file.isDirectory()) {
                    stack.push(file);
                }
                if (file.isFile()) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String path = "E:\\";
        System.out.println(countFiles(path));
    }
}
