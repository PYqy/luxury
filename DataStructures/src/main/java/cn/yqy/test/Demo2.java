package cn.yqy.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Demo2 {
    public static void main(String[] args) {
        Integer[] a = {4,3,5,1,2};
        ArrayList<Integer> ab = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            ab.add( a[i]);
        }


        top10(ab);
    }

    public static  void top10 (ArrayList<Integer> input) {
        Object[] integers = input.toArray();
        int i = 0;
        int j = integers.length - 1 ;
        int mid = (int)integers[i];
        while(i < j) {
            while((int)integers[j] > mid ) {
                j--;
            }
            while((int)integers[i] < mid ) {
                i++;
            }
            if(i < j ) {
                int temp = (int)integers[i];
                integers[i] = integers[j];
                integers[j] = temp;
            }

        }
        System.out.println(Arrays.toString(integers));input.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });

    }
}
