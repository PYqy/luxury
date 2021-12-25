package cn.it100.interview;

import java.lang.reflect.Array;

/**
 * 用反射机制声明长度为10的int型数组的表达式
 */

public class exercise109 {
    public static void main(String[] args) {
        Class c = int.class;
        Object o = Array.newInstance(c, 10);
        for (int i = 0; i < 10; i++) {
            Array.set(o, i, i);
        }
        int[] arr = (int[]) o;
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
