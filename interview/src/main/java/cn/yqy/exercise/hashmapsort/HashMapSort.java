package cn.yqy.exercise.hashmapsort;

import sun.applet.Main;

import java.util.*;

public class HashMapSort {
    public static void main(String[] args) {
        User u1 = new User("zhang1", 37);
        User u2 = new User("zhang1", 24);
        User u3 = new User("zhang1", 55);
        User u4 = new User("zhang1", 12);
        User u5 = new User("zhang1", 9);
        HashMap<Integer, User> map = new HashMap<>();
        map.put(u1.age, u1);
        map.put(u2.age, u2);
        map.put(u3.age, u3);
        map.put(u4.age, u4);
        map.put(u5.age, u5);
        HashMapSort h = new HashMapSort();
        HashMap<Integer, User> hashmapsort = h.hashmapsort(map);
        for (Map.Entry<Integer, User> en : hashmapsort.entrySet()) {
            System.out.println(en.getKey() + "---" + en.getValue().getName());
        }
    }

    public HashMap<Integer, User> hashmapsort(HashMap<Integer, User> map) {
        Set<Map.Entry<Integer, User>> entries = map.entrySet();
        ArrayList<Map.Entry<Integer, User>> list = new ArrayList<Map.Entry<Integer, User>>();
        for (Map.Entry<Integer, User> entry : entries) {
            list.add(entry);
        }
        list.sort(new Comparator<Map.Entry<Integer, User>>() {
            public int compare(Map.Entry<Integer, User> o1, Map.Entry<Integer, User> o2) {
                return o2.getValue().getAge() - o1.getValue().getAge();
            }
        });
        HashMap<Integer, User> linkedHash = new LinkedHashMap<>();
        for (Map.Entry<Integer, User> entry : list) {
            linkedHash.put(entry.getKey(), entry.getValue());
        }
        return linkedHash;

    }

}
