package cn.yqy.test1;

import java.util.Iterator;
import java.util.TreeSet;

public class Student implements Comparable {
    int english = 0;
    String name;

    public Student(int english, String name) {
        this.english = english;
        this.name = name;
    }


    public int compareTo(Object o) {
        Student s = (Student) o;
        return ((Student) o).english - this.english;
    }

    public static void main(String[] args) {
        TreeSet<Student> mytree = new TreeSet<Student>();
        Student s1, s2, s3, s4;
        s1 = new Student(90, "zhangsan1");
        s2 = new Student(22, "zhangsan2");
        s3 = new Student(33, "zhangsan3");
        s4 = new Student(44, "zhangsan4");
        mytree.add(s1);
        mytree.add(s2);
        mytree.add(s3);
        mytree.add(s4);
        Iterator<Student> it = mytree.iterator();
        while (it.hasNext()) {
            Student next = it.next();
            System.out.println("" + next.name + " " + next.english);
        }
    }
}
