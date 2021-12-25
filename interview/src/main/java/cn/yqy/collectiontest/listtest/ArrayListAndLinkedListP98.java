package cn.yqy.collectiontest.listtest;

import java.util.*;

public class ArrayListAndLinkedListP98 {
    public static final int N = 50000;//50000个数
    public static List values;  //要查找的集合


    //放入50000个数给value
    static {
        Integer vals[] = new Integer[N];
        Random r = new Random();
        for (int i = 0, currval = 0; i < N; i++) {
            vals[i] = new Integer(currval);
            currval += r.nextInt(100) + 1;
        }
        values = Arrays.asList(vals);
    }


    static long timeList(List lst) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            int index = Collections.binarySearch(lst, values.get(i));
            if (index != i) {
                System.out.println("*****错误*******");
            }

        }
        return System.currentTimeMillis() - start;
    }

    public static void main(String[] args) {
//        for( int i = 0 ; i < N ; i++){
//            System.out.println(values.get(i));
//        }
        System.out.println("ArraryList消耗时间：" + timeList(new ArrayList(values)));
        System.out.println("LinkedList消耗时间：" + timeList(new LinkedList(values)));
    }
}
