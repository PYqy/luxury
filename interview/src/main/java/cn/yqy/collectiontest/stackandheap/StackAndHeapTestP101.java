package cn.yqy.collectiontest.stackandheap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class StackAndHeapTestP101 {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<String>(); //a队列
        Queue<String> queue2 = new LinkedList<String>(); //b队列
        ArrayList<String> a = new ArrayList<String>();  //arrlist集合是中间参数
        //往a队列添加元素
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");
        queue.offer("d");
        queue.offer("e");
        queue.offer("f");
        System.out.println("进栈");
        //a队列依次加入list集合之中
        for (String q : queue) {
            a.add(q);
            System.out.println(q);
        }
        //以倒叙的方法取出（a队列依次加入list集合）之中的值，加入b队列
        for (int i = a.size() - 1; i >= 0; i--) {
            queue2.offer(a.get(i));
        }

        //打印出栈队列
        System.out.println("");
        System.out.println("出栈：");

        for (String q : queue2) {
            System.out.println(q);
        }
    }
}
