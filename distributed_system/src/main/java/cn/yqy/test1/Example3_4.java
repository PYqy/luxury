package cn.yqy.test1;

import java.util.Iterator;
import java.util.LinkedList;

public class Example3_4 {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.add("ni");
        list.add("hao");
        int number = list.size();
        for (int i = 0; i < number; i++) {
            String temp = (String) list.get(i);
            System.out.println(temp);
        }


        Iterator<String> it = list.iterator();

        while (it.hasNext()) {
            String te = it.next();
            System.out.println(te);
        }

        //使用get

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }


    }
}
