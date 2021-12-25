package cn.yqy.exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;


public class TwoQueueSimulationHeapStack {
    public static void main(String[] args) {
        simulation();
    }

    public static void simulation() {
        Queue<String> q1 = new LinkedList();
        Queue<String> q2 = new LinkedList();
        q1.offer("a");
        q1.offer("b");
        q1.offer("c");
        q1.offer("d");
        ArrayList<String> list = new ArrayList<>();
        for (String s : q1) {
            list.add(s);
        }
        Collections.reverse(list);
        for (String s : list) {
            q2.offer(s);
        }
        for (String s : q2) {
            System.out.println(s);
        }

    }
}
