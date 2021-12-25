package cn.yqy.circlesinglelinkedlist;

import java.util.ArrayList;
import java.util.List;

public class Josepfu2 {
    public void dynamic(int target, int k) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        while (!list.isEmpty()) {
            target = (target + k) % list.size();
            if (target != 0) {
                System.out.println(list.remove(target - 1));

            }

        }
    }
}
