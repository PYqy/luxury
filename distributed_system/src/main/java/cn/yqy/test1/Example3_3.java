package cn.yqy.test1;

import java.util.Iterator;
import java.util.LinkedList;

public class Example3_3 {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<String>();
        for (int i = 0; i < 60096; i++) {
            list.add("Speed" + i);
        }

        //使用迭代器
        Iterator<String> it = list.iterator();
        long l = System.currentTimeMillis();
        while (it.hasNext()) {
            String te = it.next();
        }
        long l1 = System.currentTimeMillis();
        System.out.println(l1 - l);

        //使用get
        l = System.currentTimeMillis();
//        for (int i = 0 ; i < list.size();i++){
//            System.out.println(list.get(i));
//        }
        l1 = System.currentTimeMillis();
        System.out.println(l1 - l);
        System.out.println(list.get(100));
        list.remove(98);
        System.out.println(list.get(100));

    }
}
