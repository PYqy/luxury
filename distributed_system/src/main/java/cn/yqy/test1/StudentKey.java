package cn.yqy.test1;

import java.util.Map;
import java.util.TreeMap;

public class StudentKey implements Comparable {
    public static void main(String[] args) {
        TreeMap<StudentKey, Student2> treeMap = new TreeMap<StudentKey, Student2>();
        String str[] = {"zhang3", "li4", "wang5", "zhao6"};
        double math[] = {90, 28, 44, 22};
        double english[] = {11, 22, 33, 44};
        Student2 student[] = new Student2[4];
        for (int i = 0; i < student.length; i++) {
            student[i] = new Student2(str[i], math[i], english[i]);

        }
        StudentKey key[] = new StudentKey[4];
        for (int i = 0; i < key.length; i++) {
            key[i] = new StudentKey(student[i].math);

        }
        for (int i = 0; i < student.length; i++) {
            treeMap.put(key[i], student[i]);
        }
        for (Map.Entry<StudentKey, Student2> m : treeMap.entrySet()
                ) {
            System.out.println(m.getKey().d + " name=" + m.getValue().name + "；math=" + m.getValue().math + "；english=" + m.getValue().english);

        }

    }

    double d = 0;

    public StudentKey(double d) {

        this.d = d;
    }


    public int compareTo(Object o) {
        StudentKey st = (StudentKey) o;
        if ((this.d - st.d) == 0) {
            return -1;
        } else {
            return (int) ((this.d - st.d) * 1000);

        }

    }
}

class Student2 {
    String name = null;
    double math, english;

    public Student2(String name, double math, double english) {
        this.name = name;
        this.math = math;
        this.english = english;
    }

}
