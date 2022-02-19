package com.jmc.algorithm.jjb.class06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author jmc
 * @version 1.0
 * @date 2021/3/5 18:54
 */
public class Code01_Comparator {
    public static class Student {
        public String name;
        public int id;
        public int age;

        public Student(String name, int id, int age) {
            this.name = name;
            this.id = id;
            this.age = age;
        }
    }

    public static class IdShengAgeJinagOrder implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            return o1.id != o2.id ? o1.id - o2.id : (o2.age - o1.age);
        }
    }

    public static void main(String[] args) {
        Student s1 = new Student("A", 4, 40);
        Student s2 = new Student("B", 4, 21);
        Student s3 = new Student("C", 3, 12);
        Student s4 = new Student("D", 3, 62);
        Student s5 = new Student("E", 3, 42);

        // D E C A B
        Student[] students = new Student[] {s1, s2, s3, s4, s5};
        Arrays.sort(students, new IdShengAgeJinagOrder());
        for (Student s : students) {
            System.out.println(String.format("%s %s %s", s.name, s.id, s.age));
        }

        System.out.println("=============================");

        List<Student> list = new ArrayList<>();
        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
        list.add(s5);
        list.sort(new IdShengAgeJinagOrder());

        for (Student s : list) {
            System.out.println(String.format("%s %s %s", s.name, s.id, s.age));
        }

        System.out.println((0 - 1) / 2);
    }
}
